package ru.akotov.event;

import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.Plugin;
import ru.akotov.utils.PrivateLookup;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class FilteredEventBus<T> {
    private final Plugin plugin;
    private final Listener fakeListener = new Listener() {
    };
    private final Map<Class<? extends Event>, THandler<? extends Event, T>> handlers = new HashMap<>();
    private final MethodHandles.Lookup lookup = PrivateLookup.get();

    public FilteredEventBus(Plugin plugin) {
        this.plugin = plugin;
    }

    public void register(T key, Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            EventHandler annotation = method.getAnnotation(EventHandler.class);
            if (annotation == null || method.isBridge() || method.isSynthetic())
                continue;

            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(method.getParameterTypes()[0])) {
                plugin.getLogger().severe(plugin.getDescription().getFullName() + " attempted to register an invalid EventHandler method signature \"" + method.toGenericString() + "\" in " + listener.getClass());
                continue;
            }

            Class<? extends Event> eventClass = method.getParameterTypes()[0].asSubclass(Event.class);
            THandler<? extends Event, T> handler = handlers.computeIfAbsent(eventClass, this::createHandler);

            EventPriority priority = annotation.priority();

            Map<T, List<EventConsumer>> listeners = handler.priorityMap.get(priority);
            if (listeners == null) {
                handler.priorityMap.put(priority, listeners = new HashMap<>());
                Bukkit.getPluginManager().registerEvent(eventClass, fakeListener, priority, (unused, event) -> {
                    if (eventClass.isAssignableFrom(event.getClass())) {
                        handler.handle(priority, event);
                    }
                }, plugin, false);
            }

            try {
                Consumer<Event> executor = createExecutor(eventClass, listener, method);
                listeners.computeIfAbsent(key, a -> new ArrayList<>())
                        .add(new EventConsumer(listener, executor, annotation.ignoreCancelled()));
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    abstract THandler<? extends Event, T> createHandler(Class<? extends Event> eventClass);

    public void unregister(T key, Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            EventHandler annotation = method.getAnnotation(EventHandler.class);
            if (annotation == null || method.isBridge() || method.isSynthetic())
                continue;
            if (method.getParameterTypes().length != 1 || !Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                continue;

            Class<? extends Event> eventClazz = method.getParameterTypes()[0].asSubclass(Event.class);
            THandler<? extends Event, T> handler = handlers.get(eventClazz);
            if (handler == null)
                continue;
            Map<T, List<EventConsumer>> listeners = handler.priorityMap.get(annotation.priority());
            if (listeners == null)
                continue;

            List<EventConsumer> list = listeners.get(key);
            if (list == null)
                continue;
            Iterator<EventConsumer> it = list.iterator();
            while (it.hasNext())
                if (it.next().listener == listener)
                    it.remove();

            if (list.isEmpty())
                listeners.remove(key);
        }
    }

    @SuppressWarnings("unchecked")
    private Consumer<Event> createExecutor(Class eventClass, Object listener, Method method) throws Throwable {
        method.setAccessible(true);
        CallSite factory = LambdaMetafactory.metafactory(
                lookup.in(listener.getClass()),
                "accept",
                MethodType.methodType(Consumer.class, listener.getClass()),
                MethodType.methodType(void.class, Object.class),
                lookup.unreflect(method),
                MethodType.methodType(void.class, eventClass)
        );
        return (Consumer<Event>) factory.getTarget().invoke(listener);
    }

    protected static class THandler<T extends Event, M> {
        private final Map<EventPriority, Map<M, List<EventConsumer>>> priorityMap = new EnumMap<>(EventPriority.class);
        private final Function<T, M> worldGetter;

        THandler(Function<T, M> worldGetter) {
            this.worldGetter = worldGetter;
        }

        @SuppressWarnings({"unchecked", "ForLoopReplaceableByForEach"})
        void handle(EventPriority priority, Event event) {
            Map<M, List<EventConsumer>> map = priorityMap.get(priority);
            if (map == null || map.isEmpty())
                return;
            M object = worldGetter.apply((T) event);
            if (object == null)
                return;
            List<EventConsumer> list = map.get(object);
            if (list != null) {
                for (int i = 0; i < list.size(); i++)
                    list.get(i).execute(event);
            }
        }
    }

    private static class EventConsumer {
        private final Consumer<Event> consumer;
        private final boolean ignoreCancelled;
        private final Object listener;

        EventConsumer(Object listener, Consumer<Event> consumer, boolean ignoreCancelled) {
            this.consumer = consumer;
            this.listener = listener;
            this.ignoreCancelled = ignoreCancelled;
        }

        void execute(Event event) {
            if (ignoreCancelled && event instanceof Cancellable && ((Cancellable) event).isCancelled())
                return;

            try {
                consumer.accept(event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

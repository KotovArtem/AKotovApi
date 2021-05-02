package ru.akotov.nms;

public enum NmsPackage {
    NMS("net.minecraft.server." + Version.getCurrent()),
    CB("org.bukkit.craftbukkit." + Version.getCurrent()),
    CB_BLOCK(CB, "block"),
    CB_CHUNK_IO(CB, "chunkio"),
    CB_COMMAND(CB, "commandold"),
    CB_CONVERSATIONS(CB, "conversations"),
    CB_ENCHANTMENTS(CB, "enchantments"),
    CB_ENTITY(CB, "entity"),
    CB_EVENT(CB, "event"),
    CB_GENERATOR(CB, "generator"),
    CB_HELP(CB, "help"),
    CB_INVENTORY(CB, "inventory"),
    CB_MAP(CB, "transform"),
    CB_METADATA(CB, "metadata"),
    CB_POTION(CB, "potion"),
    CB_PROJECTILES(CB, "projectiles"),
    CB_SCHEDULER(CB, "scheduler"),
    CB_SCOREBOARD(CB, "scoreboard"),
    CB_UPDATER(CB, "updater"),
    CB_UTIL(CB, "util");

    private final String path;

    NmsPackage(String path) {
        this.path = path;
    }

    NmsPackage(NmsPackage path, String subpath) {
        this(path + "." + subpath);
    }

    public String getPath() {
        return path;
    }

    public Class<?> getClass(String className) {
        try {
            return Class.forName(this + "." + className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString() {
        return path;
    }

}

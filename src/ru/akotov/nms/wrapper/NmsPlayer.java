package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsField;
import ru.akotov.nms.annotation.NmsMethod;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@NmsClass(value = NmsPackage.NMS, handle = "EntityPlayer")
public interface NmsPlayer extends NmsHandler {
    @NmsField(NmsField.Type.GETTER)
    NmsPlayerConnection playerConnection();

    @NmsField(NmsField.Type.GETTER)
    int ping();

    @NmsMethod(nmsName = "a")
    void sendBook(NmsItemStack itemStack, NmsEnumHand enumHand);

    @NmsMethod
    CraftBukkitPlayer getBukkitEntity();

    default void forceOpenBook(ItemStack book) {
        final Player player = (Player) getBukkitEntity().getNmsHandle();
        PlayerInventory inventory = player.getInventory();
        final ItemStack cachedItem = inventory.getItemInMainHand();

        inventory.setItemInMainHand(book);
        this.sendBook(NmsItemStack.fromBukkitItem(book), NmsEnumHand.getEnum().MAIN_HAND());
        inventory.setItemInMainHand(cachedItem);
    }

}
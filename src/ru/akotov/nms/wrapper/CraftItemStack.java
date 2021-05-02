package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import ru.akotov.nms.cglib.NmsClassHelper;
import org.bukkit.inventory.ItemStack;

@NmsClass(value = NmsPackage.CB_INVENTORY, handle = "CraftItemStack")
public interface CraftItemStack extends NmsHandler {
    static CraftItemStack getStatic() {
        return NmsClassHelper.getInstance().getStatic(CraftItemStack.class);
    }

    @NmsMethod(isStatic = true)
    NmsItemStack asNMSCopy(ItemStack itemStack);

    @NmsMethod(isStatic = true)
    ItemStack asBukkitCopy(NmsItemStack nmsItemStack);
}

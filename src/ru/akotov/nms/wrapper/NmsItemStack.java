package ru.akotov.nms.wrapper;

import ru.akotov.nms.NmsPackage;
import ru.akotov.nms.annotation.NmsClass;
import ru.akotov.nms.annotation.NmsMethod;
import org.bukkit.inventory.ItemStack;

@NmsClass(value = NmsPackage.NMS, handle = "ItemStack")
public interface NmsItemStack extends NmsHandler {
    @NmsMethod(nmsName = "getTag")
    NmsNbtTagCompound getTagCompound();

    @NmsMethod
    void setTag(NmsNbtTagCompound nbtTagCompound);

    static NmsItemStack fromBukkitItem(ItemStack itemStack) {
        return CraftItemStack.getStatic().asNMSCopy(itemStack);
    }
}

package ru.akotov.helper;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import javax.annotation.WillClose;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public final class InventoryHelper {
    private static final ItemStack AIR = new ItemStack(Material.AIR);

    private InventoryHelper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void encodeAndWrite(Inventory inventory, @WillClose OutputStream outputStream) {
        if (inventory == null)
            return;

        try (val dataOutputStream = new BukkitObjectOutputStream(outputStream)) {
            dataOutputStream.writeInt(inventory.getSize());
            dataOutputStream.writeUTF(inventory.getName());
            for (ItemStack itemStack : inventory) {
                if (itemStack == null) itemStack = AIR;
                dataOutputStream.writeObject(itemStack);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encodeToByteArray(Inventory inventory) {
        if (inventory == null)
            return null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        encodeAndWrite(inventory, outputStream);
        return outputStream.toByteArray();
    }

    public static String encodeToString(Inventory inventory) {
        if (inventory == null)
            return "null";

        return Base64.getEncoder().encodeToString(encodeToByteArray(inventory));
    }

    public static Inventory getInventorytemStackFromString(String string) {
        if (string.equals("null"))
            return null;

        return getInventoryFromBytes(Base64.getDecoder().decode(string));
    }

    public static Inventory getInventoryFromBytes(byte[] array) {
        if (array == null)
            return null;

        return getInventoryFromBytes(new ByteArrayInputStream(array));
    }

    public static Inventory getInventoryFromBytes(@WillClose ByteArrayInputStream byteArrayInputStream) {
        if (byteArrayInputStream == null)
            return null;

        try (val objectInputStream = new BukkitObjectInputStream(byteArrayInputStream)) {
            Inventory inventory = Bukkit.createInventory(
                    null,
                    objectInputStream.readInt(),
                    objectInputStream.readUTF()
            );
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(
                        i,
                        (ItemStack) objectInputStream.readObject()
                );
            }
            return inventory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


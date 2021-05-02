package ru.akotov.utils;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WorldUtil {
    private WorldUtil() {
        throw new RuntimeException("No instance of WorldUtil for you!");
    }

    public static void copyFolder(File source, File target) {
        List<String> ignore = Arrays.asList("uid.dat", "session.dat");
        if (!ignore.contains(source.getName())) {
            if (source.isDirectory()) {
                if (!target.exists()) {
                    target.mkdirs();
                }

                String[] files = source.list();

                if (files == null) return;

                for (int i = 0; i < files.length; i++) {
                    String file = files[i];
                    File srcFile = new File(source, file);
                    File destFile = new File(target, file);
                    copyFolder(srcFile, destFile);
                }
            } else {
                try (FileInputStream in = new FileInputStream(source);
                     FileOutputStream out = new FileOutputStream(target)) {
                    byte[] buffer = new byte[1024];

                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public static boolean delete(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            if (files == null) return false;

            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    delete(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }

        return path.delete();
    }

    public static World loadWorld(String nameArena) {
        WorldCreator wc = new WorldCreator(nameArena);

        wc.generatorSettings("3;minecraft:air;2");
        wc.type(WorldType.FLAT);
        wc.generateStructures(false);

        World w = wc.createWorld();

        w.setAutoSave(false);
        w.setPVP(true);
        w.setKeepSpawnInMemory(false);

        w.setGameRuleValue("doMobSpawning", "false");
        w.setGameRuleValue("doDaylightCycle", "true");
        w.setGameRuleValue("mobGriefing", "false");
        w.setGameRuleValue("keepInventory", "false");
        w.setGameRuleValue("commandBlockOutput", "false");

        w.setTime(6000L);

        return w;
    }
}

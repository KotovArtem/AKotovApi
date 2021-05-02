package ru.akotov.nms;

import org.bukkit.Bukkit;

public enum Version {
    v1_8_R1(1),
    v1_8_R2(2),
    v1_8_R3(3),
    v1_9_R1(4),
    v1_9_R2(5),
    v1_10_R1(6),
    v1_11_R1(7),
    v1_12_R1(8),
    v1_13_R2(9),
    v1_13_R1(10);

    private static final Version VERSION;
    private final int id;

    static {
        Version currentVersion = null;

        Package bukkitPackage = Bukkit.getServer().getClass().getPackage();
        if (bukkitPackage != null) {
            String versionName = bukkitPackage.getName().split("\\.")[3];

            try {
                currentVersion = valueOf(versionName);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException("Unsupported version: " + versionName);
            }
        }

        VERSION = currentVersion;
    }

    private Version(int id) {
        this.id = id;
    }

    public static Version getCurrent() {
        return VERSION;
    }

    public int getId() {
        return id;
    }
}

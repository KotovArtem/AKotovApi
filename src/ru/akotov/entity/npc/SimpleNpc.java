package ru.akotov.entity.npc;
import com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.akotov.entity.AbstractAKotovEntity;
import ru.akotov.entity.util.EntityUtil;
import ru.akotov.nms.Version;
import ru.akotov.nms.wrapper.*;
import ru.akotov.nms.wrapper.packet.ServerNamedEntitySpawnPacket;
import ru.akotov.nms.wrapper.packet.ServerPlayerInfoPacket;

import java.nio.charset.Charset;
import java.util.UUID;

public class SimpleNpc extends AbstractAKotovEntity implements PlayerEntity {
    private final String name;
    private final ServerNamedEntitySpawnPacket packetContainer;
    private final ServerPlayerInfoPacket packetContainer0;
    private final EntityPlayer player;

    public SimpleNpc(String name, Location location) {
        super();
        this.name = name;

        World world = location.getWorld();
        WorldServer worldServer = CraftWorld.getInstance(world).getHandle();

        player = EntityPlayer.create(
                CraftServer.getInstance(Bukkit.getServer()).getServer(),
                worldServer,
                new GameProfile(
                        UUID.nameUUIDFromBytes(
                                ("OfflinePlayer:" + name).getBytes(Charset.defaultCharset())
                        ), name
                ),
                PlayerInteractManager.create(worldServer)
        );

        packetContainer = ServerNamedEntitySpawnPacket.create(player);
        packetContainer0 = ServerPlayerInfoPacket.create(
                EnumPlayerInfoAction.getEnum().ADD_PLAYER(),
                player
        );

        setLocation(location);
    }

    @Override
    public void spawn() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player);
        }
    }

    @Override
    public void send(Player player) {
        NmsPlayerConnection playerConnection =
                CraftBukkitPlayer.get(player).getHandle().playerConnection();
        playerConnection.sendPacket(packetContainer0);
        playerConnection.sendPacket(packetContainer);
    }

    @Override
    public void setLocation(Location location) {
        if (Version.getCurrent().getId() < 4) {
            packetContainer.c(EntityUtil.floor(location.getBlockX() * 32.0D));
            packetContainer.d(EntityUtil.floor(location.getBlockY() * 32.0D));
            packetContainer.e(EntityUtil.floor(location.getBlockZ() * 32.0D));
        } else {
            packetContainer.c(location.getX());
            packetContainer.d(location.getY());
            packetContainer.e(location.getZ());
        }
    }

    public String getName() {
        return this.name;
    }

    public ServerNamedEntitySpawnPacket getPacketContainer() {
        return this.packetContainer;
    }

    public ServerPlayerInfoPacket getPacketContainer0() {
        return this.packetContainer0;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }
}

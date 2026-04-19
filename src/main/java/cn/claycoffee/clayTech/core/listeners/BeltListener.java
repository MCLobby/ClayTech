package cn.claycoffee.clayTech.core.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.api.ClayTechManager;

public class BeltListener implements Listener {
    public static int getHighestBlockAt(@NotNull World w, int x, int z) {
        int currentHighestY = 0;
        for (int y = 0; y < 255; y++) {
            if (w.getBlockAt(x, y, z).getType() != Material.AIR) {
                currentHighestY = y;
            }
        }
        return currentHighestY;
    }

    public static @Nullable Location findSafeLocation(@NotNull World w) {
        boolean pass = false;
        Location ret = null;
        int i = 0;
        while (!pass) {
            int MAX_TRY_TIMES = 40;
            // 最多寻找MAX_TRY_TIMES次,否则返回null.
            if (i <= MAX_TRY_TIMES) {
                int x = new Random().nextInt(10000);
                int z = new Random().nextInt(10000);
                int y = getHighestBlockAt(w, x, z);
                Material BlockType = w.getBlockAt(x, y, z).getType();
                if (BlockType != Material.AIR && BlockType != Material.WATER && BlockType != Material.LAVA) {
                    ret = new Location(w, x + 0.0D, y + 0.0D, z + 0.0D);
                    pass = true;
                } else {
                    i++;
                }
            } else {
                return ret;
            }
        }
        return ret;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFallIntoEarth(@NotNull EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player player)) {
            return;
        }
        if (e.getCause() != EntityDamageEvent.DamageCause.VOID) {
            return;
        }

        int y = (int) player.getLocation().getY();
        if (y <= 0 && player.getWorld().getName().equalsIgnoreCase("CAsteroidBelt")) {
            Location loc = player.getLocation();
            int teleportX = (int) (loc.getX() * 16);
            int teleportY = 500;
            int teleportZ = (int) (loc.getZ() * 16);
            World world = player.getWorld();
            // todo
            // ClayTechManager.setSpaceSuitFallNoCostDurabilityOnce(p);
            ClayTechManager.allowSpaceTeleportOnce(player);
            if (getHighestBlockAt(world, teleportX, teleportZ) == 0) {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.teleport(findSafeLocation(world), TeleportCause.PLUGIN);
                    }

                }.runTask(ClayTech.getInstance());
            } else {
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.teleport(new Location(world, teleportX, teleportY, teleportZ), TeleportCause.PLUGIN);
                    }

                }.runTask(ClayTech.getInstance());
            }
            return;
        }
    }
}
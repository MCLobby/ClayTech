package cn.claycoffee.clayTech.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.api.ClayTechManager;
import cn.claycoffee.clayTech.utils.PlanetUtil;

@SuppressWarnings("deprecation")
public class Debug implements Listener {
    @EventHandler
    public void onWantTeleport(@NotNull AsyncPlayerChatEvent e) {
        if (!ClayTech.getConfigManager().isDebug()) {
            return;
        }

        if (!e.getPlayer().isOp()) {
            return;
        }

        if (e.getMessage().equalsIgnoreCase("gomoon")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    ClayTechManager.allowSpaceTeleportOnce(e.getPlayer());
                    e.getPlayer().teleport(PlanetUtil.findSafeLocation(Bukkit.getWorld("CMoon")));
                }

            }.runTask(ClayTech.getInstance());
        }

        if (e.getMessage().equalsIgnoreCase("gomars")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    ClayTechManager.allowSpaceTeleportOnce(e.getPlayer());
                    e.getPlayer().teleport(PlanetUtil.findSafeLocation(Bukkit.getWorld("CMars")));
                }
            }.runTask(ClayTech.getInstance());
        }

        if (e.getMessage().equalsIgnoreCase("gobelt")) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    ClayTechManager.allowSpaceTeleportOnce(e.getPlayer());
                    e.getPlayer().teleport(PlanetUtil.findSafeLocation(Bukkit.getWorld("CAsteroidBelt")));
                }
            }.runTask(ClayTech.getInstance());
        }
    }
}

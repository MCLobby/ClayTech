package cn.claycoffee.clayTech.core.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.jetbrains.annotations.NotNull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

public class RailwayListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void VehicleMoveEvent(@NotNull VehicleMoveEvent e) {
        if (e.getVehicle() instanceof Minecart ve) {
            Block rail = ve.getLocation().getBlock();
            if (rail.getType() == Material.POWERED_RAIL) {
                SlimefunItem item = StorageCacheUtils.getSfItem(rail.getLocation());
                if (item != null) {
                    if (item.getId().equals(ClayTechItems.HIGHSPEED_RAILWAY.getItemId())) {
                        ve.setMaxSpeed(0.4d * ClayTech.getHighRailSpeed());
                    } else {
                        ve.setMaxSpeed(0.4d);
                    }
                } else {
                    ve.setMaxSpeed(0.4d);
                }
            } else {
                ve.setMaxSpeed(0.4d);
            }
        }
    }
}

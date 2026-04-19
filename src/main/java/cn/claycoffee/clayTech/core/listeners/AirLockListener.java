package cn.claycoffee.clayTech.core.listeners;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.AirLockUtil;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

public class AirLockListener implements Listener {
    private static final String PLATE_ID = ClayTechItems.CLAY_AIR_LOCK_PLATE.getItemId();
    private static final int MAX_SEARCH_DISTANCE = 81;
    private static final Material BLOCK_TYPE = ClayTechItems.CLAY_AIR_LOCK_BLOCK.getType();
    private static final BlockFace[] FACES = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};

    public static boolean isAirLock(@NotNull Location location) {
        SlimefunItem item = StorageCacheUtils.getSfItem(location);
        if (item == null) {
            return false;
        }

        return item.getId().equals(ClayTechItems.CLAY_AIR_LOCK_BLOCK.getItemId());
    }

    public static @NotNull Set<Location> getOpenLocations(@NotNull Location centerLocation, @NotNull BlockFace doorFace) {
        Set<Location> searchedLocations = AirLockUtil.getLocations(centerLocation.getBlock(), doorFace, MAX_SEARCH_DISTANCE, AirLockListener::isAirLock);

        Set<Location> locationsToOpen = new HashSet<>();
        for (Location location : searchedLocations) {
            Block block = location.getBlock();
            if (block.getType() == BLOCK_TYPE) {
                locationsToOpen.add(location);
            }
        }

        return locationsToOpen;
    }

    @EventHandler
    public void onAirLockPlatePress(@NotNull PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            Block plate = event.getClickedBlock();
            if (plate == null) {
                return;
            }

            SlimefunItem item = StorageCacheUtils.getSfItem(plate.getLocation());
            if (item == null) {
                return;
            }

            if (!item.getId().equals(PLATE_ID)) {
                return;
            }

            for (BlockFace face : FACES) {
                checkAirLock(plate, face);
            }
        }
    }

    public void checkAirLock(@NotNull Block plate, @NotNull BlockFace doorFace) {
        Location plateLocation = plate.getLocation();
        Location centerLocation = plateLocation.clone().toBlockLocation().add(doorFace.getDirection());
        List<BlockFace> AvailableFaces = new ArrayList<>(List.of(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN));
        AvailableFaces.remove(doorFace);
        AvailableFaces.remove(doorFace.getOppositeFace());

        Bukkit.getServer().getScheduler().runTaskLater(ClayTech.getInstance(), () -> {
            for (Location location : getOpenLocations(centerLocation, doorFace)) {
                location.getBlock().setType(Material.AIR);
            }
        }, 1);

        Bukkit.getServer().getScheduler().runTaskLater(ClayTech.getInstance(), () -> {
            for (Location location : getCloseLocations(centerLocation, doorFace)) {
                location.getBlock().setType(BLOCK_TYPE);
            }
        }, 25);
    }

    public @NotNull Set<Location> getCloseLocations(@NotNull Location centerLocation, @NotNull BlockFace doorFace) {
        Set<Location> searchedLocations = AirLockUtil.getLocations(centerLocation.getBlock(), doorFace, MAX_SEARCH_DISTANCE, AirLockListener::isAirLock);

        Set<Location> locationsToClose = new HashSet<>();
        for (Location location : searchedLocations) {
            Block block = location.getBlock();
            if (block.getType() != BLOCK_TYPE) {
                locationsToClose.add(location);
            }
        }

        return locationsToClose;
    }
}

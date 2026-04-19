package cn.claycoffee.clayTech.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTechItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

@SuppressWarnings("unused")
public class AirLockUtil {
    public static final Set<BlockFace> validFaces = new HashSet<>();

    static {
        validFaces.add(BlockFace.NORTH);
        validFaces.add(BlockFace.SOUTH);
        validFaces.add(BlockFace.EAST);
        validFaces.add(BlockFace.WEST);
        validFaces.add(BlockFace.UP);
        validFaces.add(BlockFace.DOWN);
    }

    public static @NotNull BlockFace getLookingFacing(@NotNull BlockFace originalFacing) {
        BlockFace lookingFacing = originalFacing.getOppositeFace();
        if (!originalFacing.isCartesian()) {
            switch (originalFacing) {
                case NORTH_EAST, NORTH_WEST, NORTH_NORTH_EAST, NORTH_NORTH_WEST -> lookingFacing = BlockFace.NORTH;
                case SOUTH_EAST, SOUTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST -> lookingFacing = BlockFace.SOUTH;
                case EAST_NORTH_EAST, EAST_SOUTH_EAST -> lookingFacing = BlockFace.EAST;
                case WEST_NORTH_WEST, WEST_SOUTH_WEST -> lookingFacing = BlockFace.WEST;
            }
        }

        return lookingFacing;
    }

    public static @NotNull Set<Location> getLocations(@NotNull Block lookingBlock, @NotNull BlockFace lookingFacing, int limitBlocks, Function<Location, Boolean> filter) {
        Set<Location> rawLocations = getRawLocations(lookingBlock, lookingFacing, limitBlocks, filter);
        Location lookingLocation = lookingBlock.getLocation();

        World world = lookingLocation.getWorld();
        Map<Location, Double> distances = new HashMap<>();
        for (Location location : rawLocations) {
            if (world.getWorldBorder().isInside(location)) {
                double distance = location.distance(lookingLocation);
                distances.put(location, distance);
            }
        }

        // sort by shortest distance
        Set<Location> locations = new HashSet<>(distances.keySet());
        List<Location> sortedLocations = locations.stream().sorted(Comparator.comparingDouble(distances::get)).limit(limitBlocks).toList();

        return new HashSet<>(sortedLocations);
    }

    public static @NotNull Set<Location> getRawLocations(@NotNull Block lookingBlock, @NotNull BlockFace lookingFacing, int limitBlocks, @Nullable Function<Location, Boolean> filter) {
        Set<Location> locations = new HashSet<>();
        Queue<Location> queue = new LinkedList<>();
        Location lookingLocation = lookingBlock.getLocation();
        queue.offer(lookingLocation);

        Set<BlockFace> faces = new HashSet<>(validFaces);
        faces.remove(lookingFacing);
        faces.remove(lookingFacing.getOppositeFace());

        if (faces.isEmpty()) {
            return locations;
        }

        while (!queue.isEmpty() && limitBlocks > 0) {
            Block currentBlock = queue.poll().getBlock();
            Material type = currentBlock.getType();

            Location queuedLocation = currentBlock.getLocation();
            if (!locations.contains(queuedLocation)) {
                if (filter != null && filter.apply(queuedLocation)) {
                    locations.add(queuedLocation);
                }

                for (BlockFace face : faces) {
                    Block block = currentBlock.getRelative(face);
                    if (filter != null) {
                        if (block.getType() != type) {
                            continue;
                        }

                        if (!filter.apply(block.getLocation())) {
                            continue;
                        }

                        Location location = block.getLocation();
                        if (!locations.contains(location)) {
                            Location blockLocation = block.getLocation();
                            if (manhattanDistance(lookingLocation, blockLocation) < limitBlocks) {
                                queue.offer(blockLocation);
                            }
                        }
                    } else {
                        SlimefunItem item = StorageCacheUtils.getSfItem(block.getLocation());
                        if (item == null) {
                            continue;
                        }

                        if (item.getId().equals(ClayTechItems.CLAY_AIR_LOCK_BLOCK.getItemId())) {
                            Location location = block.getLocation();
                            if (!locations.contains(location)) {
                                Location blockLocation = block.getLocation();
                                if (manhattanDistance(lookingLocation, blockLocation) < limitBlocks) {
                                    queue.offer(blockLocation);
                                }
                            }
                        }
                    }
                }
            }
        }

        return locations;
    }

    public static int manhattanDistance(@NotNull Location a, @NotNull Location b) {
        int dx = Math.abs(a.getBlockX() - b.getBlockX());
        int dy = Math.abs(a.getBlockY() - b.getBlockY());
        int dz = Math.abs(a.getBlockZ() - b.getBlockZ());
        return dx + dy + dz;
    }
}

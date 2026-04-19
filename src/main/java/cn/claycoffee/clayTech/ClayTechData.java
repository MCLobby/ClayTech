package cn.claycoffee.clayTech;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClayTechData {
    public static String currentVersion = "";
    public static String jarLocation;
    public static byte @Nullable [] updateJar;
    public static @NotNull Map<Inventory, Block> RunningInjectors = new HashMap<>();
    public static @NotNull Map<Inventory, Block> RunningInjectorsOxygen = new HashMap<>();
    public static @NotNull Map<Inventory, Block> RunningLaunchersG = new HashMap<>();
    public static @NotNull Set<UUID> InRocketPlayers = new HashSet<>();
    public static @NotNull Set<Location> CantBreakBlocks = new HashSet<>();
    public static @NotNull Map<UUID, Long> LastUseTNTCreaterTime = new HashMap<>();
    public static @NotNull Set<UUID> AllowSpaceTeleport = new HashSet<>();
    public static @NotNull Map<Location, Integer> CurrentPage = new HashMap<>();
    public static @NotNull Set<UUID> SpaceSuitNoCostDurability = new HashSet<>();
}

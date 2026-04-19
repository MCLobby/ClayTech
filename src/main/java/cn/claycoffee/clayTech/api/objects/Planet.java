package cn.claycoffee.clayTech.api.objects;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.core.managers.ConfigManager;

/**
 * Planet. 星球.
 */
public class Planet {
    private static final ConfigManager planet = ClayTech.getPlanetManager();
    private static final FileConfiguration f = planet.getConfig();
    private static final Set<String> preventEndDragon = new HashSet<>();
    private static final Set<String> preventBedrock = new HashSet<>();
    private final String planetName;
    private final ItemStack displayItem;
    private final @Nullable ChunkGenerator planetWorld;
    private final Environment environment;
    private final boolean spawnMob;
    private boolean habitable;
    private double gravity;
    private int distance;
    private int harmlevel;
    private boolean cold;
    private int fuel;

    public Planet(String planetName, ItemStack displayItem, ChunkGenerator planetWorld, Environment environment,
                  boolean habitable, double gravity, int distance, int harmlevel, boolean cold, int fuel) {
        this.planetName = planetName;
        this.displayItem = displayItem;
        this.planetWorld = planetWorld;
        this.environment = environment;
        this.habitable = habitable;
        this.gravity = gravity;
        this.distance = distance;
        this.harmlevel = harmlevel;
        this.cold = cold;
        this.fuel = fuel;
        if (!f.contains(this.planetName)) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName, true);
            } else {
                f.set(this.planetName, true);
            }
            try {
                f.save(planet.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!f.contains(this.planetName + "-spawnMobs")) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName + "-spawnMobs", true);
                this.spawnMob = true;
            } else {
                f.set(this.planetName + "-spawnMobs", false);
                this.spawnMob = false;
            }
            try {
                f.save(planet.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.spawnMob = f.getBoolean(this.planetName + "-spawnMobs");
        }

    }

    public Planet(String planetName, ItemStack displayItem, @NotNull World planetWorld, Environment environment,
                  boolean habitable, int gravity, int distance, int harmlevel, boolean cold, int fuel) {
        this.planetName = planetName;
        this.displayItem = displayItem;
        this.planetWorld = planetWorld.getGenerator();
        this.environment = environment;
        this.habitable = habitable;
        this.gravity = gravity;
        this.distance = distance;
        this.harmlevel = harmlevel;
        this.cold = cold;
        this.fuel = fuel;
        if (!f.contains(this.planetName)) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName, true);
            } else {
                f.set(this.planetName, false);
            }
            try {
                f.save(planet.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!f.contains(this.planetName + "-spawnMobs")) {
            if (this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                f.set(this.planetName + "-spawnMobs", true);
                this.spawnMob = true;
            } else {
                f.set(this.planetName + "-spawnMobs", false);
                this.spawnMob = false;
            }
            try {
                f.save(planet.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.spawnMob = f.getBoolean(this.planetName + "-spawnMobs");
        }
    }

    public void register() {
        if (planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
            if (!f.getBoolean(this.planetName) && !this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
                if (Bukkit.getWorld(this.planetName) != null) {
                    Bukkit.unloadWorld(this.planetName, true);
                }
                ClayTech.getInstance().getLogger().info("Planet overworld unloaded because it is not enabled in the config. ErrorCode: 1");
                return;
            }
            for (Planet each : ClayTech.getPlanets()) {
                if (each.planetName.equalsIgnoreCase(this.planetName)) {
                    Bukkit.getLogger().info("Registering Error: Planet" + this.planetName + "already exists! ErrorCode: 2");
                    return;
                }
            }
            ClayTech.getPlanets().add(this);
            ClayTech.getInstance().getLogger().info("Overworld Planet " + this.planetName + " registered.");
            return;
        }

        if (!f.getBoolean(this.planetName) && !this.planetName.equalsIgnoreCase(ClayTech.getOverworld())) {
            if (Bukkit.getWorld(this.planetName) != null) {
                Bukkit.unloadWorld(this.planetName, true);
            }
            ClayTech.getInstance().getLogger().info("Planet \"" + this.planetName + "\" unloaded because it is not enabled in the config. ErrorCode: 3");
            return;
        }
        for (Planet each : ClayTech.getPlanets()) {
            if (each.planetName.equalsIgnoreCase(this.planetName)) {
                Bukkit.getLogger().info("Registering Error: Planet" + this.planetName + "already exists! ErrorCode: 4");
                return;
            }
        }
        ClayTech.getPlanets().add(this);

        WorldCreator newWorld = new WorldCreator(this.planetName);
        if (Bukkit.getWorld(this.planetName) == null) {
            newWorld.environment(this.environment);
            long seed = new Random().nextLong();
            newWorld = newWorld.seed(seed);
            newWorld = newWorld.type(WorldType.NORMAL);
            newWorld = newWorld.generateStructures(false);
            newWorld = newWorld.generator(getPlanetGenerator());
            World world = newWorld.createWorld();
            if (world == null) {
                ClayTech.getInstance().getLogger().info("Failed to create planet \"" + this.planetName + "\"! ErrorCode: 5");
                return;
            }

            if (world.getEnvironment() == World.Environment.THE_END) {
                String worldName = world.getName();

                // Prevents ender dragon spawn using portal
                preventEndDragon.add(worldName);
                Location endPortal = world.getBlockAt(0, 0, 0).getLocation();
                endPortal.getBlock().setType(Material.END_PORTAL);

                // remove end portal when the chunk load (make sure bukkit has ignored the world)
                Bukkit.getScheduler().runTaskTimer(ClayTech.getInstance(), () -> {
                    if (!preventEndDragon.contains(worldName)) {
                        return;
                    }

                    for (Player player : world.getPlayers()) {
                        if (player.getLocation().distance(endPortal) < 20) {
                            if (world.getBlockAt(0, 0, 0).getType() == Material.END_PORTAL) {
                                world.getBlockAt(0, 0, 0).setType(Material.AIR);
                            }

                            preventEndDragon.remove(worldName);
                        }
                    }
                }, 1, 20);

                // Same, but for bedrock
                preventBedrock.add(worldName);
                Location bedrock = world.getBlockAt(0, 60, 0).getLocation();
                Bukkit.getScheduler().runTaskTimer(ClayTech.getInstance(), () -> {
                    if (preventBedrock.contains(worldName)) {
                        return;
                    }

                    for (Player player : world.getPlayers()) {
                        if (player.getLocation().distance(bedrock) < 20) {
                            if (world.getBlockAt(0, 60, 0).getType() == Material.BEDROCK) {
                                world.getBlockAt(0, 60, 0).setType(Material.AIR);
                            }

                            preventBedrock.remove(worldName);
                        }
                    }
                }, 1, 20);
            }
            if (this.cold) {
                // 如果冷就不会下雨
                world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
            ClayTech.getInstance().getLogger().info("Planet \"" + this.planetName + "\" created with seed " + seed);
            return;
        }

        try {
            newWorld.createWorld();
            ClayTech.getInstance().getLogger().info("Planet \"" + this.planetName + "\" created!");
        } catch (Exception e) {
            e.printStackTrace();
            ClayTech.getInstance().getLogger().info("Failed to create planet \"" + this.planetName + "\"! ErrorCode: 6");
        }
    }

    private @NotNull String trimWorldName(@NotNull String userInput) {
        return userInput.replaceAll("^[./\\\\]+", "");
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int newDistance) {
        this.distance = newDistance;
    }

    public int getHarmLevel() {
        return this.harmlevel;
    }

    public void setHarmLevel(int newLevel) {
        this.harmlevel = newLevel;
    }

    public boolean getHabitable() {
        return this.habitable;
    }

    public void setHabitable(boolean habitable) {
        this.habitable = habitable;
    }

    public boolean getCold() {
        return this.cold;
    }

    public void setCold(boolean cold) {
        this.cold = cold;
    }

    public boolean getMobSpawnable() {
        return this.spawnMob;
    }

    public double getGravity() {
        return this.gravity;
    }

    public void setGravity(int newGravity) {
        this.gravity = newGravity;
    }

    public String getPlanetWorldName() {
        return this.planetName;
    }

    public @NotNull ItemStack getDisplayStack() {
        return this.displayItem.clone();
    }

    public @Nullable ChunkGenerator getPlanetGenerator() {
        return this.planetWorld;
    }

    public int getFuel() {
        return this.fuel;
    }
}

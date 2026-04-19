package cn.claycoffee.clayTech;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.claycoffee.clayTech.api.ClayTechManager;
import cn.claycoffee.clayTech.api.objects.Planet;
import cn.claycoffee.clayTech.core.managers.ConfigManager;
import cn.claycoffee.clayTech.core.managers.ListenerManager;
import cn.claycoffee.clayTech.core.services.LocalizationService;
import cn.claycoffee.clayTech.core.worlds.AsteroidBelt;
import cn.claycoffee.clayTech.core.worlds.Earth;
import cn.claycoffee.clayTech.core.worlds.Mars;
import cn.claycoffee.clayTech.core.worlds.Moon;
import cn.claycoffee.clayTech.implementation.items.Armors;
import cn.claycoffee.clayTech.implementation.items.ClayBasic;
import cn.claycoffee.clayTech.implementation.items.ClayFuelResource;
import cn.claycoffee.clayTech.implementation.items.DrinkMakingStaff;
import cn.claycoffee.clayTech.implementation.items.Drinks;
import cn.claycoffee.clayTech.implementation.items.EffectItems;
import cn.claycoffee.clayTech.implementation.items.Elements;
import cn.claycoffee.clayTech.implementation.items.FoodMakingStaff;
import cn.claycoffee.clayTech.implementation.items.Foods;
import cn.claycoffee.clayTech.implementation.items.GoldenThings;
import cn.claycoffee.clayTech.implementation.items.Ingots;
import cn.claycoffee.clayTech.implementation.items.MachineMakingBasic;
import cn.claycoffee.clayTech.implementation.items.Machines;
import cn.claycoffee.clayTech.implementation.items.PotionAffectWeapons;
import cn.claycoffee.clayTech.implementation.items.Railways;
import cn.claycoffee.clayTech.implementation.items.RocketMakings;
import cn.claycoffee.clayTech.implementation.items.Rockets;
import cn.claycoffee.clayTech.implementation.items.Skulls;
import cn.claycoffee.clayTech.implementation.items.Spacethings;
import cn.claycoffee.clayTech.implementation.items.Tools;
import cn.claycoffee.clayTech.implementation.resources.ClayFuel;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.Metrics;
import cn.claycoffee.clayTech.utils.PlanetUtil;
import cn.claycoffee.clayTech.utils.RocketUtil;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;


@SuppressWarnings({"unused", "deprecation"})
public class ClayTech extends JavaPlugin implements SlimefunAddon {
    private static final boolean compatible = true;
    private static final List<Planet> planetList = new ArrayList<>();
    protected static ClayTech plugin;
    private static @Nullable String locale;
    private static @Nullable Double highrailspeed;
    private static @Nullable String overworld = "";
    private static boolean spacetravelneedperm;
    private static @Nullable String updateBranch;
    private static FileConfiguration config;
    private static FileConfiguration defaultLang;
    private static boolean worldBorderEnabled;
    private static LocalizationService localizationService;
    private static final BukkitRunnable harmInSpace = new BukkitRunnable() {
        @SuppressWarnings("deprecation")
        @Override
        public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player == null) {
                    continue;
                }

                if (!player.isOnline()) {
                    continue;
                }

                Planet planet = PlanetUtil.getPlanet(player.getWorld());
                if (planet == null) {
                    continue;
                }

                if (planet.getHabitable()) {
                    continue;
                }

                int harmlevel = planet.getHarmLevel();

                World PreviousWorld = player.getWorld();
                if (!PreviousWorld.equals(player.getWorld())) {
                    return;
                }

                boolean isSpaceSuit = ClayTechManager.isSpaceSuit(player.getInventory().getHelmet())
                        && ClayTechManager.isSpaceSuit(player.getInventory().getChestplate())
                        && ClayTechManager.isSpaceSuit(player.getInventory().getLeggings())
                        && ClayTechManager.isSpaceSuit(player.getInventory().getBoots());
                if (!(isSpaceSuit)) {
                    player.sendTitle(Lang.readGeneralText("SpaceSuitError"),
                            Lang.readGeneralText("SpaceSuitError_Sub"));
                    player.damage(5);
                    return;
                }

                boolean isOxygenSuit = RocketUtil.getOxygen(player.getInventory().getHelmet()) > 0
                        && RocketUtil.getOxygen(player.getInventory().getChestplate()) > 0
                        && RocketUtil.getOxygen(player.getInventory().getLeggings()) > 0
                        && RocketUtil.getOxygen(player.getInventory().getBoots()) > 0;
                if (!(isOxygenSuit)) {
                    player.sendTitle(Lang.readGeneralText("SpaceSuitError"),
                            Lang.readGeneralText("SpaceSuitError_Sub"));
                    player.damage(5);
                    return;
                }

                boolean isProtectSuit =
                        RocketUtil
                                .getProtectLevel(player.getInventory().getHelmet()) > harmlevel
                                && RocketUtil.getProtectLevel(
                                player.getInventory().getChestplate()) > harmlevel
                                && RocketUtil.getProtectLevel(
                                player.getInventory().getLeggings()) > harmlevel
                                && RocketUtil.getProtectLevel(
                                player.getInventory().getBoots()) > harmlevel;

                if (!isProtectSuit) {
                    // 扣血
                    player.sendTitle(Lang.readGeneralText("SpaceSuitError"),
                            Lang.readGeneralText("SpaceSuitError_Sub"));
                    player.damage(5);
                    return;
                }
            }
        }

    };
    private static ListenerManager listenerManager;
    private static ConfigManager configManager;
    private static ConfigManager planetManager;
    private static ConfigManager planetDataManager;
    private final BukkitRunnable autoUpdate = new BukkitRunnable() {

        @Override
        public void run() {
            ChatColor yellow = ChatColor.YELLOW;
            ChatColor green = ChatColor.GREEN;
            // Updater
            updateBranch = config.getString("update-branch");

            getLogger().info(yellow + Lang.readGeneralText("Info_1"));
            getLogger().info(yellow + Lang.readGeneralText("Auto-updater_disabled"));
            getLogger().info(yellow + Lang.readGeneralText("Info_6"));

            List<String> Authors = plugin.getDescription().getAuthors();
            getLogger().info(green + Lang.readGeneralText("Info_1"));
            getLogger().info(green + Lang.readGeneralText("Info_2").replaceAll("\\{version\\}",
                    plugin.getDescription().getVersion()));
            getLogger().info(
                    green + Lang.readGeneralText("Info_3").replaceAll(
                            "\\{author\\}",
                            Arrays.toString(Authors.toArray(new String[0]))));
            getLogger().info(green + Lang.readGeneralText("Info_4"));
            getLogger().info(green
                    + Lang.readGeneralText("Info_5").replaceAll("\\{issue_tracker\\}", plugin.getBugTrackerURL()));
            getLogger().info(green + Lang.readGeneralText("Info_6"));
        }

    };

    public static LocalizationService getLocalizationService() {
        return localizationService;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static ConfigManager getPlanetManager() {
        return planetManager;
    }

    public static ConfigManager getPlanetDataManager() {
        return planetDataManager;
    }

    public static ClayTech getInstance() {
        return plugin;
    }

    public static @Nullable String getLocale() {
        return locale;
    }

    public static boolean isSpaceTravelNeedPerm() {
        return spacetravelneedperm;
    }

    public static @Nullable Double getHighRailSpeed() {
        return highrailspeed;
    }

    public static boolean getCompatible() {
        return compatible;
    }

    public static @NotNull List<Planet> getPlanets() {
        return planetList;
    }

    public static @Nullable String getOverworld() {
        return overworld;
    }

    public static @Nullable String getUpdateBranch() {
        return updateBranch;
    }

    public static FileConfiguration getDefaultLang() {
        return defaultLang;
    }

    public static boolean isWorldBorderEnabled() {
        return worldBorderEnabled;
    }

    @Override
    public void onEnable() {
        plugin = this;

        configManager = new ConfigManager("config.yml");
        config = configManager.getConfig();

        locale = config.getString("Locale");
        if (locale == null) {
            locale = "en-US";
        }

        highrailspeed = configManager.getConfig().getDouble("highrailspeed");
        if (highrailspeed == null) {
            highrailspeed = 3D;
        }

        localizationService = new LocalizationService(this);
        localizationService.addLanguage(locale);
        localizationService.addLanguage("en-US");

        overworld = config.getString("overworld");

        if (!compatible) {
            getLogger().info(Lang.readGeneralText("Not_compatible"));
        }

        Metrics mt = new Metrics(this, 6887);
        mt.addCustomChart(new Metrics.SimplePie("language", () -> languageCodeToLanguage(locale)));

        planetManager = new ConfigManager("planets.yml");
        planetDataManager = new ConfigManager("planetdata.yml");

        getLogger().info(Lang.readGeneralText("startTip"));
        getLogger().info(Lang.readGeneralText("registeringItems"));
        try {
            getLogger().info(Lang.readGeneralText("registeringSlimefun"));
            registerSlimefun();
            getLogger().info(Lang.readGeneralText("registeringPlanets"));
            registerPlanets();
            getLogger().info(Lang.readGeneralText("registeringResources"));
            registerResources();
        } catch (Exception e) {
            getLogger().info(Lang.readGeneralText("registeringError"));
            e.printStackTrace();
        }
        if (this.getServer().getPluginManager().isPluginEnabled("WorldBorder")) {
            getLogger().info(Lang.readGeneralText("WorldBorder"));
            worldBorderEnabled = true;
        }

        getLogger().info(Lang.readGeneralText("registeringListeners"));
        listenerManager = new ListenerManager(this);
        listenerManager.setup();

        PluginCommand command = this.getCommand("claytech");
        if (command != null) {
            command.setExecutor(new ClayTechCommands());
        } else {
            getLogger().severe("Command /claytech not found.");
        }

        spacetravelneedperm = config.getBoolean("space-travel-need-perm");

        ClayTechData.currentVersion = this.getDescription().getVersion();

        autoUpdate.runTaskAsynchronously(this);

        harmInSpace.runTaskTimer(ClayTech.getInstance(), 20, 20);

        getLogger().info("ClayTech has been enabled.");
    }

    @Override
    public void onDisable() {
        if (ClayTech.getInstance().getConfig().getBoolean("replace-when-server-stops")) {
            if (ClayTechData.jarLocation != null & ClayTechData.updateJar != null) {
                try {
                    FileOutputStream os = new FileOutputStream(new File(ClayTechData.jarLocation));
                    os.write(ClayTechData.updateJar);
                    os.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private @NotNull String languageCodeToLanguage(@NotNull String code) {
        return switch (code.toUpperCase()) {
            case "ZH-CN" -> "Simplified Chinese";
            case "ZH-TW" -> "Traditional Chinese";
            case "EN-US" -> "English(US)";
            case "EN-GB" -> "English(UK)";
            case "JA" -> "Japanese";
            case "PL-PL" -> "Polski";
            case "FR" -> "Français";
            default -> code;
        };
    }

    private void registerSlimefun() {
        new ClayBasic();
        new Machines();
        new PotionAffectWeapons();
        new GoldenThings();
        new Skulls();
        new Armors();
        new DrinkMakingStaff();
        new Drinks();
        new FoodMakingStaff();
        new Foods();
        new MachineMakingBasic();
        new Elements();
        new Railways();
        new EffectItems();
        new Ingots();
        new Tools();
        new ClayFuelResource();
        new RocketMakings();
        new Rockets();
        new Spacethings();
    }


    @Override
    public @NotNull JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public @NotNull File getFile() {
        return super.getFile();
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/balugaq/ClayTech/issues";
    }

    private void registerPlanets() {
        // Earth(Overworld) 地球(主世界)
        new Earth();
        // Moon 月球
        new Moon();
        // Mars 火星
        new Mars();
        // Asteroid Belt 小行星带
        new AsteroidBelt();
    }

    private void registerResources() {
        new ClayFuel();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @NotNull String id) {
        List<String> PlanetNameList = new ArrayList<>();
        List<Planet> PlanetList = getPlanets();
        for (Planet p : PlanetList) {
            PlanetNameList.add(p.getPlanetWorldName());
        }
        for (String name : PlanetNameList.toArray(new String[0])) {
            if (id.equals(name)) {
                return PlanetList.get(PlanetNameList.indexOf(id)).getPlanetGenerator();
            }
        }
        return Bukkit.getWorld(getOverworld()).getGenerator();
    }
}

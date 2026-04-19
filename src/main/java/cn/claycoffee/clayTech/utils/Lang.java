package cn.claycoffee.clayTech.utils;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTech;

public class Lang {
    public static @NotNull String rocketPrefix = readGeneralText("Rocket");
    public static @NotNull String spaceSuitPrefix = readGeneralText("SpaceSuit");
    public static @NotNull String oxygenDistributerPrefix = readGeneralText("OxygenDistributer");

    public static @NotNull String readItemText(String name) {
        return ClayTech.getLocalizationService().getString("Items." + name);
    }

    public static @NotNull List<String> readItemLore(String name) {
        return ClayTech.getLocalizationService().getStringList("Items." + name + "_LORE");
    }

    public static @NotNull String readGeneralText(String name) {
        return ClayTech.getLocalizationService().getString("General." + name);
    }

    public static @NotNull String readCategoriesText(String name) {
        return ClayTech.getLocalizationService().getString("Categories." + name);
    }

    public static @NotNull String readResearchesText(String name) {
        return ClayTech.getLocalizationService().getString("Researches." + name);
    }

    public static @NotNull String readMachinesText(String name) {
        return ClayTech.getLocalizationService().getString("Machines." + name);
    }

    public static @NotNull String readMachineRecipesText(String name) {
        return ClayTech.getLocalizationService().getString("MachineRecipes." + name);
    }

    public static @NotNull String readPlanetsText(String name) {
        return ClayTech.getLocalizationService().getString("Planets." + name);
    }

    public static @NotNull String readResourcesText(String name) {
        return ClayTech.getLocalizationService().getString("Resources." + name);
    }

}

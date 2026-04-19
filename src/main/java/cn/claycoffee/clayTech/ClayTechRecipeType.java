package cn.claycoffee.clayTech;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.utils.KeyUtil;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class ClayTechRecipeType {
    public static final RecipeType CLAY_CRAFTING_TABLE = new RecipeType(
            KeyUtil.newKey("CLAYTECH_CRAFTIONG_TABLE"),
            new SlimefunItemStack("CLAYTECH_CRAFTING_TABLE", ClayTechItems.CLAY_CRAFTING_TABLE), "",
            Lang.readMachineRecipesText("CLAYTECH_FUSION_MACHINE"));
    public static final RecipeType CLAY_FOOD_CAULDRON = new RecipeType(
            KeyUtil.newKey("CLAYTECH_FOOD_CAULDRON"),
            new SlimefunItemStack("CLAYTECH_FOOD_CAULDRON", ClayTechItems.CLAY_FOOD_CAULDRON), "",
            Lang.readMachineRecipesText("CLAYTECH_ELETRIC_CAULDRON"));
    public static final RecipeType CLAY_FOOD_CHALKING_MACHINE = new RecipeType(
            KeyUtil.newKey("CLAYTECH_FOOD_CHALKING_MACHINE"),
            new SlimefunItemStack("CLAYTECH_FOOD_CHALKING_MACHINE", ClayTechItems.CLAY_FOOD_CHALKING_MACHINE), "",
            Lang.readMachineRecipesText("CLAYTECH_FOOD_CHALKING_MACHINE"));
    public static final RecipeType CLAY_ELEMENT_EXTRACTER = new RecipeType(
            KeyUtil.newKey("CLAYTECH_ELEMENT_EXTRACTER"),
            new SlimefunItemStack("CLAYTECH_ELEMENT_EXTRACTER", ClayTechItems.CLAY_ELEMENT_EXTRACTER), "",
            Lang.readMachineRecipesText("CLAYTECH_ELEMENT_EXTRACTER"));
    public static final RecipeType CLAY_EXPERIMENT_TABLE_BASIC = new RecipeType(
            KeyUtil.newKey("CLAYTECH_EXPERIMENT_TABLE_BASIC"),
            new SlimefunItemStack("CLAYTECH_EXPERIMENT_TABLE_BASIC", ClayTechItems.CLAY_EXPERIMENT_TABLE_NORMAL), "",
            Lang.readMachineRecipesText("CLAYTECH_EXPERIMENT_TABLE_BASIC"));
    public static final RecipeType CLAY_ROCKET_ASSEMBLING_MACHINE = new RecipeType(
            KeyUtil.newKey("CLAYTECH_ROCKET_ASSEMBLING_MACHINE"),
            new SlimefunItemStack("CLAYTECH_ROCKET_ASSEMBLING_MACHINE", ClayTechItems.CLAY_ROCKET_ASSEMBLING_MACHINE), "",
            Lang.readMachineRecipesText("CLAYTECH_ROCKET_ASSEMBLING_MACHINE"));
    public static final RecipeType CLAY_ROCKET_FUEL_GENERATOR = new RecipeType(
            KeyUtil.newKey("CLAYTECH_ROCKET_FUEL_GENERATOR"),
            new SlimefunItemStack("CLAYTECH_ROCKET_FUEL_GENERATOR", ClayTechItems.CLAY_ROCKET_FUEL_GENERATOR), "",
            Lang.readMachineRecipesText("CLAYTECH_ROCKET_FUEL_GENERATOR"));
    public static final RecipeType DIG_IN_THE_MOON = new RecipeType(
            KeyUtil.newKey("DIG_IN_THE_MOON"), new SlimefunItemStack("CLAYTECH_DIG_IN_THE_MOON",
            new ItemStack(Material.IRON_PICKAXE), "", Lang.readMachineRecipesText("DIG_IN_THE_MOON")));
    public static final RecipeType DIG_IN_NON_EARTH = new RecipeType(
            KeyUtil.newKey("DIG_IN_NON_EARTH"), new SlimefunItemStack("CLAYTECH_DIG_IN_NON_EARTH",
            new ItemStack(Material.IRON_PICKAXE), "", Lang.readMachineRecipesText("DIG_IN_NON_EARTH")));
    public static final RecipeType FISHING = new RecipeType(KeyUtil.newKey("FISHING"),
            new SlimefunItemStack("CLAYTECH_FISHING", new ItemStack(Material.TROPICAL_FISH), "",
                    Lang.readMachineRecipesText("FISHING")));
    public static final RecipeType HARVEST = new RecipeType(KeyUtil.newKey("HARVEST"),
            new SlimefunItemStack("HARVEST", new ItemStack(Material.DIAMOND_HOE), "",
                    Lang.readMachineRecipesText("HARVEST")));
    public static final RecipeType PLUCKING = new RecipeType(KeyUtil.newKey("PLUCKING"),
            new SlimefunItemStack("CLAYTECH_PLUCKING", new ItemStack(Material.APPLE), "",
                    Lang.readMachineRecipesText("PLUCKING")));
    public static final RecipeType CLEANING = new RecipeType(KeyUtil.newKey("CLEANING"),
            new SlimefunItemStack("CLAYTECH_CLEANING", new ItemStack(Material.WATER_BUCKET), "",
                    Lang.readMachineRecipesText("CLEANING")));
}

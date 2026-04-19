package cn.claycoffee.clayTech.implementation.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechMachineRecipes;
import cn.claycoffee.clayTech.ClayTechRecipeType;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class FoodMakingStaff {
    public FoodMakingStaff() {
        SlimefunUtil.newResearch()
                .withId(950245)
                .withName(Lang.readResearchesText("CLAYTECH_FOOD_MAKINGS_I"))
                .withCost(50)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.RAW_CHICKEN_FOOT)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CHALKING_MACHINE)
                                .withRecipe(ClayTechMachineRecipes.RAW_CHICKEN_FOOT)
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.RAW_BREAD)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        null, new ItemStack(Material.BREAD), null,
                                        null, ClayTechItems.MAGIC_CLAY, null,
                                        null, new ItemStack(Material.BREAD), null})
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.RAW_VEGETABLE)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        null, new ItemStack(Material.KELP), null,
                                        null, ClayTechItems.MAGIC_CLAY, null,
                                        null, new ItemStack(Material.BAMBOO), null})
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950246)
                .withName(Lang.readResearchesText("CLAYTECH_FRUIT_I"))
                .withCost(50)
                .addItem(ClayTechItems.CLAY_LEMON)
                .build();
    }
}
package cn.claycoffee.clayTech.implementation.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechMachineRecipes;
import cn.claycoffee.clayTech.ClayTechRecipeType;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

public class DrinkMakingStaff {
    public DrinkMakingStaff() {
        SlimefunUtil.newResearch()
                .withId(950234)
                .withName(Lang.readResearchesText("CLAYTECH_COCOA_BEAN"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.COCOA_BEAN)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS),
                                        new ItemStack(Material.COCOA_BEANS), ClayTechItems.CLAY_STICK, new ItemStack(Material.COCOA_BEANS),
                                        new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS)})
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950235)
                .withName(Lang.readResearchesText("CLAYTECH_PLASTIC"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.PLASTIC)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        null, null, null,
                                        null, SlimefunItems.OIL_BUCKET, null,
                                        null, null, null})
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950236)
                .withName(Lang.readResearchesText("CLAYTECH_DRINK_BOTTLE"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.DRINK_BOTTLE)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        ClayTechItems.PLASTIC, ClayTechItems.PLASTIC, ClayTechItems.PLASTIC,
                                        ClayTechItems.PLASTIC, ClayTechItems.MAGIC_CLAY, ClayTechItems.PLASTIC,
                                        ClayTechItems.PLASTIC, ClayTechItems.PLASTIC, ClayTechItems.PLASTIC})
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950237)
                .withName(Lang.readResearchesText("CLAYTECH_DIRTY_DRINK_BOTTLE"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.DIRTY_DRINK_BOTTLE)
                                .withRecipeType(RecipeType.NULL)
                                .withRecipe(ClayTechItems.NORECIPE)
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950238)
                .withName(Lang.readResearchesText("CLAYTECH_DIRTY_TEA"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.DIRTY_TEA)
                                .withRecipeType(ClayTechRecipeType.HARVEST)
                                .withRecipe(ClayTechItems.NORECIPE)
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950239)
                .withName(Lang.readResearchesText("CLAYTECH_RAW_TEA"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.RAW_TEA)
                                .withRecipeType(ClayTechRecipeType.CLEANING)
                                .withRecipe(ClayTechItems.NORECIPE)
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950240)
                .withName(Lang.readResearchesText("CLAYTECH_TEA_POWDER"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.TEA_POWDER)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CHALKING_MACHINE)
                                .withRecipe(ClayTechMachineRecipes.TEA_POWDER)
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950241)
                .withName(Lang.readResearchesText("CLAYTECH_LEMON_POWDER"))
                .withCost(10)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.LEMON_POWDER)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CHALKING_MACHINE)
                                .withRecipe(ClayTechMachineRecipes.LEMON_POWDER)
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_FOODMATERIALS)
                                .withItem(ClayTechItems.CLAY_LEMON)
                                .withRecipeType(ClayTechRecipeType.HARVEST)
                                .withRecipe(ClayTechItems.NORECIPE)
                                .build())
                .build();
    }
}
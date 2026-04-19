package cn.claycoffee.clayTech.implementation.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class Skulls {
    public Skulls() {
        SlimefunUtil.newResearch()
                .withId(950273)
                .withName(Lang.readResearchesText("CLAYTECH_SKULL_I"))
                .withCost(50)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DECORATES)
                                .withItem(ClayTechItems.CLAYCOFFEE_HEAD)
                                .withRecipeType(RecipeType.ANCIENT_ALTAR)
                                .withRecipe(new ItemStack[]{
                                        new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.DIAMOND_BLOCK),
                                        ClayTechItems.ARTIFICIAL_GOLD_INGOT, ClayTechItems.MAGIC_CLAY, ClayTechItems.ARTIFICIAL_GOLD_INGOT,
                                        new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.DIAMOND_BLOCK)})
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DECORATES)
                                .withItem(ClayTechItems.STALIN_HEAD)
                                .withRecipeType(RecipeType.ANCIENT_ALTAR)
                                .withRecipe(new ItemStack[]{
                                        new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK),
                                        ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.MAGIC_CLAY, ClayTechItems.ARTIFICIAL_GOLD_NUGGET,
                                        new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK)})
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DECORATES)
                                .withItem(ClayTechItems.MARX_HEAD)
                                .withRecipeType(RecipeType.ANCIENT_ALTAR)
                                .withRecipe(new ItemStack[]{
                                        new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK),
                                        ClayTechItems.ARTIFICIAL_GOLD_NUGGET, ClayTechItems.CLAY_STICK, ClayTechItems.ARTIFICIAL_GOLD_NUGGET,
                                        new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.IRON_BLOCK)})
                                .build())
                .build();

        SlimefunUtil.newResearch()
                .withId(950274)
                .withName(Lang.readResearchesText("CLAYTECH_DECORATES_I"))
                .withCost(50)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DECORATES)
                                .withItem(ClayTechItems.CLOCK_C)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                                        new ItemStack(Material.IRON_INGOT), ClayTechItems.MAGIC_CLAY, new ItemStack(Material.IRON_INGOT),
                                        new ItemStack(Material.IRON_INGOT), new ItemStack(Material.BLACK_DYE), new ItemStack(Material.IRON_INGOT)})
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DECORATES)
                                .withItem(ClayTechItems.LANTERN_C)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        new ItemStack(Material.OAK_LOG), new ItemStack(Material.OAK_LOG), new ItemStack(Material.OAK_LOG),
                                        new ItemStack(Material.OAK_LOG), new ItemStack(Material.GLOWSTONE), new ItemStack(Material.OAK_LOG),
                                        new ItemStack(Material.OAK_LOG), ClayTechItems.MAGIC_CLAY, new ItemStack(Material.OAK_LOG)})
                                .build())
                .build();
    }
}

package cn.claycoffee.clayTech.implementation.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class ClayBasic {
    public ClayBasic() {
        SlimefunUtil.newResearch()
                .withId(950232)
                .withName(Lang.readResearchesText("CLAYTECH_START"))
                .withCost(20)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_BASICS)
                                .withItem(ClayTechItems.MAGIC_CLAY)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(
                                        new ItemStack[]{new ItemStack(Material.COAL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COAL),
                                                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.CLAY), new ItemStack(Material.IRON_INGOT),
                                                new ItemStack(Material.COAL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COAL)}
                                )
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_BASICS)
                                .withItem(ClayTechItems.CLAY_STICK)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(
                                        new ItemStack[]{new ItemStack(Material.COAL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COAL),
                                                new ItemStack(Material.DIRT), new ItemStack(Material.STICK), new ItemStack(Material.DIRT),
                                                new ItemStack(Material.COAL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.COAL)}
                                )
                                .build())
                .build();
    }
}

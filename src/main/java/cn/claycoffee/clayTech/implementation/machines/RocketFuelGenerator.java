package cn.claycoffee.clayTech.implementation.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechMachineRecipes;
import cn.claycoffee.clayTech.api.slimefun.ACraftingTable;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class RocketFuelGenerator extends ACraftingTable {

    public RocketFuelGenerator(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                               ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_ROCKET_FUEL_GENERATOR");
    }

    @Override
    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    @Override
    public int getEnergyConsumption() {
        return 64;
    }

    @Override
    public void registerDefaultRecipes() {
        this.registerRecipe(8, ClayTechMachineRecipes.MIXED_ROCKET_FUEL,
                new ItemStack[]{ClayTechItems.MIXED_ROCKET_FUEL});
    }

    @Override
    public int getCapacity() {
        return 256;
    }

}

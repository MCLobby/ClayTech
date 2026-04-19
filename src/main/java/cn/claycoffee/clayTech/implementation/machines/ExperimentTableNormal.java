package cn.claycoffee.clayTech.implementation.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechMachineRecipes;
import cn.claycoffee.clayTech.api.slimefun.AExperimentTable;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class ExperimentTableNormal extends AExperimentTable {

    public ExperimentTableNormal(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                                 ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_EXPERIMENTTABLE_NORMAL");
    }

    @Override
    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.TNT);
    }

    @Override
    public int getEnergyConsumption() {
        return 80;
    }

    @Override
    public int getCapacity() {
        return 1024;
    }

    @Override
    public void registerDefaultRecipes() {
        this.registerRecipe(20, ClayTechMachineRecipes.SILICON_INGOT, new ItemStack[]{ClayTechItems.SILICON_INGOT});
    }
}

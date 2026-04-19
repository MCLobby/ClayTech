package cn.claycoffee.clayTech.implementation.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.api.slimefun.ANewContainer;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class FoodChalkingMachine extends ANewContainer {

    public FoodChalkingMachine(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                               ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_FOOD_CHALKING_MACHINE");
    }

    @Override
    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    @Override
    public int getEnergyConsumption() {
        return 32;
    }

    @Override
    public int getCapacity() {
        return 512;
    }

    @Override
    public void registerDefaultRecipes() {
        this.registerRecipe(8, new ItemStack[]{ClayTechItems.RAW_TEA}, new ItemStack[]{ClayTechItems.TEA_POWDER});
        this.registerRecipe(8, new ItemStack[]{ClayTechItems.CLAY_LEMON},
                new ItemStack[]{ClayTechItems.LEMON_POWDER});
        this.registerRecipe(8, new ItemStack[]{new ItemStack(Material.CHICKEN)},
                new ItemStack[]{ClayTechItems.RAW_CHICKEN_FOOT});
    }
}

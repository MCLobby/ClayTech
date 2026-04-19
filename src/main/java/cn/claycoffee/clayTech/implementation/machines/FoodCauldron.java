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

public class FoodCauldron extends ACraftingTable {
    public FoodCauldron(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                        ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_FOOD_CAULDRON");
    }

    @Override
    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.CAMPFIRE);
    }

    @Override
    public int getEnergyConsumption() {
        return 32;
    }

    @Override
    public void registerDefaultRecipes() {

        this.registerRecipe(30, ClayTechMachineRecipes.CHICKEN_FOOT, new ItemStack[]{ClayTechItems.CHICKEN_FOOT});
        this.registerRecipe(30, ClayTechMachineRecipes.SPICY_CHICKEN_BURGER,
                new ItemStack[]{ClayTechItems.SPICY_CHICKEN_BURGER});
        this.registerRecipe(30, ClayTechMachineRecipes.BABA_BURGER, new ItemStack[]{ClayTechItems.BABA_BURGER});
        this.registerRecipe(30, ClayTechMachineRecipes.CHOCOLATE, new ItemStack[]{ClayTechItems.CHOCOLATE});
        this.registerRecipe(30, ClayTechMachineRecipes.SNAIL_FOOD, new ItemStack[]{ClayTechItems.SNAIL_FOOD});
        this.registerRecipe(10, ClayTechMachineRecipes.HONEY_SWEET, new ItemStack[]{ClayTechItems.HONEY_SWEET});
        this.registerRecipe(15, ClayTechMachineRecipes.COOKED_SWEET_POTATO,
                new ItemStack[]{ClayTechItems.COOKED_SWEET_POTATO});

        this.registerRecipe(30, ClayTechMachineRecipes.CLAY_COFFEE, new ItemStack[]{ClayTechItems.CLAY_COFFEE});
        this.registerRecipe(30, ClayTechMachineRecipes.LEMON_POWDER_DRINK,
                new ItemStack[]{ClayTechItems.LEMON_POWDER_DRINK});
        this.registerRecipe(30, ClayTechMachineRecipes.TEA_DRINK, new ItemStack[]{ClayTechItems.TEA_DRINK});
        this.registerRecipe(30, ClayTechMachineRecipes.LEMON_TEA_DRINK,
                new ItemStack[]{ClayTechItems.LEMON_TEA_DRINK});
    }

    @Override
    public int getCapacity() {
        return 512;
    }
}
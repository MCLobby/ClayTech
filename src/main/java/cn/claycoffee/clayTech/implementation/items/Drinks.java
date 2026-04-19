package cn.claycoffee.clayTech.implementation.items;

import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechRecipeType;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;

public class Drinks {
    public Drinks() {
        SlimefunUtil.newResearch()
                .withId(950242)
                .withName(Lang.readResearchesText("CLAYTECH_DRINK_I"))
                .withCost(50)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DRINK)
                                .withItem(ClayTechItems.CLAY_COFFEE)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CAULDRON)
                                .withRecipe(new ItemStack[]{
                                        null, ClayTechItems.COCOA_BEAN, null,
                                        null, ClayTechItems.COCOA_BEAN, null,
                                        null, ClayTechItems.DRINK_BOTTLE, null
                                })
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DRINK)
                                .withItem(ClayTechItems.LEMON_POWDER_DRINK)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CAULDRON)
                                .withRecipe(new ItemStack[]{
                                        null, ClayTechItems.LEMON_POWDER, null,
                                        null, ClayTechItems.LEMON_POWDER, null,
                                        null, ClayTechItems.DRINK_BOTTLE, null
                                })
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DRINK)
                                .withItem(ClayTechItems.TEA_DRINK)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CAULDRON)
                                .withRecipe(new ItemStack[]{
                                        null, ClayTechItems.TEA_POWDER, null,
                                        null, ClayTechItems.TEA_POWDER, null,
                                        null, ClayTechItems.DRINK_BOTTLE, null
                                })
                                .build())
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_DRINK)
                                .withItem(ClayTechItems.LEMON_TEA_DRINK)
                                .withRecipeType(ClayTechRecipeType.CLAY_FOOD_CAULDRON)
                                .withRecipe(new ItemStack[]{
                                        null, ClayTechItems.TEA_POWDER, null,
                                        null, ClayTechItems.LEMON_POWDER, null,
                                        null, ClayTechItems.DRINK_BOTTLE, null
                                })
                                .build())
                .build();
    }
}

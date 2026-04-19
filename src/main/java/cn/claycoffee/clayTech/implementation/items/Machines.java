package cn.claycoffee.clayTech.implementation.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.implementation.machines.ClayElectricCopier;
import cn.claycoffee.clayTech.implementation.machines.CobbleStoneGenerator;
import cn.claycoffee.clayTech.implementation.machines.CraftingTable;
import cn.claycoffee.clayTech.implementation.machines.ElectricStoneCrusher;
import cn.claycoffee.clayTech.implementation.machines.ElectricWaterPump;
import cn.claycoffee.clayTech.implementation.machines.ElementExtracter;
import cn.claycoffee.clayTech.implementation.machines.ExperimentTableNormal;
import cn.claycoffee.clayTech.implementation.machines.FoodCauldron;
import cn.claycoffee.clayTech.implementation.machines.FoodChalkingMachine;
import cn.claycoffee.clayTech.implementation.machines.RocketAssemblingMachine;
import cn.claycoffee.clayTech.implementation.machines.RocketFuelGenerator;
import cn.claycoffee.clayTech.implementation.machines.RocketFuelInjector;
import cn.claycoffee.clayTech.implementation.machines.SpaceSuitOxygenInjector;
import cn.claycoffee.clayTech.implementation.machines.WitherKiller;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

/**
 * @Project: ClayTech
 * @Author: ClayCoffee
 * @Date: 2020/7/20 11:33
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class Machines {
    public Machines() {
        ItemStack[] ClayCrafingTable = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, new ItemStack(Material.CRAFTING_TABLE),
                SlimefunItems.BATTERY, ClayTechItems.MAGIC_CLAY, SlimefunItems.SMALL_CAPACITOR,
                ClayTechItems.MAGIC_CLAY};
        ItemStack[] ClayStoneCrusher = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.BATTERY, ClayTechItems.MAGIC_CLAY, SlimefunItems.SMALL_CAPACITOR,
                new ItemStack(Material.DISPENSER)};
        ItemStack[] ClayFoodCauldron = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, ClayTechItems.CLAY_CRAFTING_TABLE,
                SlimefunItems.BATTERY, ClayTechItems.MAGIC_CLAY, SlimefunItems.MEDIUM_CAPACITOR,
                ClayTechItems.MAGIC_CLAY};
        ItemStack[] ClayChalkingMachine = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRO_MAGNET,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY, ClayTechItems.CLAY_CRAFTING_TABLE,
                SlimefunItems.BATTERY, ClayTechItems.CLAY_STICK, SlimefunItems.MEDIUM_CAPACITOR,
                ClayTechItems.MAGIC_CLAY};
        ItemStack[] ClayElementExtracter = {ClayTechItems.BLISTERING_CORE, ClayTechItems.BLISTERING_CORE,
                ClayTechItems.BLISTERING_CORE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.PROGRAMMABLE_ANDROID,
                SlimefunItems.WITHER_PROOF_OBSIDIAN};
        ItemStack[] ClayExperimentTableBasic = {ClayTechItems.CLAY_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR,
                ClayTechItems.CLAY_ALLOY_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, ClayTechItems.CLAY_FOOD_CAULDRON,
                ClayTechItems.BLISTERING_CORE, ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.ELEMENT_UNIT,
                ClayTechItems.CLAY_ALLOY_INGOT};
        ItemStack[] ClayRocketAssemblingMachine = {SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.BLISTERING_CORE,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.MEDIUM_CAPACITOR,
                SlimefunItems.PROGRAMMABLE_ANDROID, SlimefunItems.MEDIUM_CAPACITOR};
        ItemStack[] ClayRocketFuelGenerator = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR, ClayTechItems.CLAY_FUSION_INGOT,
                SlimefunItems.SMALL_CAPACITOR};
        ItemStack[] ClayRocketFuelInjector = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.WITHER_PROOF_OBSIDIAN,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR, ClayTechItems.BLISTERING_CORE,
                SlimefunItems.SMALL_CAPACITOR};
        ItemStack[] ClaySpaceSuitOxygenInjector = {SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                ClayTechItems.CLAY_ROCKET_FUEL_INJECTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.SMALL_CAPACITOR, ClayTechItems.OXYGEN_TANK, SlimefunItems.SMALL_CAPACITOR};
        ItemStack[] ClayCobbleStoneGenerator = {SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.WATER_BUCKET),
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.LAVA_BUCKET), SlimefunItems.SMALL_CAPACITOR};
        ItemStack[] ClayElectricWaterPump = {SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.DISPENSER),
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.DISPENSER), SlimefunItems.SMALL_CAPACITOR};
        ItemStack[] ClayElectricCopier = {SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.COPYING_MODULE,
                SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.INK_MODULE,
                SlimefunItems.PROGRAMMABLE_ANDROID_MINER, ClayTechItems.INK_MODULE,
                SlimefunItems.SMALL_CAPACITOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.SMALL_CAPACITOR};
        ItemStack[] ClayWitherKiller = {ClayTechItems.BLISTERING_CORE, SlimefunItems.PROGRAMMABLE_ANDROID_2_BUTCHER, ClayTechItems.BLISTERING_CORE, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, SlimefunItems.WITHER_ASSEMBLER, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, SlimefunItems.CARBONADO_EDGED_CAPACITOR, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN};

        // 机器
        SlimefunItemStack craftingtable = new SlimefunItemStack("CLAYTECH_CRAFTING_TABLE",
                ClayTechItems.CLAY_CRAFTING_TABLE);
        SlimefunItemStack foodcauldron = new SlimefunItemStack("CLAYTECH_FOOD_CAULDRON", ClayTechItems.CLAY_FOOD_CAULDRON);
        SlimefunItemStack chalkingmachine = new SlimefunItemStack("CLAYTECH_FOOD_CHALKING_MACHINE",
                ClayTechItems.CLAY_FOOD_CHALKING_MACHINE);
        SlimefunItemStack elementextracter = new SlimefunItemStack("CLAYTECH_ELEMENT_EXTRACTER",
                ClayTechItems.CLAY_ELEMENT_EXTRACTER);
        SlimefunItemStack electricstonecrusher = new SlimefunItemStack("CLAYTECH_ELECTRIC_STONE_CRUSHER",
                ClayTechItems.CLAY_ELECTRIC_STONE_CRUSHER);
        SlimefunItemStack experimenttablebasic = new SlimefunItemStack("CLAYTECH_EXPERIMENT_TABLE_BASIC",
                ClayTechItems.CLAY_EXPERIMENT_TABLE_NORMAL);
        SlimefunItemStack rocketassemblingmachine = new SlimefunItemStack("CLAYTECH_ROCKET_ASSEMBLING_MACHINE",
                ClayTechItems.CLAY_ROCKET_ASSEMBLING_MACHINE);
        SlimefunItemStack rocketfuelgenerator = new SlimefunItemStack("CLAYTECH_ROCKET_FUEL_GENERATOR",
                ClayTechItems.CLAY_ROCKET_FUEL_GENERATOR);
        SlimefunItemStack rocketfuelinjector = new SlimefunItemStack("CLAYTECH_ROCKET_FUEL_INJECTOR",
                ClayTechItems.CLAY_ROCKET_FUEL_INJECTOR);
        SlimefunItemStack spacesuitoxygeninjector = new SlimefunItemStack("CLAYTECH_SPACESUIT_OXYGEN_INJECTOR",
                ClayTechItems.CLAY_SPACESUIT_OXYGEN_INJECTOR);
        SlimefunItemStack cobblestonegenerator = new SlimefunItemStack("CLAYTECH_COBBLESTONE_GENERATOR",
                ClayTechItems.CLAY_COBBLESTONE_GENERATOR);
        SlimefunItemStack electricwaterpump = new SlimefunItemStack("CLAYTECH_ELECTRIC_WATER_PUMP",
                ClayTechItems.CLAY_ELECTRIC_WATER_PUMP);
        SlimefunItemStack electriccopier = new SlimefunItemStack("CLAYTECH_ELECTRIC_COPIER",
                ClayTechItems.CLAY_ELECTRIC_COPIER);
        SlimefunItemStack witherkiller = new SlimefunItemStack("CLAYTECH_WITHER_KILLER",
                ClayTechItems.CLAY_WITHER_KILLER);

        new CraftingTable(ClayTechItems.C_MACHINES, craftingtable, "CLAYTECH_CRAFTING_TABLE",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayCrafingTable).register(ClayTech.getInstance());
        new ElectricStoneCrusher(ClayTechItems.C_MACHINES, electricstonecrusher, "CLAYTECH_ELECTRIC_STONE_CRUSHER",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayStoneCrusher).register(ClayTech.getInstance());
        new FoodCauldron(ClayTechItems.C_MACHINES, foodcauldron, "CLAYTECH_FOOD_CAULDRON",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayFoodCauldron).register(ClayTech.getInstance());
        new FoodChalkingMachine(ClayTechItems.C_MACHINES, chalkingmachine, "CLAYTECH_FOOD_CHALKING_MACHINE",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayChalkingMachine).register(ClayTech.getInstance());
        new ElementExtracter(ClayTechItems.C_MACHINES, elementextracter, "CLAYTECH_ELEMENT_EXTRACTER",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayElementExtracter).register(ClayTech.getInstance());
        new ExperimentTableNormal(ClayTechItems.C_MACHINES, experimenttablebasic, "CLAYTECH_EXPERIMENT_TABLE_BASIC",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayExperimentTableBasic).register(ClayTech.getInstance());
        new RocketAssemblingMachine(ClayTechItems.C_MACHINES, rocketassemblingmachine, "CLAYTECH_ROCKET_ASSEMBLING_MACHINE",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayRocketAssemblingMachine).register(ClayTech.getInstance());
        new RocketFuelGenerator(ClayTechItems.C_MACHINES, rocketfuelgenerator, "CLAYTECH_ROCKET_FUEL_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayRocketFuelGenerator).register(ClayTech.getInstance());
        new RocketFuelInjector(ClayTechItems.C_MACHINES, rocketfuelinjector,
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayRocketFuelInjector).register(ClayTech.getInstance());
        new SpaceSuitOxygenInjector(ClayTechItems.C_MACHINES, spacesuitoxygeninjector, "CLAYTECH_SPACESUIT_OXYGEN_INJECTOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClaySpaceSuitOxygenInjector).register(ClayTech.getInstance());
        new CobbleStoneGenerator(ClayTechItems.C_MACHINES, cobblestonegenerator, "CLAYTECH_COBBLESTONE_GENERATOR",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayCobbleStoneGenerator).register(ClayTech.getInstance());
        new ElectricWaterPump(ClayTechItems.C_MACHINES, electricwaterpump, "CLAYTECH_ELECTRIC_WATER_PUMP",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayElectricWaterPump).register(ClayTech.getInstance());
        new ClayElectricCopier(ClayTechItems.C_MACHINES, electriccopier, "CLAYTECH_ELECTRIC_COPIER",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayElectricCopier).register(ClayTech.getInstance());
        new WitherKiller(ClayTechItems.C_MACHINES, witherkiller, "CLAYTECH_WITHER_KILLER",
                RecipeType.ENHANCED_CRAFTING_TABLE, ClayWitherKiller).register(ClayTech.getInstance());


        SlimefunUtil.newResearch()
                .withId(950257)
                .withName(Lang.readResearchesText("CLAYTECH_ELECTRICMACHINE"))
                .withCost(65)
                .addItems(
                        ClayTechItems.CLAY_CRAFTING_TABLE,
                        ClayTechItems.CLAY_ELECTRIC_STONE_CRUSHER,
                        ClayTechItems.CLAY_FOOD_CAULDRON,
                        ClayTechItems.CLAY_FOOD_CHALKING_MACHINE)
                .build();

        SlimefunUtil.newResearch()
                .withId(950258)
                .withName(Lang.readResearchesText("CLAYTECH_ELEMENT_EXTRACTER_MACHINE"))
                .withCost(65)
                .addItem(ClayTechItems.CLAY_ELEMENT_EXTRACTER)
                .build();

        SlimefunUtil.newResearch()
                .withId(950259)
                .withName(Lang.readResearchesText("CLAYTECH_COBBLESTONE_GENERATOR"))
                .withCost(65)
                .addItem(ClayTechItems.CLAY_COBBLESTONE_GENERATOR)
                .build();

        SlimefunUtil.newResearch()
                .withId(950260)
                .withName(Lang.readResearchesText("CLAYTECH_ELECTRIC_WATER_PUMP"))
                .withCost(65)
                .addItem(ClayTechItems.CLAY_ELECTRIC_WATER_PUMP)
                .build();

        SlimefunUtil.newResearch()
                .withId(950261)
                .withName(Lang.readResearchesText("CLAYTECH_WITHER_KILLER"))
                .withCost(65)
                .addItem(ClayTechItems.CLAY_WITHER_KILLER)
                .build();
    }
}

package cn.claycoffee.clayTech;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.api.objects.enums.ArmorType;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

public class ClayTechMachineRecipes {
    public static final ItemStack[] BLIND_CORE = {
            new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL), new ItemStack(Material.INK_SAC),
            new ItemStack(Material.INK_SAC), ClayTechItems.MAGIC_CLAY, new ItemStack(Material.INK_SAC),
            new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL), new ItemStack(Material.INK_SAC)};
    public static final ItemStack[] BLIND_SWORD = new ItemStack[]{
            ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE,
            ClayTechItems.BLIND_CORE, new ItemStack(Material.NETHERITE_SWORD), ClayTechItems.BLIND_CORE,
            ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE};
    public static final ItemStack[] POISON_EYE = {
            new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
            new ItemStack(Material.SPIDER_EYE), ClayTechItems.MAGIC_CLAY, new ItemStack(Material.SPIDER_EYE),
            new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE)};
    public static final ItemStack[] POISON_CORE = {
            new ItemStack(Material.COAL_BLOCK), ClayTechItems.POISON_EYE, new ItemStack(Material.COAL_BLOCK),
            new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.FERMENTED_SPIDER_EYE),
            new ItemStack(Material.COAL_BLOCK), ClayTechItems.POISON_EYE, new ItemStack(Material.COAL_BLOCK)};
    public static final ItemStack[] CONFUSION_CORE = {new ItemStack(Material.PUFFERFISH),
            new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
            ClayTechItems.MAGIC_CLAY, new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
            new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH)};
    public static final ItemStack[] BLACK_ROCK_BLOCK = {
            new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
            new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
            new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN)};
    public static final ItemStack[] SLOWNESS_CORE = {
            ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK,
            ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.MAGIC_CLAY, ClayTechItems.BLACK_ROCK_BLOCK,
            ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK};
    public static final ItemStack[] ADVANCED_CONFUSION_CORE = {
            ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE,
            ClayTechItems.CONFUSION_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.CONFUSION_CORE,
            ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE};
    public static final ItemStack[] ADVANCED_POISON_CORE = {
            ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE,
            ClayTechItems.POISON_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.POISON_CORE,
            ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE};
    public static final ItemStack[] ADVANCED_SLOWNESS_CORE = {
            ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE,
            ClayTechItems.SLOWNESS_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.SLOWNESS_CORE,
            ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE};
    public static final ItemStack[] ADVANCED_BLIND_CORE = {
            ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE,
            ClayTechItems.BLIND_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.BLIND_CORE,
            ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE};
    public static final ItemStack[] FOUR_BOW = {
            ClayTechItems.ADVANCED_SLOWNESS_CORE, ClayTechItems.ADVANCED_POISON_CORE, ClayTechItems.ADVANCED_BLIND_CORE,
            ClayTechItems.ADVANCED_CONFUSION_CORE, new ItemStack(Material.BOW), ClayTechItems.ADVANCED_CONFUSION_CORE,
            ClayTechItems.ADVANCED_BLIND_CORE, ClayTechItems.ADVANCED_POISON_CORE, ClayTechItems.ADVANCED_SLOWNESS_CORE};
    public static final ItemStack[] POISON_SWORD = new ItemStack[]{
            ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE,
            ClayTechItems.POISON_CORE, new ItemStack(Material.NETHERITE_SWORD), ClayTechItems.POISON_CORE,
            ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE};
    public static final ItemStack[] ANTI_SLOWNESS_BOOTS = {
            ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE,
            ClayTechItems.SLOWNESS_CORE, new ItemStack(Material.IRON_BOOTS), ClayTechItems.SLOWNESS_CORE,
            ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE};
    public static final ItemStack[] TNT_EXPLOSION_CREATER = {
            new ItemStack(Material.TNT), ClayTechItems.MAGIC_CLAY, new ItemStack(Material.TNT),
            ClayTechItems.MAGIC_CLAY, new ItemStack(Material.DIAMOND_HOE), ClayTechItems.MAGIC_CLAY,
            new ItemStack(Material.TNT), new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.TNT)};
    // 食物
    public static final ItemStack[] CHICKEN_FOOT = {
            null, new ItemStack(Material.COAL), null,
            null, ClayTechItems.RAW_CHICKEN_FOOT, null,
            null, new ItemStack(Material.COAL), null};
    public static final ItemStack[] SPICY_CHICKEN_BURGER = {
            ClayTechItems.RAW_BREAD, ClayTechItems.RAW_VEGETABLE, ClayTechItems.RAW_BREAD,
            ClayTechItems.RAW_VEGETABLE, ClayTechItems.CHICKEN_FOOT, ClayTechItems.RAW_VEGETABLE,
            ClayTechItems.RAW_BREAD, ClayTechItems.MAGIC_CLAY, ClayTechItems.RAW_BREAD};
    public static final ItemStack[] BABA_BURGER = {
            new ItemStack(Material.COAL), ClayTechItems.RAW_BREAD, new ItemStack(Material.COAL),
            new ItemStack(Material.COAL), new ItemStack(Material.COAL), new ItemStack(Material.COAL),
            new ItemStack(Material.COAL), ClayTechItems.RAW_BREAD, new ItemStack(Material.COAL)};
    public static final ItemStack[] CHOCOLATE = {
            ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN,
            ClayTechItems.COCOA_BEAN, ClayTechItems.STARCH, ClayTechItems.COCOA_BEAN,
            ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN};
    public static final ItemStack[] SNAIL_FOOD = {
            ClayTechItems.FLOUR, ClayTechItems.FLOUR, ClayTechItems.FLOUR,
            ClayTechItems.FLOUR, ClayTechItems.SNAIL_HEALTHY, ClayTechItems.FLOUR,
            ClayTechItems.FLOUR, ClayTechItems.FLOUR, ClayTechItems.FLOUR};
    public static final ItemStack[] CLAY_COFFEE = {
            null, ClayTechItems.COCOA_BEAN, null,
            null, ClayTechItems.COCOA_BEAN, null,
            null, ClayTechItems.DRINK_BOTTLE, null};
    public static final ItemStack[] LEMON_POWDER_DRINK = {
            null, ClayTechItems.LEMON_POWDER, null,
            null, ClayTechItems.LEMON_POWDER, null,
            null, ClayTechItems.DRINK_BOTTLE, null};
    public static final ItemStack[] TEA_DRINK = {
            null, ClayTechItems.TEA_POWDER, null,
            null, ClayTechItems.TEA_POWDER, null,
            null, ClayTechItems.DRINK_BOTTLE, null};
    public static final ItemStack[] LEMON_TEA_DRINK = {
            null, ClayTechItems.TEA_POWDER, null,
            ClayTechItems.LEMON_POWDER, ClayTechItems.TEA_POWDER, ClayTechItems.LEMON_POWDER,
            null, ClayTechItems.DRINK_BOTTLE, null};
    public static final ItemStack[] TEA_POWDER = {
            null, null, null,
            null, ClayTechItems.RAW_TEA, null,
            null, null, null};
    public static final ItemStack[] LEMON_POWDER = {
            null, null, null,
            null, ClayTechItems.CLAY_LEMON, null,
            null, null, null};
    public static final ItemStack[] COOKED_SWEET_POTATO = {
            null, new ItemStack(Material.COAL), null,
            null, ClayTechItems.CLAY_SWEET_POTATO, null,
            null, new ItemStack(Material.COAL), null};
    public static final ItemStack[] ELEMENT_CARBON = {
            null, null, null,
            null, new ItemStack(Material.COAL, 8), null,
            null, null, null};
    public static final ItemStack[] ELEMENT_OXYGEN = {
            null, null, null,
            null, new ItemStack(Material.DIRT, 3), null,
            null, null, null};
    public static final ItemStack[] ELEMENT_SILICON = {
            null, null, null,
            null, new ItemStack(Material.SAND, 10), null,
            null, null, null};
    public static final ItemStack[] BLISTERING_CORE = {
            null, ClayTechItems.CLAY_FUSION_INGOT, null,
            null, SlimefunItems.BLISTERING_INGOT_3, null,
            null, ClayTechItems.CLAY_FUSION_INGOT, null};
    public static final ItemStack[] ELEMENT_UNIT = {
            SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
            SlimefunItems.DAMASCUS_STEEL_INGOT, null, SlimefunItems.DAMASCUS_STEEL_INGOT,
            SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT};
    public static final ItemStack[] ELECTRIC_MOTOR_8 = {
            null, null, null,
            null, SlimefunItems.ELECTRIC_MOTOR, null,
            null, null, null};
    public static final ItemStack[] REINFORCED_ALLOY_PICKAXE = {
            SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
            null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
            null, SlimefunItems.REINFORCED_ALLOY_INGOT, null};
    public static final ItemStack[] CLAY_FUSION_INGOT = {
            SlimefunItems.GOLD_12K, SlimefunItems.SYNTHETIC_EMERALD, SlimefunItems.SYNTHETIC_SAPPHIRE,
            SlimefunItems.REINFORCED_ALLOY_INGOT, ClayTechItems.MAGIC_CLAY, SlimefunItems.REINFORCED_ALLOY_INGOT,
            null, null, null};
    public static final ItemStack[] CLAY_ALLOY_INGOT = {
            SlimefunItems.REINFORCED_ALLOY_INGOT, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.CARBONADO,
            SlimefunItems.REDSTONE_ALLOY, ClayTechItems.MAGIC_CLAY, SlimefunItems.GOLD_24K,
            null, null, null};
    public static final ItemStack[] CLAY_ALLOY_PICKAXE = {
            ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.CLAY_ALLOY_INGOT,
            null, ClayTechItems.CLAY_ALLOY_INGOT, null,
            null, ClayTechItems.CLAY_ALLOY_INGOT, null};
    public static final ItemStack[] CLAY_ALLOY_HELMET = SlimefunUtil.getArmorsStack(ArmorType.HELMET, ClayTechItems.CLAY_ALLOY_INGOT);
    public static final ItemStack[] CLAY_ALLOY_CHESTPLATE = SlimefunUtil.getArmorsStack(ArmorType.CHESTPLATE, ClayTechItems.CLAY_ALLOY_INGOT);
    public static final ItemStack[] CLAY_ALLOY_LEGGINGS = SlimefunUtil.getArmorsStack(ArmorType.LEGGINGS, ClayTechItems.CLAY_ALLOY_INGOT);
    public static final ItemStack[] CLAY_ALLOY_BOOTS = SlimefunUtil.getArmorsStack(ArmorType.BOOTS, ClayTechItems.CLAY_ALLOY_INGOT);
    public static final ItemStack[] SILICON_INGOT = {
            ClayTechItems.ELEMENT_SILICON, ClayTechItems.ELEMENT_SILICON, ClayTechItems.ELEMENT_SILICON,
            ClayTechItems.ELEMENT_SILICON, ClayTechItems.ELEMENT_SILICON, null,
            null, null, null};
    public static final ItemStack[] MOTOR_CORE = {
            SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
            SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.ELECTRIC_MOTOR,
            SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.SILICON_INGOT, SlimefunItems.ELECTRIC_MOTOR};
    public static final ItemStack[] TEMPERATURE_RESISTANCE_OBSIDIAN = {
            SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN,
            SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.WITHER_PROOF_OBSIDIAN,
            SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN, SlimefunItems.WITHER_PROOF_OBSIDIAN};
    public static final ItemStack[] ROCKET_ENGINE_SHELL = {
            SlimefunItems.DAMASCUS_STEEL_INGOT, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
            ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.CLAY_FUSION_INGOT,
            SlimefunItems.DAMASCUS_STEEL_INGOT, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT};
    public static final ItemStack[] FUEL_TANK = {
            SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT,
            SlimefunItems.TIN_INGOT, ClayTechItems.MAGIC_CLAY, SlimefunItems.TIN_INGOT,
            SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT};
    public static final ItemStack[] ROCKET_ENGINE = {
            ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL,
            ClayTechItems.MOTOR_CORE, ClayTechItems.FUEL_TANK, ClayTechItems.MOTOR_CORE,
            ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK};
    public static final ItemStack[] ROCKET_ANTENNA = {
            SlimefunItems.DAMASCUS_STEEL_INGOT, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
            ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.COPPER_WIRE, ClayTechItems.CLAY_FUSION_INGOT,
            SlimefunItems.DAMASCUS_STEEL_INGOT, ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT};
    public static final ItemStack[] ROCKET_CPU = {
            ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.CLAY_FUSION_INGOT,
            ClayTechItems.ROCKET_ANTENNA, SlimefunItems.PROGRAMMABLE_ANDROID, ClayTechItems.ROCKET_ANTENNA,
            ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL};
    public static final ItemStack[] ROCKET_CONTROL_CORE = {
            ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK, ClayTechItems.ARTIFICIAL_GOLD_BLOCK,
            ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_ENGINE_SHELL,
            ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN};
    public static final ItemStack[] ROCKET_FUEL_TANK = {
            ClayTechItems.FUEL_TANK, ClayTechItems.FUEL_TANK, ClayTechItems.FUEL_TANK,
            ClayTechItems.FUEL_TANK, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.FUEL_TANK,
            ClayTechItems.FUEL_TANK, ClayTechItems.FUEL_TANK, ClayTechItems.FUEL_TANK};
    public static final ItemStack[] ROCKET_GLASS = {
            SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS,
            SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.STEEL_PLATE, SlimefunItems.WITHER_PROOF_GLASS,
            SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS, SlimefunItems.WITHER_PROOF_GLASS};
    public static final ItemStack[] ROCKET_STEEL_PLATE = {
            ClayTechItems.CLAY_FUSION_INGOT, null, ClayTechItems.CLAY_FUSION_INGOT,
            ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.CLAY_FUSION_INGOT,
            ClayTechItems.CLAY_FUSION_INGOT, null, ClayTechItems.CLAY_FUSION_INGOT};
    public static final ItemStack[] MIXED_ROCKET_FUEL = {
            ClayTechItems.CLAY_FUEL, ClayTechItems.CLAY_FUEL, ClayTechItems.CLAY_FUEL,
            new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.COAL_BLOCK),
            SlimefunItems.NETHER_ICE, SlimefunItems.NETHER_ICE, SlimefunItems.NETHER_ICE};
    public static final ItemStack[] ROCKET_1 = {
            ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_GLASS,
            ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_CONTROL_CORE, ClayTechItems.ROCKET_STEEL_PLATE,
            ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_ENGINE, ClayTechItems.ROCKET_STEEL_PLATE};
    public static final ItemStack[] ROCKET_2_ENGINE_SHELL = {
            ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL,
            ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL,
            ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE_SHELL};
    public static final ItemStack[] ROCKET_2_ENGINE = {
            ClayTechItems.ROCKET_2_ENGINE_SHELL, ClayTechItems.ROCKET_2_ENGINE_SHELL, ClayTechItems.ROCKET_2_ENGINE_SHELL,
            ClayTechItems.ROCKET_2_ENGINE_SHELL, ClayTechItems.ROCKET_ENGINE, ClayTechItems.ROCKET_2_ENGINE_SHELL,
            ClayTechItems.ROCKET_2_ENGINE_SHELL, ClayTechItems.ROCKET_2_ENGINE_SHELL, ClayTechItems.ROCKET_2_ENGINE_SHELL};
    public static final ItemStack[] ROCKET_2_CPU = {
            ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_CPU,
            ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_CPU,
            ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_CPU, ClayTechItems.ROCKET_CPU};
    public static final ItemStack[] ROCKET_2_CONTROL_CORE = {
            ClayTechItems.ROCKET_CONTROL_CORE, ClayTechItems.ROCKET_CONTROL_CORE, ClayTechItems.ROCKET_CONTROL_CORE,
            ClayTechItems.ROCKET_CONTROL_CORE, ClayTechItems.ROCKET_2_CPU, ClayTechItems.ROCKET_CONTROL_CORE,
            ClayTechItems.ROCKET_CONTROL_CORE, ClayTechItems.ROCKET_CONTROL_CORE, ClayTechItems.ROCKET_CONTROL_CORE};
    public static final ItemStack[] ROCKET_2_FUEL_TANK = {
            ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_FUEL_TANK,
            ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_FUEL_TANK,
            ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_FUEL_TANK, ClayTechItems.ROCKET_FUEL_TANK};
    public static final ItemStack[] ROCKET_2_GLASS = {
            ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_GLASS,
            ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_GLASS,
            ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_GLASS, ClayTechItems.ROCKET_GLASS};
    public static final ItemStack[] ROCKET_2_STEEL_PLATE = {
            ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE,
            ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE,
            ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE, ClayTechItems.ROCKET_STEEL_PLATE};
    public static final ItemStack[] ROCKET_2 = {
            ClayTechItems.ROCKET_2_GLASS, ClayTechItems.ROCKET_2_FUEL_TANK, ClayTechItems.ROCKET_2_GLASS,
            ClayTechItems.ROCKET_2_STEEL_PLATE, ClayTechItems.ROCKET_2_CONTROL_CORE, ClayTechItems.ROCKET_2_STEEL_PLATE,
            ClayTechItems.ROCKET_2_STEEL_PLATE, ClayTechItems.ROCKET_2_ENGINE_SHELL, ClayTechItems.ROCKET_2_STEEL_PLATE};
    public static final ItemStack[] OXYGEN_TANK = {
            SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT,
            SlimefunItems.TIN_INGOT, ClayTechItems.CLAY_STICK, SlimefunItems.TIN_INGOT,
            SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT, SlimefunItems.TIN_INGOT};
    public static final ItemStack[] SPACESUIT_OXYGEN_TANK = {
            ClayTechItems.OXYGEN_TANK, ClayTechItems.OXYGEN_TANK, ClayTechItems.OXYGEN_TANK,
            ClayTechItems.OXYGEN_TANK, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, ClayTechItems.OXYGEN_TANK,
            ClayTechItems.OXYGEN_TANK, ClayTechItems.OXYGEN_TANK, ClayTechItems.OXYGEN_TANK};
    public static final ItemStack[] SPACESUIT_HELMET = {
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, ClayTechItems.SPACESUIT_OXYGEN_TANK, SlimefunItems.PLASTIC_SHEET,
            null, null, null};
    public static final ItemStack[] SPACESUIT_CHESTPLATE = {
            SlimefunItems.PLASTIC_SHEET, ClayTechItems.SPACESUIT_OXYGEN_TANK, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET};
    public static final ItemStack[] SPACESUIT_LEGGINGS = {
            SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, ClayTechItems.SPACESUIT_OXYGEN_TANK, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, null, SlimefunItems.PLASTIC_SHEET};
    public static final ItemStack[] SPACESUIT_BOOTS = {
            SlimefunItems.PLASTIC_SHEET, null, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, ClayTechItems.SPACESUIT_OXYGEN_TANK, SlimefunItems.PLASTIC_SHEET,
            null, null, null};
    public static final ItemStack[] KREEP_INGOT = {
            null, null, null,
            null, ClayTechItems.KREEP_ROCK, null,
            null, null, null};
    public static final ItemStack[] RAW_CHICKEN_FOOT = {
            null, null, null,
            null, new ItemStack(Material.CHICKEN), null,
            null, null, null};
    public static final ItemStack[] PLANET_BASE_SIGNER = {
            SlimefunItems.STEEL_PLATE, ClayTechItems.ROCKET_ANTENNA, SlimefunItems.STEEL_PLATE,
            SlimefunItems.HARDENED_GLASS, SlimefunItems.PROGRAMMABLE_ANDROID, SlimefunItems.HARDENED_GLASS,
            ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN, SlimefunItems.ELECTRIC_MOTOR, ClayTechItems.TEMPERATURE_RESISTANCE_OBSIDIAN};
    public static final ItemStack[] TUBE = {
            SlimefunItems.PLASTIC_SHEET, null, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, null, SlimefunItems.PLASTIC_SHEET,
            SlimefunItems.PLASTIC_SHEET, null, SlimefunItems.PLASTIC_SHEET};
    public static final ItemStack[] OXYGEN_DISTRIBUTER = {
            SlimefunItems.REINFORCED_ALLOY_INGOT, ClayTechItems.TUBE, SlimefunItems.REINFORCED_ALLOY_INGOT,
            ClayTechItems.SPACESUIT_OXYGEN_TANK, ClayTechItems.SPACESUIT_OXYGEN_TANK, ClayTechItems.SPACESUIT_OXYGEN_TANK,
            ClayTechItems.KREEP_INGOT, ClayTechItems.SPACESUIT_OXYGEN_TANK, ClayTechItems.KREEP_INGOT};
    public static final ItemStack[] INK_MODULE = {
            new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC),
            new ItemStack(Material.INK_SAC), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.INK_SAC),
            new ItemStack(Material.INK_SAC), new ItemStack(Material.WRITABLE_BOOK), new ItemStack(Material.INK_SAC)};
    public static final ItemStack[] COPYING_MODULE = {
            new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.REDSTONE_BLOCK),
            SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.ELECTRIC_MOTOR,
            ClayTechItems.BLISTERING_CORE, SlimefunItems.ALUMINUM_BRONZE_INGOT, ClayTechItems.BLISTERING_CORE};

    public static final ItemStack[] COPPER_DUST_O = {
            null, null, null,
            null, ClayTechItems.COPPER_ORE, null,
            null, null, null};
    public static final ItemStack[] CLAY_FUSION_INGOT_O = {
            null, null, null,
            null, ClayTechItems.CLAY_FUSION_ORE, null,
            null, null, null};
    public static ItemStack @NotNull [] HONEY_SWEET = new ItemStack[]{
            new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES),
            new ItemStack(Material.SUGAR), new ItemStack(Material.HONEY_BOTTLE), new ItemStack(Material.SUGAR),
            new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR)};

    public static ItemStack @NotNull [] ROCKET_2_BLUEPRINT = new ItemStack[]{
            ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.CLAY_ALLOY_INGOT,
            ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.ROCKET, ClayTechItems.CLAY_ALLOY_INGOT,
            ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.CLAY_ALLOY_INGOT, ClayTechItems.CLAY_ALLOY_INGOT
    };
    static ItemStack newMotor;
    public static final ItemStack[] HIGHSPEED_RAILWAY = {
            null, null, null,
            null, new ItemStack(Material.POWERED_RAIL), null,
            null, newMotor, null};

    static {
        newMotor = ClayTechItems.ELECTRIC_MOTOR_8.clone();
        newMotor.setAmount(1);
    }
}

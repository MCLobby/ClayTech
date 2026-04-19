package cn.claycoffee.clayTech.implementation.items;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTechData;
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechMachineRecipes;
import cn.claycoffee.clayTech.ClayTechRecipeType;
import cn.claycoffee.clayTech.api.objects.Planet;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.PlanetUtil;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class Rockets {
    private static final int[] BORDER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 17, 18, 26, 27, 35, 36, 44, 45, 47, 48, 49,
            50, 51, 53};
    private static final int[] BORDER_2 = {10, 11, 12, 14, 15, 16};
    private static final ItemStack BORDER_ITEM = new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack OTHERBORDER_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static int currentPage = 1;

    public Rockets() {
        SlimefunUtil.newResearch()
                .withId(950272)
                .withName(Lang.readResearchesText("CLAYTECH_UNIVERSE_MACHINE_II"))
                .withCost(55)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_MACHINES)
                                .withItem(ClayTechItems.ROCKET_LAUNCHER)
                                .withRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                                .withRecipe(new ItemStack[]{
                                        ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT,
                                        ClayTechItems.CLAY_FUSION_INGOT, SlimefunItems.PROGRAMMABLE_ANDROID, ClayTechItems.CLAY_FUSION_INGOT,
                                        ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT, ClayTechItems.CLAY_FUSION_INGOT})
                                .withHandler(new BlockPlaceHandler(false) {
                                    @Override
                                    public void onPlayerPlace(@NotNull BlockPlaceEvent blockPlaceEvent) {
                                        StorageCacheUtils.setData(blockPlaceEvent.getBlock().getLocation(), "owner", blockPlaceEvent.getPlayer().getName());
                                    }

                                })
                                .withHandler((BlockUseHandler) ev -> {
                                    PlayerInteractEvent e = ev.getInteractEvent();
                                    if (e.hasBlock() && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                        Block b = e.getClickedBlock();
                                        SlimefunItem item = StorageCacheUtils.getSfItem(b.getLocation());
                                        if (item != null) {
                                            if (item.getId().equals(ClayTechItems.ROCKET_LAUNCHER.getItemId())) {
                                                Map<String, String> jbj = StorageCacheUtils.getBlock(b.getLocation()).getAllData();
                                                String ownerName = jbj.get("owner");
                                                if (ownerName.equalsIgnoreCase(e.getPlayer().getName())) {
                                                    Planet current = PlanetUtil.getPlanet(b.getWorld());
                                                    if (current == null) {
                                                        e.getPlayer().sendMessage(Lang.readGeneralText("NotAtAPlanet"));
                                                        return;
                                                    }
                                                    if (ClayTechData.CurrentPage.containsKey(b.getLocation())) {
                                                        currentPage = ClayTechData.CurrentPage.get(b.getLocation());
                                                    }
                                                    Inventory preset = Bukkit.createInventory(null, 54,
                                                            Lang.readMachinesText("ROCKET_LAUNCHER"));
                                                    if (!ClayTechData.RunningLaunchersG.containsKey(preset)) {
                                                        ClayTechData.RunningLaunchersG.put(preset, b);
                                                    }
                                                    preset.setItem(5, BORDER_ITEM);
                                                    for (int eachID : BORDER) {
                                                        preset.setItem(eachID, BORDER_ITEM);
                                                    }
                                                    for (int eachID : BORDER_2) {
                                                        preset.setItem(eachID, OTHERBORDER_ITEM);
                                                    }
                                                    preset.setItem(5, BORDER_ITEM);

                                                    preset = PlanetUtil.renderLauncherMenu(current, preset, currentPage);

                                                    e.getPlayer().openInventory(preset);
                                                } else {
                                                    e.getPlayer().sendMessage(Lang.readGeneralText("notOwner"));
                                                    e.setCancelled(true);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                })
                                .build())
                .build();

        SlimefunUtil.newItem()
                .withItemGroup(ClayTechItems.C_OTHER)
                .withItem(ClayTechItems.ROCKET)
                .withRecipeType(ClayTechRecipeType.CLAY_ROCKET_ASSEMBLING_MACHINE)
                .withRecipe(ClayTechMachineRecipes.ROCKET_1)
                .build();

        SlimefunUtil.newItem()
                .withItemGroup(ClayTechItems.C_OTHER)
                .withItem(ClayTechItems.ROCKET_2)
                .withRecipeType(ClayTechRecipeType.CLAY_ROCKET_ASSEMBLING_MACHINE)
                .withRecipe(ClayTechMachineRecipes.ROCKET_2)
                .build();
    }
}

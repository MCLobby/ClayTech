package cn.claycoffee.clayTech.implementation.items;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechData;
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.ClayTechMachineRecipes;
import cn.claycoffee.clayTech.ClayTechRecipeType;
import cn.claycoffee.clayTech.api.events.PlayerUseItemEvent;
import cn.claycoffee.clayTech.utils.DurabilityUtil;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffectItems {
    public EffectItems() {
        // rewrite
        SlimefunUtil.newResearch()
                .withId(950243)
                .withName(Lang.readResearchesText("CLAYTECH_EFFECT_ITEM_I"))
                .withCost(30)
                .addItem(
                        SlimefunUtil.newItem()
                                .withItemGroup(ClayTechItems.C_TOOLS)
                                .withItem(ClayTechItems.TNT_EXPLOSION_CREATER)
                                .withRecipeType(ClayTechRecipeType.CLAY_CRAFTING_TABLE)
                                .withRecipe(ClayTechMachineRecipes.TNT_EXPLOSION_CREATER)
                                .withHandlers((ItemUseHandler) e -> {
                                    Player player = e.getPlayer();
                                    Bukkit.getPluginManager().callEvent(new PlayerUseItemEvent(player, e.getItem()));
                                    boolean pass = false;
                                    Long md = ClayTechData.LastUseTNTCreaterTime.get(player.getUniqueId());
                                    if (md == null || (System.currentTimeMillis() >= md + 5000L)) {
                                        pass = true;
                                    }

                                    if (!pass) {
                                        player.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_CD"));
                                        return;
                                    }

                                    if (!player.getInventory().containsAtLeast(new ItemStack(Material.TNT), 1)) {
                                        player.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_NO_TNT"));
                                        return;
                                    }


                                    Location currentLoc = player.getLocation();
                                    Inventory inv = player.getInventory();
                                    ItemStack tnt = findFirstTNTStack(inv);
                                    if (tnt == null) {
                                        player.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_NO_TNT"));
                                        return;
                                    }

                                    ItemStack tool = player.getInventory().getItemInMainHand();
                                    SlimefunItem item = SlimefunItem.getByItem(tool);
                                    if (item == null || !item.getId().equals(ClayTechItems.TNT_EXPLOSION_CREATER.getItemId())) {
                                        player.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_NOT_RIGHT_TOOL"));
                                        return;
                                    }

                                    tnt.setAmount(tnt.getAmount() - 1);
                                    DurabilityUtil.setDurability(tool, DurabilityUtil.getDurability(tool) - 1);
                                    player.sendMessage(Lang.readGeneralText("TNT_EXPLOSION_CREATER_WAIT"));
                                    ClayTechData.LastUseTNTCreaterTime.put(player.getUniqueId(), System.currentTimeMillis());
                                    Bukkit.getScheduler().runTaskLater(ClayTech.getInstance(), () -> {
                                        player.getWorld().spawnEntity(currentLoc, EntityType.TNT);
                                    }, 100);
                                })
                                .build())
                .build();
    }

    public static @Nullable ItemStack findFirstTNTStack(@NotNull Inventory inv) {
        for (ItemStack item : inv.getContents()) {
            if (item == null) {
                continue;
            }

            if (item.getType() == Material.TNT && SlimefunUtils.isItemSimilar(item, new ItemStack(Material.TNT), true)) {
                return item;
            }
        }

        return null;
    }
}

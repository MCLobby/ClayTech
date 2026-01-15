package cn.claycoffee.clayTech.utils;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.api.events.PlayerDrinkEvent;
import cn.claycoffee.clayTech.api.events.PlayerEatEvent;
import cn.claycoffee.clayTech.api.events.PlayerWashEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class FoodUtil {
    public static void drink(@NotNull Player p, @NotNull ItemStack handingItem, ItemStack food, int incraseFoodLevel,
                             PotionEffect @NotNull [] effect) {
        if (handingItem.hasItemMeta()) {
            if (SlimefunUtils.isItemSimilar(food, handingItem, true)) {
                if (p.getFoodLevel() < 20) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
                    handingItem.setAmount(handingItem.getAmount() - 1);
                    p.getInventory().addItem(ClayTechItems.DIRTY_DRINK_BOTTLE);
                    if (p.getFoodLevel() + incraseFoodLevel > 20) {
                        p.setFoodLevel(20);
                        p.setSaturation(p.getSaturation() + (p.getFoodLevel() + incraseFoodLevel - 20));
                    } else {
                        p.setFoodLevel(p.getFoodLevel() + incraseFoodLevel);
                    }
                    for (PotionEffect pe : effect) {
                        p.addPotionEffect(pe);
                    }
                    p.sendMessage(Lang.readGeneralText("Drink_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerDrinkEvent(p, handingItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Drink_Message"));
                }
            }
        }
    }

    public static void drink(@NotNull Player p, @NotNull ItemStack handingItem, ItemStack food, int increaseFoodLevel) {
        drink(p, handingItem, food, increaseFoodLevel, new PotionEffect[]{});
    }

    public static void food(@NotNull Player p, @NotNull ItemStack handingItem, ItemStack food, int increaseFoodLevel,
                            PotionEffect @NotNull [] effect) {
        if (handingItem.hasItemMeta()) {
            if (SlimefunUtils.isItemSimilar(food, handingItem, true)) {
                if (food instanceof SlimefunItemStack sis) {
                    SlimefunItem item = sis.getItem();
                    if (item != null) {
                        Research research = item.getResearch();
                        if (research != null) {
                            Optional<PlayerProfile> profile = PlayerProfile.find(p);
                            if (profile.isPresent()) {
                                Set<Research> researches = profile.get().getResearches();
                                boolean researched = false;
                                for (Research r : researches) {
                                    if (r.getKey().equals(research.getKey())) {
                                        researched = true;
                                        break;
                                    }
                                }

                                if (!researched) {
                                    p.sendMessage(Lang.readGeneralText("Cant_Eat_Message"));
                                    return;
                                }
                            }
                        }
                    }
                }
                if (p.getFoodLevel() < 20) {
                    p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1.0F, 1.0F);
                    handingItem.setAmount(handingItem.getAmount() - 1);
                    if (p.getFoodLevel() + increaseFoodLevel > 20) {
                        p.setFoodLevel(20);
                        p.setSaturation(p.getSaturation() + (p.getFoodLevel() + increaseFoodLevel - 20));
                    } else {
                        p.setFoodLevel(p.getFoodLevel() + increaseFoodLevel);
                    }
                    for (PotionEffect pe : effect) {
                        p.addPotionEffect(pe);
                    }
                    p.sendMessage(Lang.readGeneralText("Eat_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerEatEvent(p, handingItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Eat_Message"));
                }
            }
        }
    }

    public static void food(@NotNull Player p, @NotNull ItemStack handingItem, ItemStack food, int incraseFoodLevel) {
        food(p, handingItem, food, incraseFoodLevel, new PotionEffect[]{});
    }

    public static void wash(@NotNull Player p, @NotNull ItemStack handingItem, ItemStack matchItem, ItemStack cleanItem) {
        try {
            if (SlimefunUtils.isItemSimilar(handingItem, matchItem, true)) {
                Inventory i = p.getInventory();
                if (i.contains(new ItemStack(Material.WATER_BUCKET))) {
                    int waterBucketIndex = i.first(new ItemStack(Material.WATER_BUCKET));
                    handingItem.setAmount(handingItem.getAmount() - 1);
                    i.setItem(waterBucketIndex, null);
                    i.addItem(new ItemStack(Material.BUCKET));
                    i.addItem(cleanItem);
                    p.sendMessage(Lang.readGeneralText("Wash_Message"));
                    Bukkit.getPluginManager().callEvent(new PlayerWashEvent(p, matchItem, cleanItem));
                } else {
                    p.sendMessage(Lang.readGeneralText("Cant_Wash_Message"));
                }
            }

        } catch (NullPointerException ignored) {
        }
    }

    public static void destroy(@NotNull Player p, @NotNull Block b, @NotNull Material targetBlock, @NotNull ItemStack dropItem, ItemStack disableItem,
                               int i, @NotNull BlockBreakEvent b1) {
        destroy(p, b, targetBlock, dropItem, disableItem, i, i, b1);
    }

    public static void destroy(@NotNull Player p, @NotNull Block b, @NotNull Material targetBlock, @NotNull ItemStack dropItem, ItemStack disableItem,
                               int from, int to, @NotNull BlockBreakEvent b1) {
        if (b.getType() == targetBlock) {
            if (!p.getInventory().getItemInMainHand().equals(new ItemStack(Material.AIR))) {
                if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)
                        || SlimefunUtils.isItemSimilar(disableItem, p.getInventory().getItemInMainHand(), true)) {
                    return;
                }
            }
            Random r = new Random();
            int random = r.nextInt(100);
            if (random >= from && random <= to) {
                p.getWorld().dropItemNaturally(p.getLocation(), dropItem);
                b1.setDropItems(false);
            }
        }
    }

    public static void fish(@NotNull PlayerFishEvent e, int from, int to, @NotNull ItemStack drop) {
        Random r = new Random();
        int random = r.nextInt(100);
        if (random >= from && random <= to) {
            //e.setCancelled(true);
        	if (e.getCaught() instanceof Item) {
                Item caughtItem = (Item) e.getCaught();
                
                caughtItem.setItemStack(drop);
            }
            //e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), drop);
        }
    }
}

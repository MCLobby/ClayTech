package cn.claycoffee.clayTech.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

public class EffectUtil {
    public static void EffectCheck(@NotNull Player d, @NotNull Player e) {
        ItemStack HandItem = d.getInventory().getItemInMainHand();
        SlimefunItem item = SlimefunItem.getByItem(HandItem);
        try {
            if (ObjectUtil.ExistsInList(Lang.readGeneralText("Blind_5_effect"), ItemStackUtil.getLore(HandItem))) {
                e.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 4));
            }
            if (ObjectUtil.ExistsInList(Lang.readGeneralText("Slowness_5_effect"), ItemStackUtil.getLore(HandItem))) {
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 4));
            }
            if (ObjectUtil.ExistsInList(Lang.readGeneralText("Confusion_5_effect"), ItemStackUtil.getLore(HandItem))) {
                e.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 4));
            }
            if (ObjectUtil.ExistsInList(Lang.readGeneralText("Poison_3_effect"), ItemStackUtil.getLore(HandItem))) {
                e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 2));
            }
        } catch (NullPointerException ignored) {
        }
        if (e.getInventory().getBoots() != null) {
            try {
                if (ObjectUtil.ExistsInList(Lang.readGeneralText("Anti_Slowness_3_effect"),
                        ItemStackUtil.getLore(e.getInventory().getBoots()))) {
                    d.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 2));
                }
            } catch (NullPointerException ignored) {
            }
        }
    }
}

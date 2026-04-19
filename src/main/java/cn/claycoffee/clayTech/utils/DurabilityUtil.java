package cn.claycoffee.clayTech.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import cn.claycoffee.clayTech.ClayTechItems;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public class DurabilityUtil {
    public static int getDurability(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return 0;
        }

        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return 0;
        }

        Integer durability = meta.getPersistentDataContainer().get(KeyUtil.DURABILITY, PersistentDataType.INTEGER);
        if (durability == null) {
            return 0;
        }

        return durability;
    }

    public static void setDurability(@Nullable ItemStack itemStack, int durability) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        if (durability <= 0) {
            itemStack.setType(Material.AIR);
            return;
        }

        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return;
        }

        meta.getPersistentDataContainer().set(KeyUtil.DURABILITY, PersistentDataType.INTEGER, durability);
        if (itemStack instanceof SlimefunItemStack slimefunItemStack) {
            if (slimefunItemStack.getItemId().equals(ClayTechItems.TNT_EXPLOSION_CREATER.getItemId())) {
                List<String> lore = meta.getLore();
                lore.set(3, ChatColor.translateAlternateColorCodes('&', Lang.readGeneralText("durability") + ": &6" + durability));
                meta.setLore(lore);
            }
        }
        itemStack.setItemMeta(meta);
    }
}

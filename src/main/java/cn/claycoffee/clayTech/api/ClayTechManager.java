package cn.claycoffee.clayTech.api;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.claycoffee.clayTech.ClayTechData;
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.Lang;

/**
 * the ClayTech API manager. 粘土科技API管理器
 */

// TODO: refactor
public class ClayTechManager {
    /**
     * @param item The item.物品.
     * @return the ItemStack is ClayTech item or not. 这个ItemStack是不是粘土科技物品.
     */
    public static boolean isClayTechItem(ItemStack item) {
        Field[] fields = ClayTechItems.class.getDeclaredFields();
        for (Field field : fields) {
            ItemStack is;
            try {
                is = (ItemStack) field.get(null);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                return false;
            }

            if (is != null && is.isSimilar(item)) {
                return true;
            } else if (isRocket(is) || isSpaceSuit(is) || isOxygenDistributer(is)) return true;
        }
        return false;
    }

    public static boolean isRocket(@Nullable ItemStack item) {
        if (item == null)
            return false;
        if (item.hasItemMeta()) {
            return item.getItemMeta().getDisplayName().startsWith(Lang.rocketPrefix);
        }
        return false;
    }

    public static boolean isSpaceSuit(@Nullable ItemStack item) {
        if (item == null)
            return false;
        if (item.hasItemMeta()) {
            return item.getItemMeta().getDisplayName().startsWith(Lang.spaceSuitPrefix);
        }
        return false;
    }

    public static boolean isOxygenDistributer(@Nullable ItemStack item) {
        if (item == null)
            return false;
        if (item.hasItemMeta()) {
            return item.getItemMeta().getDisplayName().startsWith(Lang.oxygenDistributerPrefix);
        }
        return false;
    }

    public static void allowSpaceTeleportOnce(@NotNull Player p) {
        ClayTechData.AllowSpaceTeleport.add(p.getUniqueId());
    }
}

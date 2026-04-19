package cn.claycoffee.clayTech.utils;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.api.ClayTechManager;

public class RocketUtil {
    public static int getFuel(ItemStack im) {
        if (ClayTechManager.isRocket(im)) {
            for (String str : ItemStackUtil.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Fuel"))) {
                    str = StringUtil.getRightStr(str, "§6");
                    str = StringUtil.getLeftStr(str, "§9");
                    return Integer.parseInt(str);
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static int getMaxFuel(ItemStack im) {
        if (ClayTechManager.isRocket(im)) {
            for (String str : ItemStackUtil.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Fuel"))) {
                    str = StringUtil.getRightStr(str, "§9/§a");
                    return Integer.parseInt(str);
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static void setFuel(@NotNull ItemStack im, int fuel) {
        if (ClayTechManager.isRocket(im)) {
            int i = 0;
            List<String> lore = ItemStackUtil.getLoreList(im);
            for (String str : lore) {
                if (str.startsWith(Lang.readGeneralText("Fuel"))) {
                    str = str.replaceAll("§6" + getFuel(im), "§6" + fuel);
                    lore.set(i, str);
                }
                i++;
            }
            ItemStackUtil.setLore(im, lore);
        }
    }

    public static int getOxygen(ItemStack im) {
        if (ClayTechManager.isSpaceSuit(im) || ClayTechManager.isOxygenDistributer(im)) {
            for (String str : ItemStackUtil.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Oxygen"))) {
                    str = StringUtil.getRightStr(str, "§6");
                    str = StringUtil.getLeftStr(str, "§9");
                    return Integer.parseInt(str);
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static void setOxygen(@NotNull ItemStack im, int oxygen) {
        if (ClayTechManager.isSpaceSuit(im) || ClayTechManager.isOxygenDistributer(im)) {
            int i = 0;
            List<String> lore = ItemStackUtil.getLoreList(im);
            for (String str : lore) {
                if (str.startsWith(Lang.readGeneralText("Oxygen"))) {
                    str = str.replaceAll("§6" + getOxygen(im), "§6" + oxygen);
                    lore.set(i, str);
                }
                i++;
            }
            ItemStackUtil.setLore(im, lore);
        }
    }

    public static int getMaxOxygen(ItemStack im) {
        if (ClayTechManager.isSpaceSuit(im) || ClayTechManager.isOxygenDistributer(im)) {
            for (String str : ItemStackUtil.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("Oxygen"))) {
                    str = StringUtil.getRightStr(str, "§9/§a");
                    return Integer.parseInt(str);
                }
            }
        } else {
            return -1;
        }
        return -1;
    }

    public static int getProtectLevel(ItemStack im) {
        if (ClayTechManager.isSpaceSuit(im)) {
            for (String str : ItemStackUtil.getLore(im)) {
                if (str.startsWith(Lang.readGeneralText("ProtectLevel"))) {
                    str = StringUtil.getRightStr(str, "§6");
                    return Integer.parseInt(str);
                }
            }
        } else {
            return -1;
        }
        return -1;
    }
}

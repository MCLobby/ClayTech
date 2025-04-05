package cn.claycoffee.clayTech.core.listeners;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.FoodUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FoodDropListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreakEvent(@NotNull BlockBreakEvent e) {
        if (!e.isCancelled()) {
            if (e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                FoodUtil.destroy(e.getPlayer(), e.getBlock(), Material.OAK_LEAVES,
                        ClayTechItems.CLAY_LEMON, new ItemStack(Material.SHEARS), 10, e);
                try {
                    // 这里放其他事件
                    FoodUtil.destroy(e.getPlayer(), e.getBlock(), Material.SHORT_GRASS,
                            ClayTechItems.DIRTY_TEA, new ItemStack(Material.SHEARS), 10, e);
                    FoodUtil.destroy(e.getPlayer(), e.getBlock(), Material.WHEAT,
                            ClayTechItems.FLOUR, new ItemStack(Material.SHEARS), 15, 20, e);
                    FoodUtil.destroy(e.getPlayer(), e.getBlock(), Material.POTATOES,
                            ClayTechItems.STARCH, new ItemStack(Material.SHEARS), 15, 20, e);
                    FoodUtil.destroy(e.getPlayer(), e.getBlock(), Material.POTATOES,
                            ClayTechItems.CLAY_SWEET_POTATO, new ItemStack(Material.SHEARS), 25, 30, e);
                    FoodUtil.destroy(e.getPlayer(), e.getBlock(), Material.SHORT_GRASS,
                            ClayTechItems.GREEN_GRASS, new ItemStack(Material.SHEARS), 31, 40, e);
                } catch (NullPointerException ignored) {
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerFishEvent(@NotNull PlayerFishEvent e) {
        if (e.getState() == State.CAUGHT_FISH) {
            FoodUtil.fish(e, 1, 10, ClayTechItems.SNAIL_HEALTHY);
            try {
                // 这里放其他食物/饮料8!!
                FoodUtil.fish(e, 11, 20, ClayTechItems.SNAIL_BAD);
                FoodUtil.fish(e, 85, 92, ClayTechItems.TUNA_FISH);
            } catch (NullPointerException ignored) {
            }

        }
    }
}

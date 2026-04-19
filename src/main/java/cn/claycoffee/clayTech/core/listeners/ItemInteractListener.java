package cn.claycoffee.clayTech.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.utils.ItemStackUtil;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.ObjectUtil;

public class ItemInteractListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BlockPlaceEvent(@NotNull BlockPlaceEvent e) {
        if (ObjectUtil.ExistsInList(Lang.readGeneralText("CantPlaceLore"), ItemStackUtil.getLore(e.getItemInHand()))) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Lang.readGeneralText("CantPlace"));
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void PlayerInteractEvent(@NotNull PlayerInteractEvent e) {
        if (e.hasItem()) {
            if (e.getItem().hasItemMeta()) {
                if (ObjectUtil.ExistsInList(Lang.readGeneralText("CantEat"), ItemStackUtil.getLore(e.getItem()))) {
                    e.getPlayer().sendMessage(Lang.readGeneralText("CantEatMessage"));
                    e.setCancelled(true);
                    return;
                }
                if (ObjectUtil.ExistsInList(Lang.readGeneralText("CantInteract"), ItemStackUtil.getLore(e.getItem()))
                        && e.hasBlock()) {
                    e.getPlayer().sendMessage(Lang.readGeneralText("CantInteractMessage"));
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
}

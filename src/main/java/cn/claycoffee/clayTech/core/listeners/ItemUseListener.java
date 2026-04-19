package cn.claycoffee.clayTech.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTechData;
import cn.claycoffee.clayTech.utils.Lang;

public class ItemUseListener implements Listener {
    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent e) {
        if (ClayTechData.CantBreakBlocks.contains(e.getBlock().getLocation())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Lang.readGeneralText("cantBreak"));
            return;
        }

        if (ClayTechData.InRocketPlayers.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(Lang.readGeneralText("cantBreak"));
            return;
        }
    }
}

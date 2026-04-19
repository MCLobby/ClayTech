package cn.claycoffee.clayTech.core.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.utils.EffectUtil;
import cn.claycoffee.clayTech.utils.ItemStackUtil;
import cn.claycoffee.clayTech.utils.ObjectUtil;

public class WeaponListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void EntityDamageByEntityEvent(@NotNull EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        if (e.getDamager().getType() == EntityType.ARROW) {
            try {
                Player d = (Player) ((Projectile) e.getDamager()).getShooter();
                Player p = (Player) e.getEntity();
                EffectUtil.EffectCheck(d, p);
            } catch (Exception ignored) {
            }
        } else {
            if (e.getDamager().getType() == EntityType.PLAYER && e.getEntity().getType() == EntityType.PLAYER) {
                Player d = (Player) e.getDamager();
                Player p = (Player) e.getEntity();
                EffectUtil.EffectCheck(d, p);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEntityEvent(@NotNull PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.PLAYER) {
            Player d = e.getPlayer();
            try {
                if (ObjectUtil.ExistsInList("§7钩子武器", ItemStackUtil.getLore(d.getInventory().getItemInMainHand()))) {
                    Player p = (Player) e.getRightClicked();
                    EffectUtil.EffectCheck(d, p);
                }
            } catch (Exception ignored) {

            }
        }
    }
}

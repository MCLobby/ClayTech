package cn.claycoffee.clayTech.core.listeners;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.FoodUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class FoodEatListener implements Listener {
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(@NotNull PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            Player p = e.getPlayer();
            if (e.hasItem()) {
                FoodUtil.drink(p, e.getItem(), ClayTechItems.CLAY_COFFEE, 5,
                        new PotionEffect[]{new PotionEffect(PotionEffectType.NIGHT_VISION, 3600, 1)});
                try {
                    // 这里放其他食物/饮料8!!
                    FoodUtil.drink(p, e.getItem(), ClayTechItems.LEMON_POWDER_DRINK, 6,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.NAUSEA, 200, 3)});
                    FoodUtil.drink(p, e.getItem(), ClayTechItems.TEA_DRINK, 6,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.RESISTANCE, 600, 2)});
                    FoodUtil.drink(p, e.getItem(), ClayTechItems.LEMON_TEA_DRINK, 12,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.RESISTANCE, 1200, 2)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.CHICKEN_FOOT, 8);
                    FoodUtil.food(p, e.getItem(), ClayTechItems.RAW_BREAD, 4);
                    FoodUtil.food(p, e.getItem(), ClayTechItems.RAW_VEGETABLE, 1);
                    FoodUtil.food(p, e.getItem(), ClayTechItems.CLAY_LEMON, 1,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.NAUSEA, 200, 3)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.SPICY_CHICKEN_BURGER, 15,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.getById(5), 400, 1)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.BABA_BURGER, -15,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.POISON, 3600, 5)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.SNAIL_BAD, -20,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.POISON, 8000, 9)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.CHOCOLATE, 15,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.RESISTANCE, 600, 2)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.SNAIL_FOOD, 12,
                            new PotionEffect[]{new PotionEffect(PotionEffectType.RESISTANCE, 600, 2)});
                    FoodUtil.food(p, e.getItem(), ClayTechItems.HONEY_SWEET, 8);
                    FoodUtil.food(p, e.getItem(), ClayTechItems.COOKED_SWEET_POTATO, 6);
                    FoodUtil.food(p, e.getItem(), ClayTechItems.TUNA_FISH, 6);
                    FoodUtil.food(p, e.getItem(), ClayTechItems.GREEN_GRASS, 1);
                } catch (NullPointerException ignored) {
                }
                FoodUtil.wash(p, e.getItem(), ClayTechItems.DIRTY_DRINK_BOTTLE, ClayTechItems.DRINK_BOTTLE);
                try {
                    // 这里放其他清理Event!!
                    FoodUtil.wash(p, e.getItem(), ClayTechItems.DIRTY_TEA, ClayTechItems.RAW_TEA);
                } catch (NullPointerException ignored) {
                }
            }
        }
    }
}

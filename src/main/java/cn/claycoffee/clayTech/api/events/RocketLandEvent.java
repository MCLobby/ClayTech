package cn.claycoffee.clayTech.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.api.objects.Planet;

/**
 * Called when a rocket land.在火箭着陆的时候触发.
 */
public class RocketLandEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player onRocketPlayer;
    private final Planet fromPlanet;
    private final Planet toPlanet;
    private final ItemStack rocket;

    public RocketLandEvent(Player p, Planet fromPlanet, Planet toPlanet, ItemStack rocket) {
        this.onRocketPlayer = p;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
        this.rocket = rocket;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return onRocketPlayer;
    }

    public ItemStack getRocket() {
        return rocket;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}

package cn.claycoffee.clayTech.core.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import cn.claycoffee.clayTech.core.listeners.AirLockListener;
import cn.claycoffee.clayTech.core.listeners.BeltListener;
import cn.claycoffee.clayTech.core.listeners.Debug;
import cn.claycoffee.clayTech.core.listeners.FoodDropListener;
import cn.claycoffee.clayTech.core.listeners.FoodEatListener;
import cn.claycoffee.clayTech.core.listeners.ItemInteractListener;
import cn.claycoffee.clayTech.core.listeners.ItemUseListener;
import cn.claycoffee.clayTech.core.listeners.PlanetBaseListener;
import cn.claycoffee.clayTech.core.listeners.PlanetListener;
import cn.claycoffee.clayTech.core.listeners.RailwayListener;
import cn.claycoffee.clayTech.core.listeners.RocketLauncherListener;
import cn.claycoffee.clayTech.core.listeners.WeaponListener;

public class ListenerManager {
    private final JavaPlugin plugin;
    private final List<Listener> listeners = new ArrayList<>();

    public ListenerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        listeners.add(new Debug()); // todo remove it in release version
        listeners.add(new AirLockListener());
        listeners.add(new BeltListener());
        listeners.add(new FoodDropListener());
        listeners.add(new FoodEatListener());
        listeners.add(new ItemInteractListener());
        listeners.add(new ItemUseListener());
        listeners.add(new PlanetBaseListener());
        listeners.add(new PlanetListener());
        listeners.add(new RailwayListener());
        listeners.add(new RocketLauncherListener());
        listeners.add(new WeaponListener());
    }

    public void setup() {
        for (Listener listener : this.listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, this.plugin);
        }
    }
}

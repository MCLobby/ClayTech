package cn.claycoffee.clayTech.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTech;

public class KeyUtil {
    public static @NotNull NamespacedKey DURABILITY = newKey("durability");

    public static @NotNull NamespacedKey newKey(@NotNull JavaPlugin plugin, @NotNull String key) {
        return new NamespacedKey(plugin, key);
    }

    public static @NotNull NamespacedKey newKey(@NotNull String namespace, @NotNull String key) {
        return new NamespacedKey(namespace, key);
    }

    public static @NotNull NamespacedKey newKey(@NotNull String key) {
        return new NamespacedKey(ClayTech.getInstance(), key);
    }
}

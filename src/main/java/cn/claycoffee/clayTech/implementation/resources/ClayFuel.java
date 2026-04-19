package cn.claycoffee.clayTech.implementation.resources;

import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.utils.KeyUtil;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.SlimefunUtil;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;

public class ClayFuel implements GEOResource {
    private final NamespacedKey key = KeyUtil.newKey("CLAY_FUEL");

    public ClayFuel() {
        SlimefunUtil.registerResource(this);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return key;
    }

    @Override
    public int getDefaultSupply(Environment environment, Biome biome) {
        if (environment == Environment.NORMAL && biome == Biome.THE_END) {
            return 12;
        } else if (environment == Environment.NORMAL) {
            return 4;
        } else if (environment == Environment.NETHER) {
            return 5;
        } else {
            return 0;
        }
    }

    @Override
    public int getMaxDeviation() {
        return 2;
    }

    @Override
    public @NotNull String getName() {
        return Lang.readResourcesText("CLAY_FUEL");
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ClayTechItems.CLAY_FUEL.clone();
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

}

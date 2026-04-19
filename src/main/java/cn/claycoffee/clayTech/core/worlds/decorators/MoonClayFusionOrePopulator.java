package cn.claycoffee.clayTech.core.worlds.decorators;

import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.scheduler.BukkitRunnable;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechItems;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

public class MoonClayFusionOrePopulator extends BlockPopulator {

    @Override
    public void populate(@Nonnull World world, @Nonnull Random random, @Nonnull Chunk source) {
        new BukkitRunnable() {

            @Override
            public void run() {
                int tryc = 3 + random.nextInt(2);
                for (int i = 0; i < tryc; i++) {
                    int x = random.nextInt(16);
                    int y = random.nextInt(100) + 1;
                    int z = random.nextInt(16);
                    int count = 0;
                    while (count <= 3 || random.nextDouble() < 0.85D && count <= 6) {
                        final int tx = x;
                        final int ty = y;
                        final int tz = z;
                        Block sourceb = source.getBlock(x, y, z);
                        if (sourceb.getType() == Material.STONE) {
                            if (StorageCacheUtils.hasBlock(sourceb.getLocation())) {
                                return;
                            }

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    sourceb.setType(ClayTechItems.CLAY_FUSION_ORE.getType(), false);
                                    if (Slimefun.getDatabaseManager().getBlockDataController().getBlockData(sourceb.getLocation()) != null) {
                                        cancel();
                                        return;
                                    }
                                    Slimefun.getDatabaseManager().getBlockDataController().createBlock(sourceb.getLocation(), ClayTechItems.CLAY_FUSION_ORE.getItemId());
                                }

                            }.runTask(ClayTech.getInstance());
                            count++;
                        }

                        switch (random.nextInt(6)) {
                            case 0 -> x = Math.min(x + 1, 15);
                            case 1 -> y = Math.min(y + 1, 15);
                            case 2 -> z = Math.min(z + 1, 15);
                            case 3 -> x = Math.max(x - 1, 0);
                            case 4 -> y = Math.max(y - 1, 1);
                            default -> z = Math.max(z - 1, 0);
                        }
                    }

                }

            }

        }.runTaskAsynchronously(ClayTech.getInstance());
    }

}

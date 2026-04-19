package cn.claycoffee.clayTech.core.worlds.decorators;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechItems;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

public class MoonKreepPopulator extends BlockPopulator {

    @Override
    public void populate(World world, @NotNull Random random, @NotNull Chunk source) {
        new BukkitRunnable() {

            @Override
            public void run() {
                int decreaseY = random.nextInt(9) + 1;
                int x = random.nextInt(16);
                int y = 30 - decreaseY;
                int z = random.nextInt(16);
                final int tx = x;
                final int ty = y;
                final int tz = z;
                int count = 0;
                Block sourceb = source.getBlock(x, y, z);
                while (count <= 3 || random.nextDouble() < 0.8D && count <= 6) {
                    if (sourceb.getType() == Material.STONE) {
                        if (StorageCacheUtils.hasBlock(sourceb.getLocation())) {
                            return;
                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                sourceb.setType(ClayTechItems.KREEP_ROCK.getType(), false);
                                if (Slimefun.getDatabaseManager().getBlockDataController().getBlockData(sourceb.getLocation()) != null) {
                                    cancel();
                                    return;
                                }
                                Slimefun.getDatabaseManager().getBlockDataController().createBlock(sourceb.getLocation(), ClayTechItems.KREEP_ROCK.getItemId());
                            }

                        }.runTask(ClayTech.getInstance());
                        count++;

                    }

                    switch (random.nextInt(6)) {
                        case 0 -> x = Math.min(x + 1, 15);
                        case 1 -> y = Math.min(y + 1, 20);
                        case 2 -> z = Math.min(z + 1, 15);
                        case 3 -> x = Math.max(x - 1, 0);
                        case 4 -> y = Math.max(y - 1, 30);
                        default -> z = Math.max(z - 1, 0);
                    }
                }

            }

        }.runTaskAsynchronously(ClayTech.getInstance());
    }

}

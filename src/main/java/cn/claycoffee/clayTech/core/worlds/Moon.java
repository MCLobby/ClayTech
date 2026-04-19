package cn.claycoffee.clayTech.core.worlds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockState;

import cn.claycoffee.clayTech.api.objects.Planet;
import cn.claycoffee.clayTech.core.worlds.decorators.MoonClayFusionOrePopulator;
import cn.claycoffee.clayTech.core.worlds.decorators.MoonCoalPopulator;
import cn.claycoffee.clayTech.core.worlds.decorators.MoonCopperOrePopulator;
import cn.claycoffee.clayTech.core.worlds.decorators.MoonDiamondPopulator;
import cn.claycoffee.clayTech.core.worlds.decorators.MoonKreepPopulator;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class Moon extends ChunkGenerator {
    private SimplexOctaveGenerator sog;

    public Moon() {
        new Planet("CMoon", new CustomItemStack(Material.GRAY_GLAZED_TERRACOTTA, Lang.readPlanetsText("Moon")), this,
                Environment.NORMAL, false, 0.165D, 384000, 1, true, 10).register();
    }

    private void generateWithFAWE(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        try (EditSession editSession = WorldEdit.getInstance().newEditSessionBuilder()
                .world(BukkitAdapter.adapt(world))
                .allowedRegionsEverywhere()
                .limitUnlimited()
                .changeSetNull()
                .fastMode(true)
                .build()) {
            
            if (sog == null) {
                sog = new SimplexOctaveGenerator(world.getSeed(), 1);
                sog.setScale(0.0025D);
            }
            
            int startX = chunkX * 16;
            int startZ = chunkZ * 16;
            
            // 缓存 BlockState
            BlockState bedrock = BukkitAdapter.adapt(Material.BEDROCK.createBlockData());
            BlockState stone = BukkitAdapter.adapt(Material.STONE.createBlockData());
            BlockState lava = BukkitAdapter.adapt(Material.LAVA.createBlockData());
            BlockState endStone = BukkitAdapter.adapt(Material.END_STONE.createBlockData());
            
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int worldX = startX + x;
                    int worldZ = startZ + z;
                    
                    // 生成噪声（修正：原代码使用 Math.random()，这里使用传入的 random）
                    double noiseValue = sog.noise(worldX, worldZ, random.nextDouble(), random.nextDouble());
                    int height = (int) (Math.pow(noiseValue, 2) * 40D + 90D);
                    
                    // 设置基岩
                    editSession.setBlock(BlockVector3.at(worldX, 0, worldZ), bedrock);
                    
                    // 填充石头
                    for (int y = 1; y < height; y++) {
                        editSession.setBlock(BlockVector3.at(worldX, y, worldZ), stone);
                    }
                    
                    // 处理顶部
                    if (height <= 66) {
                        for (int y = height; y <= 66; y++) {
                            editSession.setBlock(BlockVector3.at(worldX, y, worldZ), lava);
                        }
                    } else {
                        editSession.setBlock(BlockVector3.at(worldX, height, worldZ), endStone);
                    }
                    
                    // 设置生物群系
                    biome.setBiome(x, z, org.bukkit.block.Biome.THE_END);
                }
            }
            
            // 可选：立即刷新更改
            editSession.flushQueue();
            
        } catch (Exception e) {
            Bukkit.getLogger().severe("Error generating chunk at " + chunkX + ", " + chunkZ + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        
        // 确保在服务器主线程中执行（区块生成通常在主线程）
        generateWithFAWE(world, random, chunkX, chunkZ, biome);
        
        return chunkData;
    }

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> ret = new ArrayList<>();
        ret.add(new MoonCoalPopulator());
        ret.add(new MoonDiamondPopulator());
        // Check enabled ores then add its populator to the list
        ret.add(new MoonKreepPopulator());
        ret.add(new MoonCopperOrePopulator());
        ret.add(new MoonClayFusionOrePopulator());
        return ret;
    }
}

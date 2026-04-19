package cn.claycoffee.clayTech.core.worlds;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockState;

import cn.claycoffee.clayTech.api.objects.Planet;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class Mars extends ChunkGenerator {
    private SimplexOctaveGenerator sog;

    public Mars() {
        new Planet("CMars", new CustomItemStack(Material.YELLOW_GLAZED_TERRACOTTA, Lang.readPlanetsText("Mars")), this,
                Environment.NORMAL, true, 0.38D, 227000000, 0, false, 125).register();
    }

    
    private void generateDirectToWorld(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        try (EditSession editSession = WorldEdit.getInstance().newEditSessionBuilder()
                .world(BukkitAdapter.adapt(world))
                .allowedRegionsEverywhere()
                .limitUnlimited()
                .changeSetNull()
                .fastMode(true)
                .build()) {
            
            
            if (sog == null) {
                sog = new SimplexOctaveGenerator(world.getSeed(), 1);
                sog.setScale(0.00125D);
            }
            
            int startX = chunkX * 16;
            int startZ = chunkZ * 16;
            
            // 批量准备BlockState
            BlockState bedrockState = BukkitAdapter.adapt(Material.BEDROCK.createBlockData());
            BlockState stoneState = BukkitAdapter.adapt(Material.STONE.createBlockData());
            BlockState redSandstoneState = BukkitAdapter.adapt(Material.RED_SANDSTONE.createBlockData());
            BlockState lavaState = BukkitAdapter.adapt(Material.LAVA.createBlockData());
            
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int realX = startX + x;
                    int realZ = startZ + z;
                    
                    // 计算高度
                    double[] noises = new double[5];
                    noises[0] = sog.noise(realX, realZ, 5D, 12D);
                    noises[1] = sog.noise(realX, realZ, 128D, 6D);
                    noises[2] = sog.noise(realX, realZ, 8D, 128D);
                    noises[3] = sog.noise(realX, realZ, 256D, 16D);
                    noises[4] = sog.noise(realX, realZ, 64D, 188D);
                    Arrays.sort(noises);
                    
                    int finalHeight = (int) (noises[0] * 40D + 45D) + 7;
                    int posX = startX + x;
                    int posZ = startZ + z;
                    
                    // 批量设置这一列的所有方块
                    // 基岩
                    editSession.setBlock(BlockVector3.at(posX, 0, posZ), bedrockState);
                    
                    // 石头（使用循环，但FAWE内部会优化）
                    for (int y = 1; y < finalHeight - 1; y++) {
                        editSession.setBlock(BlockVector3.at(posX, y, posZ), stoneState);
                    }
                    
                    // 顶部材料
                    double c1 = random.nextDouble();
                    BlockState topState;
                    if (c1 >= 0.4) {
                        topState = BukkitAdapter.adapt(Material.GRAVEL.createBlockData());
                    } else if (c1 >= 0.3) {
                        topState = BukkitAdapter.adapt(Material.RED_SAND.createBlockData());
                    } else {
                        topState = redSandstoneState;
                    }
                    editSession.setBlock(BlockVector3.at(posX, finalHeight - 1, posZ), topState);
                    editSession.setBlock(BlockVector3.at(posX, finalHeight, posZ), redSandstoneState);
                    
                    // 填充空隙
                    if (finalHeight + 1 <= 57) {
                        for (int y = 57; y >= finalHeight + 1; y--) {
                            editSession.setBlock(BlockVector3.at(posX, y, posZ), lavaState);
                        }
                    } else if (finalHeight + 1 <= 60) {
                        for (int y = 60; y >= finalHeight + 1; y--) {
                            editSession.setBlock(BlockVector3.at(posX, y, posZ), redSandstoneState);
                        }
                    }
                    
                    // 设置生物群系
                    biome.setBiome(x, z, org.bukkit.block.Biome.DESERT);
                }
            }
            
            editSession.flushQueue();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int chunkX, int chunkZ, @NotNull BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        
        // 直接在世界中生成，不返回实际数据（性能最优）
        generateDirectToWorld(world, random, chunkX, chunkZ, biome);
        
        return chunkData;
    }
    
    
    /*
     * try (EditSession editSession = WorldEdit.getInstance().newEditSessionBuilder()
        		.world(BukkitAdapter.adapt(world))
                .allowedRegionsEverywhere() // 允许任何区域
                .limitUnlimited() // 解除限制
                .changeSetNull() // 不记录变化
                .fastMode(true) // 禁用快速模式（true = 无物理/粒子，false = 有物理/粒子）
                .build()) {
        	
        	
        }
     * 
     * 
     * */

    // todo 世界装饰器
    //	@Override
    //	public List<BlockPopulator> getDefaultPopulators(World world) {
    //	}
}

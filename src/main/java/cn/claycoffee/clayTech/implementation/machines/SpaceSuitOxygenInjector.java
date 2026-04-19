package cn.claycoffee.clayTech.implementation.machines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechData;
import cn.claycoffee.clayTech.api.ClayTechManager;
import cn.claycoffee.clayTech.api.events.InjectOxygenEvent;
import cn.claycoffee.clayTech.utils.ItemStackUtil;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.RocketUtil;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class SpaceSuitOxygenInjector extends SlimefunItem implements InventoryBlock, EnergyNetComponent {
    public static final int[] inputslots = new int[]{22};
    public static final int[] outputslots = new int[]{};
    private static final ItemStack HANDLED_PROGRESS_BAR = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ");
    private static final ItemStack HANDLING_PROGRESS_BAR = new CustomItemStack(Material.WHITE_STAINED_GLASS_PANE, " ");
    private static final int[] BORDER_A = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 15, 16, 17, 18, 19, 20, 24, 25, 26, 27,
            28, 29, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    private static final int[] BORDER_B = {12, 13, 14, 21, 23, 30, 31, 32};
    private static final ItemStack BORDER_A_ITEM = new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_B_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final List<Material> LEAVES = Arrays
            .asList(Material.OAK_LEAVES, Material.ACACIA_LEAVES, Material.BIRCH_LEAVES,
                    Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.SPRUCE_LEAVES,
                    Material.AZALEA_LEAVES, Material.FLOWERING_AZALEA_LEAVES, Material.MANGROVE_LEAVES,
                    Material.CHERRY_LEAVES);
    private static final Map<Block, ItemStack> item = new HashMap<>();
    public static @NotNull Map<Block, MachineRecipe> processing = new HashMap<>();
    public static @NotNull Map<Block, Integer> progress = new HashMap<>();
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public SpaceSuitOxygenInjector(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                                   ItemStack @NotNull [] recipe) {

        super(itemGroup, item, recipeType, recipe);
        createPreset(this, getInventoryTitle(), this::SetupMenu);
    }

    @Override
    public @NotNull EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int[] getInputSlots() {
        return inputslots;
    }

    @Override
    public int[] getOutputSlots() {
        return outputslots;
    }

    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_SPACESUIT_OXYGEN_INJECTOR");
    }

    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    public int getEnergyConsumption() {
        return 80;
    }

    public int getSpeed() {
        return 1;
    }

    public void SetupMenu(@NotNull BlockMenuPreset Preset) {
        Preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        Preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        Preset.addItem(5, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER_A) {
            Preset.addItem(eachID, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            Preset.addItem(eachID, BORDER_B_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        }
        Preset.addItem(4, ItemStackUtil.addLore(new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());

        Preset.addItem(5, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        Preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        for (int i : getOutputSlots()) {
            Preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, @Nullable ItemStack cursor,
                                       ClickAction action) {
                    return cursor == null || cursor.getType() == Material.AIR;
                }
            });
        }
        Preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
    }

    public MachineRecipe getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return getProcessing(b) != null;
    }

    public void registerRecipe(@NotNull MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks() / getSpeed());
        recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(@NotNull Block b, SlimefunItem sf, SlimefunBlockData data) {
                SpaceSuitOxygenInjector.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        }, new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Block block = blockBreakEvent.getBlock();
                if (isProcessing(block)) {
                    MachineRecipe recipe = getProcessing(block);
                    ItemStack input = recipe.getInput()[0];
                    World world = block.getWorld();
                    world.dropItemNaturally(block.getLocation(), input);
                    processing.remove(block);
                    progress.remove(block);
                    item.remove(block);
                }
            }
        });
    }

    protected void tick(@NotNull Block b) {
        BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
        // 机器正在处理
        if (isProcessing(b)) {
            // 剩余时间
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                // 还在处理
                ChestMenuUtils.updateProgressbar(inv, 4, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                        return;
                    }
                    removeCharge(b.getLocation(), getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else {
                    if (!LEAVES.contains(b.getLocation().add(0, 1, 0).getBlock().getType())) {
                        return;
                    }
                    progress.put(b, timeleft - 1);
                }
            } else {
                // 处理结束
                inv.replaceExistingItem(4, HANDLED_PROGRESS_BAR.clone());

                ItemStack spacesuit = item.get(b);
                RocketUtil.setOxygen(spacesuit, Math.min(RocketUtil.getOxygen(spacesuit) + 5, RocketUtil.getMaxOxygen(spacesuit)));
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        Bukkit.getPluginManager().callEvent(new InjectOxygenEvent(b, spacesuit));

                    }

                }.runTask(ClayTech.getInstance());
                inv.replaceExistingItem(22, spacesuit);
                ClayTechData.RunningInjectorsOxygen.remove(inv.toInventory());
                progress.remove(b);
                processing.remove(b);
                item.remove(b);
            }
        } else {
            // 没有在处理
            ItemStack spacesuit = inv.getItemInSlot(22);
            if (spacesuit != null) {
                if (spacesuit.getAmount() == 1 && (ClayTechManager.isSpaceSuit(spacesuit) || ClayTechManager.isOxygenDistributer(spacesuit))) {
                    if (isChargeable()) {
                        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                            return;
                        }
                        removeCharge(b.getLocation(), getEnergyConsumption());
                    }
                    if (!LEAVES.contains(b.getLocation().add(0, 1, 0).getBlock().getType())) {
                        return;
                    }
                    if (StorageCacheUtils.getSfItem(b.getLocation().add(0, 1, 0)) != null) {
                        return;
                    }
                    if (RocketUtil.getOxygen(spacesuit) >= RocketUtil.getMaxOxygen(spacesuit)) {
                        return;
                    }
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            b.getLocation().add(0, 1, 0).getBlock().setType(Material.AIR); // 消耗树叶
                        }

                    }.runTask(ClayTech.getInstance());
                    MachineRecipe oxygeninjectrecipe = new MachineRecipe(8, new ItemStack[]{spacesuit},
                            new ItemStack[]{});
                    item.put(b, spacesuit.clone());
                    inv.consumeItem(22, 1);
                    ClayTechData.RunningInjectorsOxygen.put(inv.toInventory(), b);
                    inv.replaceExistingItem(22, HANDLING_PROGRESS_BAR.clone());
                    processing.put(b, oxygeninjectrecipe);
                    progress.put(b, oxygeninjectrecipe.getTicks());
                }
            }
        }
    }
}

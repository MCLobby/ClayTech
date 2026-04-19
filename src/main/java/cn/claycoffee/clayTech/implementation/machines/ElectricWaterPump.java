package cn.claycoffee.clayTech.implementation.machines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.api.slimefun.ANewContainer;
import cn.claycoffee.clayTech.utils.ItemStackUtil;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class ElectricWaterPump extends ANewContainer {
    public static final int[] inputslots = new int[]{20};
    public static final int[] outputslots = new int[]{24};
    private static final int[] BORDER_A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 31, 17, 18, 26, 27, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44};
    private static final int[] BORDER_B = {10, 11, 12, 19, 21, 28, 29, 30, 14, 15, 16, 23, 25, 32, 33, 34};
    private static final ItemStack BORDER_A_ITEM = new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_B_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    public static @NotNull Map<Block, MachineRecipe> processing = new HashMap<>();
    public static @NotNull Map<Block, Integer> progress = new HashMap<>();
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public ElectricWaterPump(@NotNull ItemGroup category, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                             ItemStack @NotNull [] recipe) {

        super(category, item, recipeType, recipe);
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
        return Lang.readMachinesText("CLAY_ELECTRIC_WATER_PUMP");
    }

    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    public int getEnergyConsumption() {
        return 26;
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
        Preset.addItem(22, ItemStackUtil.addLore(new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "§9§l→"), " "),
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
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
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

    public void registerRecipe(int seconds, ItemStack @NotNull [] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(@NotNull Block b, SlimefunItem sf, SlimefunBlockData data) {
                ElectricWaterPump.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
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
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption())
                        return;
                    removeCharge(b.getLocation(), getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else
                    progress.put(b, timeleft - 1);
            } else {
                // 处理结束
                inv.replaceExistingItem(22,
                        ItemStackUtil.addLore(new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "§9§l→"), " "));

                for (ItemStack output : processing.get(b).getOutput()) {
                    if (output != null)
                        inv.pushItem(output.clone(), getOutputSlots());
                }

                progress.remove(b);
                processing.remove(b);
            }
        } else {
            // 没有在处理
            ItemStack input = inv.getItemInSlot(20);
            ItemStack output = inv.getItemInSlot(24);
            if (input != null && output == null) {
                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption())
                        return;
                    removeCharge(b.getLocation(), getEnergyConsumption());
                }
                Dispenser dp = (Dispenser) b.getBlockData();
                Block targetBlock = b.getRelative(dp.getFacing());
                if (targetBlock.getType() != Material.WATER)
                    return;

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        targetBlock.setType(Material.AIR);
                    }

                }.runTask(ClayTech.getInstance());

                inv.consumeItem(20, 1);

                MachineRecipe recipe = new MachineRecipe(5, new ItemStack[]{new ItemStack(Material.BUCKET)},
                        new ItemStack[]{new ItemStack(Material.WATER_BUCKET)});

                processing.put(b, recipe);
                progress.put(b, recipe.getTicks());
            }
        }
    }
}
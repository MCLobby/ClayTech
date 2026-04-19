package cn.claycoffee.clayTech.implementation.machines;

import java.util.ArrayList;
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
import cn.claycoffee.clayTech.ClayTechItems;
import cn.claycoffee.clayTech.api.ClayTechManager;
import cn.claycoffee.clayTech.api.events.RocketInjectFuelEvent;
import cn.claycoffee.clayTech.api.slimefun.ANewContainer;
import cn.claycoffee.clayTech.utils.Lang;
import cn.claycoffee.clayTech.utils.RocketUtil;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class RocketFuelInjector extends ANewContainer {
    public static final int[] inputSlots = new int[]{20, 24};
    public static final int[] outputSlots = new int[]{};
    private static final ItemStack HANDLED_PROGRESS_BAR = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "§9§l←", " ");
    private static final ItemStack HANDLING_PROGRESS_BAR = new CustomItemStack(Material.WHITE_STAINED_GLASS_PANE, " ");
    private static final int[] BORDER_A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 31, 17, 18, 26, 27, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44};
    private static final int[] BORDER_B = {10, 11, 12, 19, 21, 28, 29, 30, 14, 15, 16, 23, 25, 32, 33, 34};
    private static final ItemStack BORDER_A_ITEM = new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_B_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final Map<Block, ItemStack> item = new HashMap<>();
    private static final Map<Block, ItemStack> itemFuel = new HashMap<>();
    private static final Map<Block, Integer> time = new HashMap<>();
    protected final List<MachineRecipe> recipes = new ArrayList<>();

    public RocketFuelInjector(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType,
                              ItemStack @NotNull [] recipe) {

        super(itemGroup, item, recipeType, recipe);
        createPreset(this, getInventoryTitle(), this::constructMenu);

        addItemHandler(onBlockBreak());
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
        return inputSlots;
    }

    @Override
    public int[] getOutputSlots() {
        return outputSlots;
    }

    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_ROCKET_FUEL_INJECTOR");
    }

    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.REDSTONE_TORCH);
    }

    public int getEnergyConsumption() {
        return 64;
    }

    public void constructMenu(@NotNull BlockMenuPreset preset) {
        preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(5, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER_A) {
            preset.addItem(eachID, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            preset.addItem(eachID, BORDER_B_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, "§9§l←", " "),
                ChestMenuUtils.getEmptyClickHandler());

        preset.addItem(5, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

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
        preset.addItem(43, BORDER_A_ITEM.clone(), ChestMenuUtils.getEmptyClickHandler());
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
                RocketFuelInjector.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        }, new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                Block block = blockBreakEvent.getBlock();
                ItemStack rocket = item.get(block);
                if (rocket != null) {
                    World world = block.getWorld();
                    world.dropItemNaturally(block.getLocation(), rocket);
                    item.remove(block);
                    itemFuel.remove(block);
                    time.remove(block);
                    ClayTechData.RunningInjectors.remove(StorageCacheUtils.getMenu(block.getLocation()).toInventory());
                }
            }
        });
    }

    protected void tick(@NotNull Block b) {
        BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
        if (inv == null) {
            return;
        }
        // 机器正在处理;
        Integer operation = time.get(b);
        if (operation != null) {
            // 剩余时间
            int timeleft = operation;

            if (timeleft > 0) {
                // 还在处理
                ChestMenuUtils.updateProgressbar(inv, 22, timeleft, 8, getProgressBar());

                if (isChargeable()) {
                    if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                        return;
                    }
                    removeCharge(b.getLocation(), getEnergyConsumption());
                    time.put(b, timeleft - 1);
                } else {
                    time.put(b, timeleft - 1);
                }
            } else {
                // 处理结束
                inv.replaceExistingItem(22, HANDLED_PROGRESS_BAR.clone());

                ItemStack rocket = item.get(b);
                RocketUtil.setFuel(rocket, Math.min(RocketUtil.getFuel(rocket) + 5, RocketUtil.getMaxFuel(rocket)));
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        Bukkit.getPluginManager().callEvent(new RocketInjectFuelEvent(b, itemFuel.get(b), rocket));
                    }

                }.runTask(ClayTech.getInstance());
                inv.replaceExistingItem(20, rocket);
                ClayTechData.RunningInjectors.remove(inv.toInventory());
                item.remove(b);
                itemFuel.remove(b);
                time.remove(b);
            }
        } else {
            // 没有在处理
            ItemStack rocket = inv.getItemInSlot(20);
            ItemStack fuel = inv.getItemInSlot(24);
            if (rocket != null && fuel != null) {
                if (ClayTechManager.isRocket(rocket)
                        && SlimefunUtils.isItemSimilar(fuel, ClayTechItems.MIXED_ROCKET_FUEL, true)
                        && rocket.getAmount() == 1) {
                    if (isChargeable()) {
                        if (getCharge(b.getLocation()) < getEnergyConsumption())
                            return;
                        removeCharge(b.getLocation(), getEnergyConsumption());
                    }
                    if (RocketUtil.getFuel(rocket) == RocketUtil.getMaxFuel(rocket)) {
                        return;
                    }
                    ItemStack f = fuel.clone();
                    f.setAmount(1);
                    itemFuel.put(b, f);

                    inv.consumeItem(24, 1);
                    item.put(b, rocket.clone());
                    inv.consumeItem(20, 1);
                    ClayTechData.RunningInjectors.put(inv.toInventory(), b);
                    inv.replaceExistingItem(20, HANDLING_PROGRESS_BAR.clone());
                    time.put(b, 8);
                }
            }
        }
    }
}

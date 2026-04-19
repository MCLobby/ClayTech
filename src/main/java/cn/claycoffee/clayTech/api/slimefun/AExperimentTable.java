package cn.claycoffee.clayTech.api.slimefun;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public abstract class AExperimentTable extends AbstractMachine {
    public static final int[] inputSlots = new int[]{20, 21, 22, 23, 24};
    public static final int[] outputSlots = new int[]{40};
    private static final int[] BORDER_A = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 25, 26,
            27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 41, 52, 53};
    private static final int[] BORDER_B = {37, 38, 39, 41, 42, 43};
    private static final ItemStack FREE_STATE_ITEM = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_A_ITEM = new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDER_B_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));

    public AExperimentTable(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType,
                            ItemStack @NotNull [] recipe) {

        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int getProcessBarSlot() {
        return 4;
    }

    @Override
    public void constructMenu(@NotNull BlockMenuPreset preset) {
        preset.addItem(4, FREE_STATE_ITEM, ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER_A) {
            preset.addItem(eachID, BORDER_A_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            preset.addItem(eachID, BORDER_B_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(4, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(4, FREE_STATE_ITEM, ChestMenuUtils.getEmptyClickHandler());
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
    }

    @Override
    public int[] getInputSlots() {
        return inputSlots;
    }

    @Override
    public int[] getOutputSlots() {
        return outputSlots;
    }

    @Override
    public @Nullable MachineRecipe findNextRecipe(@NotNull BlockMenu inv) {
        MachineRecipe r = null;
        Map<Integer, Integer> found = new HashMap<>();
        int i;
        for (MachineRecipe recipe : recipes) {
            i = 0;
            for (ItemStack input : recipe.getInput()) {
                if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(inputSlots[i]), input, true)) {
                    // 如果该位置的物品符合某实验配方的对应位置物品
                    if (input != null) {
                        found.put(inputSlots[i], input.getAmount());
                    }
                }
                if (inv.getItemInSlot(inputSlots[i]) == input && input == null) {
                    found.put(i, 0);
                }
                if (i < 4) {
                    i++;
                } else
                    i = 0;
            }
            if (found.size() == 5) {
                r = recipe;
                break;
            } else {
                found.clear();
            }
        }

        if (r != null) {
            for (int slot : inputSlots) {
                inv.consumeItem(slot, found.get(slot));
            }
        }

        return r;
    }

    @Override
    protected void tick(@NotNull Block b) {
        BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
        if (inv == null) {
            return;
        }
        CraftingOperation currentOperation = getMachineProcessor().getOperation(b);

        if (currentOperation != null) {
            if (takeCharge(b.getLocation())) {

                if (!currentOperation.isFinished()) {
                    getMachineProcessor().updateProgressBar(inv, getProcessBarSlot(), currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(getProcessBarSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));

                    for (ItemStack output : currentOperation.getResults()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    getMachineProcessor().endOperation(b);
                }
            }
        } else {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                getMachineProcessor().startOperation(b, new CraftingOperation(next));
            }
        }
    }
}

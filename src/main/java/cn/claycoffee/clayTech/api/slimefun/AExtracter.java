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

public abstract class AExtracter extends AbstractMachine {
    public static final int[] inputSlots = new int[]{20};
    public static final int[] outputSlots = new int[]{24};
    private static final int[] BORDER = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49,
            50, 51, 52, 53, 13};
    private static final int[] BORDER_A = {10, 11, 12, 19, 21, 28, 29, 30, 14, 15, 16, 23, 25, 32, 33, 34};
    private static final int[] BORDER_B = {37, 38, 39, 41, 42, 43};
    private static final ItemStack BORDER_ITEM = new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDERA_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack BORDERB_ITEM = new CustomItemStack(Material.MAGENTA_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));

    public AExtracter(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
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
    @SuppressWarnings("deprecation")
    public void constructMenu(@NotNull BlockMenuPreset preset) {
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER) {
            preset.addItem(eachID, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_A) {
            preset.addItem(eachID, BORDERA_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_B) {
            preset.addItem(eachID, BORDERB_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(22, new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(31,
                new CustomItemStack(Material.OAK_SIGN, Lang.readMachinesText("ELEMENT_UNIT_DOWN")),
                ChestMenuUtils.getEmptyClickHandler());

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
    public @Nullable MachineRecipe findNextRecipe(@NotNull BlockMenu inv) {
        MachineRecipe r = null;
        Map<Integer, Integer> found = new HashMap<>();
        for (MachineRecipe recipe : recipes) {
            ItemStack input = recipe.getInput()[0];
            if (SlimefunUtils.isItemSimilar(inv.getItemInSlot(inputSlots[0]), input, true)) {
                if (input != null) {
                    found.put(inputSlots[0], input.getAmount());
                }
            }
            if (found.size() == recipe.getInput().length) {
                r = recipe;
                break;
            } else {
                found.clear();
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
                    getMachineProcessor().updateProgressBar(inv, 22, currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(22, new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, " "));

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

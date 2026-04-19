package cn.claycoffee.clayTech.api.slimefun;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.api.events.PlayerAssembleEvent;
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

@SuppressWarnings("deprecation")
public abstract class ARocketTable extends AbstractMachine {
    public static final int[] inputSlots = new int[]{11, 19, 20, 21, 28, 29, 30, 37, 38, 39};
    public static final int[] outputSlots = new int[]{34};
    private static final int[] BORDER = {0, 1, 3, 5, 6, 7, 8, 14, 15, 16, 17, 23, 41, 50, 51, 52, 53, 32};
    private static final int[] BORDER_IN = {9, 10, 12, 13, 18, 22, 27, 31, 36, 40, 45, 46, 47, 48, 49};
    private static final int[] BORDER_OUT = {24, 25, 26, 33, 35, 42, 43, 44};
    private static final ItemStack BORDER_ITEM = new CustomItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));
    private static final ItemStack OTHERBORDER_ITEM = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE,
            Lang.readMachinesText("SPLIT_LINE"));

    public ARocketTable(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                        ItemStack @NotNull [] recipe) {

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
    public void constructMenu(@NotNull BlockMenuPreset preset) {
        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        for (int eachID : BORDER) {
            preset.addItem(eachID, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_IN) {
            preset.addItem(eachID, OTHERBORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        for (int eachID : BORDER_OUT) {
            preset.addItem(eachID, OTHERBORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(4, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());

        preset.addItem(2, new CustomItemStack(Material.RED_STAINED_GLASS_PANE,
                Lang.readMachinesText("ROCKET_ASSEMBLING_BLUEPRINT")), ChestMenuUtils.getEmptyClickHandler());

        preset.addItem(5, BORDER_ITEM, ChestMenuUtils.getEmptyClickHandler());

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
        for (MachineRecipe recipe : recipes) {
            boolean found = true;
            for (int i = 0; i < getInputSlots().length; i++) {
                ItemStack input = recipe.getInput()[i];
                ItemStack existing = inv.getItemInSlot(getInputSlots()[i]);

                if (input == null && existing != null && existing.getType() != Material.AIR) {
                    found = false;
                    break;
                }

                if (input != null && (existing == null || existing.getType() == Material.AIR)) {
                    found = false;
                    break;
                }

                if (!SlimefunUtils.isItemSimilar(existing, input, true)) {
                    found = false;
                    break;
                }
            }

            for (int i = 0; i < getOutputSlots().length; i++) {
                ItemStack output = recipe.getOutput()[i];
                ItemStack existing = inv.getItemInSlot(getOutputSlots()[i]);

                if (existing == null) {
                    continue;
                }

                if (output.getAmount() + existing.getAmount() > output.getMaxStackSize()) {
                    found = false;
                    break;
                }

                if (!SlimefunUtils.isItemSimilar(existing, output, true, false)) {
                    found = false;
                    break;
                }
            }

            if (found) {
                ItemStack[] input = recipe.getInput();
                for (int i = 0; i < getInputSlots().length; i++) {
                    if (input[i] != null) {
                        inv.consumeItem(getInputSlots()[i], input[i].getAmount());
                    }
                }

                return recipe;
            }
        }

        return null;
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
                    getMachineProcessor().updateProgressBar(inv, 4, currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(4, new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, " "));

                    for (ItemStack output : currentOperation.getResults()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            Bukkit.getPluginManager()
                                    .callEvent(new PlayerAssembleEvent(b, getMachineProcessor().getOperation(b).getIngredients(), getMachineProcessor().getOperation(b).getResults()[0]));
                        }

                    }.runTask(ClayTech.getInstance());

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

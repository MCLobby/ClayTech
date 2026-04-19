package cn.claycoffee.clayTech.implementation.machines;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import cn.claycoffee.clayTech.api.slimefun.ANewContainer;
import cn.claycoffee.clayTech.utils.Lang;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

/**
 * @Project: ClayTech
 * @Author: ClayCoffee
 * @Date: 2020/7/20 11:35
 * @Email: 1020757140@qq.com
 * AGPL 3.0
 */

public class ClayElectricCopier extends ANewContainer {
    private static final Map<Block, Integer> mode = new HashMap<>();

    public ClayElectricCopier(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, String id, @NotNull RecipeType recipeType,
                              ItemStack @NotNull [] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }


    @Override
    public @NotNull String getInventoryTitle() {
        return Lang.readMachinesText("CLAY_ELECTRIC_COPIER");
    }

    @Override
    public @NotNull ItemStack getProgressBar() {
        return new ItemStack(Material.BOOK);
    }

    @Override
    public int getEnergyConsumption() {
        return 40;
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public @Nullable MachineRecipe findNextRecipe(BlockMenu inv) {
        return null;
    }

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
                    inv.replaceExistingItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                    inv.pushItem(getMachineProcessor().getOperation(b).getResults()[0], getOutputSlots());
                    getMachineProcessor().endOperation(b);
                }
            }
        } else {
            ItemStack input1 = inv.getItemInSlot(19);
            ItemStack input2 = inv.getItemInSlot(20);

            if (input1 == null || input2 == null) {
                return;
            }

            if (input1.getType() == Material.WRITTEN_BOOK && input2.getType() == Material.WRITABLE_BOOK) {
                ItemMeta meta1 = input1.getItemMeta();
                if (meta1 instanceof BookMeta bookMeta1) {
                    MachineRecipe recipe = new MachineRecipe(bookMeta1.getPageCount() * 4, new ItemStack[]{input2.clone()}, new ItemStack[]{input1.clone()});
                    inv.consumeItem(20, 1);
                    getMachineProcessor().startOperation(b, new CraftingOperation(recipe));
                }
            }

            if (input1.getType() == Material.WRITABLE_BOOK && input2.getType() == Material.WRITTEN_BOOK) {
                ItemMeta meta2 = input2.getItemMeta();
                if (meta2 instanceof BookMeta bookMeta2) {
                    MachineRecipe recipe = new MachineRecipe(bookMeta2.getPageCount() * 4, new ItemStack[]{input1.clone()}, new ItemStack[]{input2.clone()});
                    inv.consumeItem(19, 1);
                    getMachineProcessor().startOperation(b, new CraftingOperation(recipe));
                }
            }
        }
    }
}
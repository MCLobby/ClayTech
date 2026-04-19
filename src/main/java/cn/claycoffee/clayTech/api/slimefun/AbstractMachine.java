package cn.claycoffee.clayTech.api.slimefun;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class AbstractMachine extends AContainer {
    public AbstractMachine(@NotNull ItemGroup itemGroup, @NotNull SlimefunItemStack item, @NotNull RecipeType recipeType, ItemStack @NotNull [] recipe) {

        super(itemGroup, item, recipeType, recipe);

        createPreset(this, getInventoryTitle(), this::constructMenu);
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    public @NotNull String getInventoryTitle() {
        return getItemName();
    }

    public @NotNull String getMachineIdentifier() {
        return getId();
    }

    @Override
    public @NotNull EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public int getProcessBarSlot() {
        return 22;
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    @Override
    public abstract ItemStack getProgressBar();

    @Override
    public abstract int getEnergyConsumption();

    @Override
    public abstract int getCapacity();

    protected void tick(@NotNull Block b) {
        BlockMenu inv = StorageCacheUtils.getMenu(b.getLocation());
        CraftingOperation currentOperation = getMachineProcessor().getOperation(b);
        if (currentOperation != null) {
            if (this.takeCharge(b.getLocation())) {
                if (!currentOperation.isFinished()) {
                    getMachineProcessor().updateProgressBar(inv, getProcessBarSlot(), currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(getProcessBarSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                    ItemStack[] var4 = currentOperation.getResults();
                    int var5 = var4.length;

                    for (int var6 = 0; var6 < var5; ++var6) {
                        ItemStack output = var4[var6];
                        inv.pushItem(output.clone(), this.getOutputSlots());
                    }

                    getMachineProcessor().endOperation(b);
                }
            }
        } else {
            MachineRecipe next = this.findNextRecipe(inv);
            if (next != null) {
                currentOperation = new CraftingOperation(next);
                getMachineProcessor().startOperation(b, currentOperation);
                getMachineProcessor().updateProgressBar(inv, getProcessBarSlot(), currentOperation);
            }
        }

    }
}

package cn.claycoffee.clayTech.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.api.objects.NotPlaceableItem;
import cn.claycoffee.clayTech.api.objects.enums.ArmorType;
import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

public class SlimefunUtil {
    public static int id = 950530;

    public static @NotNull ItemBuilder newItem() {
        return new ItemBuilder();
    }

    public static @NotNull ResearchBuilder newResearch() {
        return new ResearchBuilder();
    }

    public static void registerItem(@NotNull ItemGroup itemGroup, @NotNull String id, SlimefunItemStack item, @NotNull String ResearchName, int cost, @NotNull RecipeType Recipetype, ItemStack @NotNull [] recipe, boolean registerResearch) {
        item = new SlimefunItemStack(id, item);
        SlimefunItem slimefunItem = new SlimefunItem(itemGroup, item, Recipetype, recipe);
        slimefunItem.setResearch(new Research(KeyUtil.newKey(ResearchName), id.hashCode(), ResearchName, cost));
        slimefunItem.register(ClayTech.getInstance());
    }

    public static void registerItem(@NotNull ItemGroup itemGroup, @NotNull String id, SlimefunItemStack item, @NotNull String ResearchName, int cost, @NotNull RecipeType Recipetype, ItemStack @NotNull [] recipe, boolean registerResearch, ItemHandler @NotNull [] handlers) {
        item = new SlimefunItemStack(id, item);
        SlimefunItem slimefunItem = new SlimefunItem(itemGroup, item, Recipetype, recipe);
        slimefunItem.setResearch(new Research(KeyUtil.newKey(ResearchName), id.hashCode(), ResearchName, cost));
        for (ItemHandler handler : handlers) {
            slimefunItem.addItemHandler(handler);
        }
        slimefunItem.register(ClayTech.getInstance());
    }

    public static void registerItem(@NotNull ItemGroup itemGroup, @NotNull String id, @NotNull ItemStack itemStack, @NotNull String ResearchName, int cost, @NotNull RecipeType Recipetype, ItemStack @NotNull [] recipe, boolean registerResearch) {
        registerItem(itemGroup, id, new SlimefunItemStack(id, itemStack), ResearchName, cost, Recipetype, recipe, registerResearch);
    }

    public static void registerArmors(@NotNull ItemGroup itemGroup, String nameprefix, ItemStack @NotNull [] ItemStack, String ResearchName,
                                      int cost, @NotNull RecipeType Recipetype, ItemStack MaterialStack, boolean registerResearch) {

        SlimefunItemStack HELMET = new SlimefunItemStack(nameprefix + "_HELMET", ItemStack[0]);
        SlimefunItem HELMET_I = new SlimefunItem(itemGroup, HELMET, Recipetype, getArmorsStack(ArmorType.HELMET, MaterialStack));
        HELMET_I.register(ClayTech.getInstance());
        SlimefunItemStack CHESTPLATE = new SlimefunItemStack(nameprefix + "_CHESTPLATE", ItemStack[1]);
        SlimefunItem CHESTPLATE_I = new SlimefunItem(itemGroup, CHESTPLATE, Recipetype,
                getArmorsStack(ArmorType.CHESTPLATE, MaterialStack));
        CHESTPLATE_I.register(ClayTech.getInstance());
        SlimefunItemStack LEGGINGS = new SlimefunItemStack(nameprefix + "_LEGGINGS", ItemStack[2]);
        SlimefunItem LEGGINGS_I = new SlimefunItem(itemGroup, LEGGINGS, Recipetype, getArmorsStack(ArmorType.LEGGINGS, MaterialStack));
        LEGGINGS_I.register(ClayTech.getInstance());
        SlimefunItemStack BOOTS = new SlimefunItemStack(nameprefix + "_BOOTS", ItemStack[3]);
        SlimefunItem BOOTS_I = new SlimefunItem(itemGroup, BOOTS, Recipetype, getArmorsStack(ArmorType.BOOTS, MaterialStack));
        BOOTS_I.register(ClayTech.getInstance());
    }

    public static ItemStack @NotNull [] getArmorsStack(@NotNull ArmorType type, ItemStack itemStack) {
        return switch (type) {
            case HELMET -> new ItemStack[]{
                    itemStack, itemStack, itemStack,
                    itemStack, null, itemStack,
                    null, null, null};
            case CHESTPLATE -> new ItemStack[]{
                    itemStack, null, itemStack,
                    itemStack, itemStack, itemStack,
                    itemStack, itemStack, itemStack};
            case LEGGINGS -> new ItemStack[]{
                    itemStack, itemStack, itemStack,
                    itemStack, null, itemStack,
                    itemStack, null, itemStack};
            case BOOTS -> new ItemStack[]{
                    null, null, null,
                    itemStack, null, itemStack,
                    itemStack, null, itemStack};
        };
    }

    public static void registerResource(@NotNull GEOResource res) {
        res.register();
        Slimefun.getRegistry().getGEOResources().add(res);
    }

    public static int getResearchId() {
        return id++;
    }

    public static class ItemBuilder {
        private final List<ItemHandler> handlers = new ArrayList<>();
        private ItemGroup itemGroup;
        private SlimefunItemStack itemStack;
        private RecipeType recipeType;
        private ItemStack[] recipe;
        private boolean placeable = true;

        public @NotNull ItemBuilder withPlaceable(boolean placeable) {
            this.placeable = placeable;
            return this;
        }

        public @NotNull ItemBuilder withItemGroup(ItemGroup itemGroup) {
            this.itemGroup = itemGroup;
            return this;
        }

        public @NotNull ItemBuilder withItem(SlimefunItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        public @NotNull ItemBuilder withItem(ItemStack itemStack) {
            if (itemStack instanceof SlimefunItemStack slimefunItemStack) {
                return withItem(slimefunItemStack);
            }

            return this;
        }

        public @NotNull ItemBuilder withRecipeType(RecipeType recipeType) {
            this.recipeType = recipeType;
            return this;
        }

        public @NotNull ItemBuilder withRecipe(ItemStack[] recipe) {
            this.recipe = recipe;
            return this;
        }

        public @NotNull ItemBuilder withHandler(ItemHandler handler) {
            this.handlers.add(handler);
            return this;
        }

        public @NotNull ItemBuilder withHandlers(ItemHandler... handlers) {
            this.handlers.addAll(Arrays.asList(handlers));
            return this;
        }

        public @NotNull SlimefunItem build() {
            if (itemGroup == null) {
                throw new IllegalArgumentException("Item Group cannot be null");
            }
            if (itemStack == null) {
                throw new IllegalArgumentException("Item Stack cannot be null");
            }
            if (recipeType == null) {
                throw new IllegalArgumentException("Recipe Type cannot be null");
            }
            if (recipe == null) {
                throw new IllegalArgumentException("Recipe cannot be null");
            }

            List<String> lore = ItemStackUtil.getLore(itemStack);
            if (lore != null && !lore.isEmpty()) {
                for (String line : lore) {
                    if (line.equals(Lang.readGeneralText("CantPlaceLore"))) {
                        placeable = false;
                        break;
                    }
                }
            }

            SlimefunItem slimefunItem;
            if (placeable) {
                slimefunItem = new SlimefunItem(itemGroup, itemStack, recipeType, recipe);
            } else {
                slimefunItem = new NotPlaceableItem(itemGroup, itemStack, recipeType, recipe);
            }
            if (slimefunItem == null) {
                throw new IllegalArgumentException("Slimefun Item cannot be null");
            }
            for (ItemHandler handler : handlers) {
                slimefunItem.addItemHandler(handler);
            }
            slimefunItem.register(ClayTech.getInstance());
            return slimefunItem;
        }

        public @NotNull SlimefunItem go() {
            return build();
        }
    }

    public static class ResearchBuilder {
        private final List<SlimefunItem> items = new ArrayList<>();
        private String name;
        private int id;
        private int cost;

        public @NotNull ResearchBuilder addItems(SlimefunItem... items) {
            this.items.addAll(Arrays.asList(items));
            return this;
        }

        public @NotNull ResearchBuilder addItems(SlimefunItemStack @NotNull ... items) {
            for (SlimefunItemStack item : items) {
                this.items.add(SlimefunItem.getByItem(item));
            }
            return this;
        }

        public @NotNull ResearchBuilder addItems(ItemStack @NotNull ... items) {
            for (ItemStack item : items) {
                this.items.add(SlimefunItem.getByItem(item));
            }
            return this;
        }

        public @NotNull ResearchBuilder addItem(SlimefunItem item) {
            this.items.add(item);
            return this;
        }

        public @NotNull ResearchBuilder addItem(SlimefunItemStack item) {
            this.items.add(SlimefunItem.getByItem(item));
            return this;
        }

        public @NotNull ResearchBuilder addItem(ItemStack item) {
            this.items.add(SlimefunItem.getByItem(item));
            return this;
        }

        public @NotNull ResearchBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public @NotNull ResearchBuilder withCost(int cost) {
            this.cost = cost;
            return this;
        }

        public @NotNull ResearchBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public @NotNull Research build() {
            if (items.isEmpty()) {
                throw new IllegalArgumentException("Items cannot be empty");
            }

            Research research = new Research(KeyUtil.newKey(String.valueOf(id)), id, name, cost);
            for (SlimefunItem item : items) {
                research.addItems(item);
            }
            research.register();
            return research;
        }

        public @NotNull Research go() {
            return build();
        }
    }
}

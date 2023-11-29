package net.ynov.createnuclear.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.ynov.createnuclear.CreateNuclear;
import net.ynov.createnuclear.tools.EnrichedFlintAndSteel;

public class ModItems {
    public static final Item URANIUM_POWDER = registerItem("uranium_powder", new Item(new FabricItemSettings()));
    public static final Item YELLOW_CAKE = registerItem("yellow_cake", new Item(new FabricItemSettings().food(new FoodProperties.Builder().nutrition(20).alwaysEat().saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.POISON, 6000, 25), 1.0F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 5), 1.0F).effect(new MobEffectInstance(MobEffects.HUNGER, 6000, 1000), 1.0F).effect(new MobEffectInstance(MobEffects.CONFUSION, 6000, 5), 1.0F).effect(new MobEffectInstance(MobEffects.WITHER, 6000, 8), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 5), 1.0F).build())));
    public static final Item YELLOW_CAKE_ENRICHED = registerItem("yellow_cake_enriched", new Item(new FabricItemSettings()));
    public static final Item COAL_DUST = registerItem("coal_dust", new Item(new FabricItemSettings()));
    public static final Item GRAPHENE = registerItem("graphene", new Item(new FabricItemSettings()));
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new FabricItemSettings()));
    public static final Item YELLOW_CAKE_NETHER_STAR = registerItem("yellow_cake_nether_star_wip", new Item(new FabricItemSettings()));
    public static final Item URANIUM_ROD = registerItem("uranium_rod", new Item(new FabricItemSettings()));
    public static final Item SEAL_URANIUM_ROD = registerItem("seal_uranium_rod", new Item(new FabricItemSettings()));
    public static final Item ENRICHED_FLINT_AND_STEEL = registerItem("enriched_flint_and_steel", new EnrichedFlintAndSteel(new Item.Properties().durability(100)));
    public static final Item GRAPHITE_ROD = registerItem("graphite_rod", new Item(new FabricItemSettings()));
    public static final Item RAW_URANIUM = registerItem("raw_uranium", new Item(new FabricItemSettings()));

    private static void AddItemToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.accept(URANIUM_POWDER, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(YELLOW_CAKE, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(YELLOW_CAKE_ENRICHED, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(COAL_DUST, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(GRAPHENE, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(STEEL_INGOT, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(YELLOW_CAKE_NETHER_STAR, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(URANIUM_ROD, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(SEAL_URANIUM_ROD, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(ENRICHED_FLINT_AND_STEEL, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(GRAPHITE_ROD, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        entries.accept(RAW_URANIUM,CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(CreateNuclear.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CreateNuclear.LOGGER.info("Registering mod items for " + CreateNuclear.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ModGroup.MAIN_KEY).register(ModItems::AddItemToIngredientItemGroup);
    }

}

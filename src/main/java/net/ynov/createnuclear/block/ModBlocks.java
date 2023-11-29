package net.ynov.createnuclear.block;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import io.github.fabricators_of_create.porting_lib.tags.Tags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.ynov.createnuclear.CreateNuclear;
import net.ynov.createnuclear.tools.UraniumFireBlock;

import static com.simibubi.create.Create.REGISTRATE;
import static net.minecraft.world.level.block.Blocks.litBlockEmission;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

    public static final Block DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", new DropExperienceBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f), UniformInt.of(2, 4)));
    public static final Block URANIUM_ORE = registerBlock("uranium_ore", new DropExperienceBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(4f), UniformInt.of(2, 4)));
    public static final Block URANIUM_RAW_BLOCK = registerBlock("uranium_raw_block", new DropExperienceBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(4f), UniformInt.of(2, 4)));
    public static final Block ENRICHED_SOUL_SOIL = registerBlock("enriched_soul_soil", new DropExperienceBlock(FabricBlockSettings.copyOf(Blocks.SOUL_SOIL).strength(4f), UniformInt.of(2, 4)));
    public static final Block ENRICHING_FIRE = registerBlockNoItem("enriching_fire", new UraniumFireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).replaceable().noCollission().instabreak().lightLevel(state -> 15).sound(SoundType.MOSS).pushReaction(PushReaction.DESTROY)));
    public static final Block ENRICHING_CAMPFIRE = registerBlock("enriching_campfire", new CampfireBlock(false, 5, BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(10)).noOcclusion().ignitedByLava()));


    // COBBLESTONE = register("cobblestone", new Block(Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));

    private static void AddBlockToBuildingBlockItemGroup(FabricItemGroupEntries entries) {
        entries.accept(DEEPSLATE_URANIUM_ORE, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
        entries.accept(URANIUM_ORE, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
        entries.accept(URANIUM_RAW_BLOCK, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
        entries.accept(ENRICHED_SOUL_SOIL, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
        entries.accept(ENRICHING_CAMPFIRE, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(CreateNuclear.MOD_ID, name), block);
    }
     private static Block registerBlockNoItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(CreateNuclear.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(CreateNuclear.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }


    public static void registerModBlocks() {
        CreateNuclear.LOGGER.info("Registering ModBlocks for " + CreateNuclear.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(ModBlocks::AddBlockToBuildingBlockItemGroup);
    }
}

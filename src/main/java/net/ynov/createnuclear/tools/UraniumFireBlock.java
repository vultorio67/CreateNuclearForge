package net.ynov.createnuclear.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.ynov.createnuclear.Tags.ModTag;
import net.ynov.createnuclear.block.ModBlocks;

public class UraniumFireBlock extends BaseFireBlock {
    public UraniumFireBlock(Properties properties) {
        super(properties, 3.0f);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState();
    }

    @Override
    protected boolean canBurn(BlockState pState) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return this.canSurvive(pState, pLevel, pCurrentPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos below = pos.below();
        return worldIn.getBlockState(below).isFaceSturdy(worldIn, pos, Direction.UP);
    }

    public static boolean canBePlacedAt(Level level, BlockPos pos, Direction direction) {
        BlockState blockState = level.getBlockState(pos);
        if (!blockState.isAir()) { return false; }
        return UraniumFireBlock.getState(level, pos).canSurvive(level, pos);
    }

    public static BlockState getState(BlockGetter reader, BlockPos pos) {
        BlockPos blockPos = pos.below();
        BlockState blockState = reader.getBlockState(blockPos);
        if (UraniumFireBlock.canSurviveOnBlock(blockState)) {
            return ModBlocks.ENRICHING_FIRE.defaultBlockState();
        }
        return Blocks.AIR.defaultBlockState();
    }

    public static boolean canSurviveOnBlock(BlockState state) {
        return state.is(ModTag.URANIUM_FIRE_BASE_BLOCKS);
    }

}

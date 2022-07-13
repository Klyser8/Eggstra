package com.github.klyser8.eggstra.block;

import com.github.klyser8.eggstra.registry.EggstraBlocks;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Map;

import static com.github.klyser8.eggstra.block.GoldenCakeBlock.playEffects;

public class GoldenCandleCakeBlock extends CandleCakeBlock {

    private static final Map<Block, GoldenCandleCakeBlock> BY_GOLDEN_CANDLE = Maps.newHashMap();

    public GoldenCandleCakeBlock(Block block, Properties properties) {
        super(Blocks.CANDLE_CAKE, properties);
        BY_GOLDEN_CANDLE.put(block, this);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos,
                                 Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is(Items.FLINT_AND_STEEL) || itemStack.is(Items.FIRE_CHARGE)) {
            return InteractionResult.PASS;
        }
        if (!(GoldenCandleCakeBlock.candleHit(blockHitResult) && player.getItemInHand(interactionHand).isEmpty() &&
                blockState.getValue(LIT))) {
            InteractionResult interactionResult = GoldenCakeBlock.eat(level, blockPos,
                    EggstraBlocks.GOLDEN_CAKE.get().defaultBlockState(), player);
            if (interactionResult.consumesAction()) {
                GoldenCandleCakeBlock.dropResources(blockState, level, blockPos);
            }
            return interactionResult;
        }
        GoldenCandleCakeBlock.extinguish(player, blockState, level, blockPos);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static boolean candleHit(BlockHitResult blockHitResult) {
        return blockHitResult.getLocation().y - (double)blockHitResult.getBlockPos().getY() > 0.5;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(EggstraBlocks.GOLDEN_CAKE.get());
    }

     public static BlockState byGoldenCandle(Block block) {
        return BY_GOLDEN_CANDLE.get(block).defaultBlockState();
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        for (int i = 0; i < randomSource.nextInt(4); i++) {
            playEffects(level, blockPos, randomSource, true);
        }
    }
}

package com.github.klyser8.eggstra.block;

import com.github.klyser8.eggstra.registry.EggstraParticles;
import com.github.klyser8.eggstra.registry.EggstraSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GoldenCakeBlock extends CakeBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public GoldenCakeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BITES, 0)
                .setValue(WATERLOGGED, false));
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        Item item = itemStack.getItem();
        if (itemStack.is(ItemTags.CANDLES) && blockState.getValue(BITES) == 0) {
            Block block = Block.byItem(item);
            if (block instanceof CandleBlock) {
                if (!player.isCreative()) {
                    itemStack.shrink(1);
                }
                level.playSound(null, blockPos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(blockPos, GoldenCandleCakeBlock.byGoldenCandle(block));
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                player.awardStat(Stats.ITEM_USED.get(item));
                return InteractionResult.SUCCESS;
            }
        }

        if (level.isClientSide) {
            if (eat(level, blockPos, blockState, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return eat(level, blockPos, blockState, player);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        for (int i = 0; i < randomSource.nextInt(4); i++) {
            playEffects(level, blockPos, randomSource, false);
        }
    }

    protected static InteractionResult eat(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player) {
        MobEffectInstance absorption = player.getEffect(MobEffects.ABSORPTION);
        if (absorption != null && absorption.getAmplifier() >= 4) {
            return InteractionResult.FAIL;
        }
        player.awardStat(Stats.EAT_CAKE_SLICE);
        if (player.getFoodData().needsFood()) {
            player.getFoodData().eat(6, 1.2F);
        }
        int effectMaxDuration = 3600;
        if (absorption != null) {
            player.removeEffect(MobEffects.ABSORPTION);
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,
                    Math.max(absorption.getDuration(), effectMaxDuration / 2), absorption.getAmplifier() + 1));
        } else {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, effectMaxDuration, 0));
        }
        for (int i = 0; i < 3; i++) {
            player.level.addParticle(ParticleTypes.HEART,
                    player.getRandomX(1), player.getRandomY(), player.getRandomZ(1), 0, 0, 0);
        }
        int i = blockState.getValue(BITES);
        levelAccessor.gameEvent(player, GameEvent.EAT, blockPos);
        if (i < 6) {
            levelAccessor.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
        } else {
            levelAccessor.removeBlock(blockPos, false);
            levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.getValue(WATERLOGGED)) {
            levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));
        }
        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Level levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        return defaultBlockState().setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState blockState) {
        if (blockState.getValue(WATERLOGGED)) {
            return Fluids.WATER.getSource(false);
        }
        return super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    public static void playEffects(Level level, BlockPos blockPos, RandomSource randomSource, boolean isCandleCake) {
        double sliceModifier = 0;
        if (!isCandleCake) {
            sliceModifier = (level.getBlockState(blockPos).getValue(BITES));
        }
        double x = (blockPos.getX() - 0.075 + sliceModifier / 7) + (Math.max(randomSource.nextDouble() * 1.1 - sliceModifier, 0));
        level.addParticle(EggstraParticles.GOLD_GLITTER.get(),
                x,
                blockPos.getY() + 0.4 + randomSource.nextDouble() / 5,
                blockPos.getZ() - 0.075 + (randomSource.nextDouble() * 1.1),
                0, 0, 0);
        level.playLocalSound(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, EggstraSounds.GOLDEN_CAKE_GLITTER.get(),
                SoundSource.BLOCKS, 0.25f, 0.75f + randomSource.nextFloat() / 2, true);
    }
}

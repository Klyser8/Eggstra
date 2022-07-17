package com.github.klyser8.eggstra.mixin;

import com.github.klyser8.eggstra.block.GoldenCandleCakeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public abstract class LightGoldenCakesMixin extends Item {

    public LightGoldenCakesMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "useOn", at = @At("TAIL"), cancellable = true)
    public void injectUseOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = useOnContext.getLevel();
        Player player = useOnContext.getPlayer();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (GoldenCandleCakeBlock.canLight(blockState)) {
            level.playSound(player, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0f, level.getRandom().nextFloat() * 0.4f + 0.8f);
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, true), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
            if (player != null) {
                useOnContext.getItemInHand().hurtAndBreak(1, player, p -> p.broadcastBreakEvent(useOnContext.getHand()));
            }
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide()));
        }
    }

}

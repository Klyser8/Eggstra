package com.github.klyser8.eggstra.mixin;

import com.github.klyser8.eggstra.registry.EggstraDispenserBehaviors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSourceImpl;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(DispenserBlock.class)
public class DispenserBlockCustomBehaviorMixin {

    @Inject(
            method = "dispenseFrom",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/DispenserBlock;getDispenseMethod(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/core/dispenser/DispenseItemBehavior;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void dispenseCustomBehavior(ServerLevel serverWorld, BlockPos pos, CallbackInfo ci,
                                        BlockSourceImpl blockSourceImpl,
                                        DispenserBlockEntity dispenserBlockEntity, int i, ItemStack itemStack) {
        // get custom behavior
        DispenseItemBehavior customBehavior = EggstraDispenserBehaviors.getCustomDispenserBehavior(itemStack);
        // check if custom behavior exists
        if(customBehavior != null) {
            // run custom behavior
            dispenserBlockEntity.setItem(i, customBehavior.dispense(blockSourceImpl, itemStack));
            ci.cancel();
        }
    }

}

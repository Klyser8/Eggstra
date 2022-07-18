package com.github.klyser8.eggstra.dispenser;

import com.github.klyser8.eggstra.entity.GoldenEggEntity;
import com.github.klyser8.eggstra.item.GoldenEggItem;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;

public class GoldenEggDispenserBehavior implements DispenseItemBehavior {

    @Override
    public ItemStack dispense(BlockSource blockSource, ItemStack itemStack) {
        ServerLevel level = blockSource.getLevel();
        Direction direction = blockSource.getBlockState().getValue(DispenserBlock.FACING);
        Position position = DispenserBlock.getDispensePosition(blockSource);
        GoldenEggItem.playServerThrowSound(level, (Vec3) position, SoundSource.BLOCKS);
        if (itemStack.getItem() instanceof GoldenEggItem) {
            GoldenEggEntity entity = new GoldenEggEntity(position.x(), position.y(), position.z(), level);
            entity.shoot(direction.getStepX(),
                    direction.getStepY() + 0.1f, direction.getStepZ(), 1.1f, 5.0f);
            level.addFreshEntity(entity);
        }
        itemStack.shrink(1);
        return itemStack;
    }

}

package com.github.klyser8.eggstra.item;

import com.github.klyser8.eggstra.entity.GoldenEggEntity;
import com.github.klyser8.eggstra.registry.EggstraSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GoldenEggItem extends Item {
    public GoldenEggItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (!level.isClientSide) {
            playServerThrowSound((ServerLevel) level, player.getEyePosition(), SoundSource.PLAYERS);
            GoldenEggEntity egg = new GoldenEggEntity(level, player);
            egg.setItem(itemStack);
            egg.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.0f, 1.0f);
            level.addFreshEntity(egg);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    public static void playServerThrowSound(ServerLevel level, Vec3 pos, SoundSource source) {
        level.playSound(null, pos.x, pos.y, pos.z, EggstraSounds.GOLDEN_EGG_THROW.get(),
                source, 0.5f, 0.8f + level.getRandom().nextFloat() / 2.5f);
    }

}

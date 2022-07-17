package com.github.klyser8.eggstra.mixin;

import com.github.klyser8.eggstra.registry.EggstraItems;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Random;

@Mixin(Chicken.class)
public abstract class ChickensLayGoldenEggsMixin extends Animal {

    private static final Random eggstra$RANDOM = new Random();

    protected ChickensLayGoldenEggsMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/animal/Chicken;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
    private ItemLike tryGoldenEgg(ItemLike originalItem) {
        double chance = 0.001; //0.1% chance
        if (hasEffect(MobEffects.DAMAGE_BOOST)) {
            chance *= 2.5;
        }
        if (eggstra$RANDOM.nextDouble() < chance) {
            return EggstraItems.GOLDEN_EGG.get();
        } else {
            return originalItem;
        }
    }

}

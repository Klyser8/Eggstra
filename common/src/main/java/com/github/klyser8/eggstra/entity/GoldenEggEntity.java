package com.github.klyser8.eggstra.entity;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.registry.*;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class GoldenEggEntity extends ThrowableItemProjectile {

    public GoldenEggEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public GoldenEggEntity(double d, double e, double f, Level level) {
        super(EggstraEntityTypes.GOLDEN_EGG_ENTITY.get(), d, e, f, level);
    }

    public GoldenEggEntity(Level level, LivingEntity livingEntity) {
        super(EggstraEntityTypes.GOLDEN_EGG_ENTITY.get(), livingEntity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return EggstraItems.GOLDEN_EGG.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        result.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 10.0f);
    }

    @Override
    public void tick() {
        super.tick();
        playParticleTrail();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (result.getType() == HitResult.Type.MISS) {
            return;
        }
        ObjectArrayList<ItemStack> loot = new ObjectArrayList<>();
        if (level instanceof ServerLevel serverLevel) {
            MinecraftServer server = level.getServer();
            if (server == null) {
                return;
            }
            serverLevel.playSound(null, blockPosition(), EggstraSounds.GOLDEN_EGG_CRASH.get(), SoundSource.PLAYERS, 0.8f, 0.8f + random.nextFloat() / 2.5f);
            serverLevel.sendParticles(EggstraParticles.GOLD_FLASH.get(), getX(), getY(), getZ(),
                    1, 0, 0, 0, 0);
            serverLevel.sendParticles(new ItemParticleOption(ParticleTypes.ITEM, EggstraItems.GOLDEN_EGG.get().getDefaultInstance()), getX(), getY(), getZ(), 20, 0.05, 0.05, 0.05, 0.15);
            if (level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                LootTable lootTable = server.getLootTables().get(new ResourceLocation(Eggstra.MOD_ID,
                        "entities/golden_egg"));
                //See LootContextParamSets to see what's required and what isn't
                LootContext.Builder builder = new LootContext.Builder(((ServerLevel) level))
                        .withParameter(LootContextParams.ORIGIN, position()).withParameter(LootContextParams.THIS_ENTITY, this)
                        .withParameter(LootContextParams.DAMAGE_SOURCE, DamageSource.GENERIC).withRandom(level.getRandom());
                loot = lootTable.getRandomItems(builder.create(LootContextParamSets.ENTITY));
                loot.forEach(stack -> {
                    ItemEntity itemEntity = new ItemEntity(level, getX(), getY(), getZ(), stack);
                    itemEntity.setDefaultPickUpDelay();
                    level.addFreshEntity(itemEntity);
                });
                int xpAmount = random.nextInt(3) + 2;
                for (int i = 0; i < xpAmount; i++) {
                    ExperienceOrb exp = new ExperienceOrb(level, getX(), getY(), getZ(), random.nextInt(5) + 4);
                    level.addFreshEntity(exp);
                }
            }
        }
        if (getOwner() != null) {
            Entity hitEntity = null;
            if (result instanceof EntityHitResult entityHitResult && entityHitResult.getEntity() != null) {
                hitEntity = entityHitResult.getEntity();
            }
            for (ItemStack itemStack : loot) {
                EggstraAdvancementTriggers.CRACK_GOLDEN_EGG.trigger((ServerPlayer) getOwner(), itemStack, hitEntity);
            }
        }
        discard();
    }

    private void playParticleTrail() {
        Vec3 vel = getDeltaMovement().normalize();
        if (level.isClientSide) {
            for (int i = 0; i < 2; ++i) {
                level.addParticle(EggstraParticles.GOLD_GLITTER.get(),
                        getRandomX(0.2) - vel.x,
                        getRandomY() - vel.y,
                        getRandomZ(0.2) - vel.z,
                        -vel.x / 2, (-vel.y + 0.2) / 2, -vel.z / 2);
            }
        }
    }

}

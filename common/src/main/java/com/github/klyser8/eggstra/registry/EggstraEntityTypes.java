package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.entity.GoldenEggEntity;
import com.github.klyser8.eggstra.platform.CommonPlatformHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

public class EggstraEntityTypes {

    public static void register() {}

    public static final Supplier<EntityType<GoldenEggEntity>> GOLDEN_EGG_ENTITY =
            CommonPlatformHelper.registerEntityType("golden_egg", GoldenEggEntity::new, MobCategory.MISC, 0.25f, 0.25f, 128);

}

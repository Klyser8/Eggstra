package com.github.klyser8.eggstra.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

public class CommonPlatformHelper {

    @ExpectPlatform
    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ParticleType<SimpleParticleType>> Supplier<T> registerParticle(String name, Supplier<T> particleType) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends StructureProcessorType<?>> Supplier<T> registerStructureProcessor(String name, Supplier<T> soundEvent) {
        throw new AssertionError();
    }

}

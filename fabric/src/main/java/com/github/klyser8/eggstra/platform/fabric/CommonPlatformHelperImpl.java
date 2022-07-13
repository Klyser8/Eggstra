package com.github.klyser8.eggstra.platform.fabric;

import com.github.klyser8.eggstra.Eggstra;
import com.ibm.icu.impl.Assert;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

public class CommonPlatformHelperImpl {

    public static <T extends Item> Supplier<T> registerItem(String name, Supplier<T> item) {
        T registry = Registry.register(Registry.ITEM, new ResourceLocation(Eggstra.MOD_ID, name), item.get());
        return () -> registry;
    }

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        T registry = Registry.register(Registry.BLOCK, new ResourceLocation(Eggstra.MOD_ID, name), block.get());
        return () -> registry;
    }

    public static <T extends ParticleType<SimpleParticleType>> Supplier<T> registerParticle(String name, Supplier<T> particleType) {
        T registry = Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(Eggstra.MOD_ID, name), particleType.get());
        return () -> registry;
    }

    public static <T extends SoundEvent> Supplier<T> registerSoundEvent(String name, Supplier<T> soundEvent) {
        T registry = Registry.register(Registry.SOUND_EVENT, new ResourceLocation(Eggstra.MOD_ID, name), soundEvent.get());
        return () -> registry;
    }

    public static <T extends StructureProcessorType<?>> Supplier<T> registerStructureProcessor(
            String name, Supplier<T> structureProcessor) {
        T registry = Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(Eggstra.MOD_ID, name), structureProcessor.get());
        return () -> registry;
    }

}

package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.client.particle.EggstraSimpleParticleType;
import com.github.klyser8.eggstra.client.particle.GoldGlitterParticle;
import com.github.klyser8.eggstra.platform.CommonPlatformHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

public class EggstraParticles {

    public static void register() {}

    public static final Supplier<SimpleParticleType> GOLD_GLITTER =
            CommonPlatformHelper.registerParticle("gold_glitter", () -> new EggstraSimpleParticleType(false));

}

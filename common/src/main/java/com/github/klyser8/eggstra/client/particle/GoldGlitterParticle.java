package com.github.klyser8.eggstra.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class GoldGlitterParticle extends SimpleAnimatedParticle {

    public static final int MAX_AGE = 6;

    public GoldGlitterParticle(ClientLevel clientLevel, double x, double y, double z, SpriteSet spriteSet) {
        super(clientLevel, x, y, z, spriteSet, -0.025f);
        xd = 0; //X velocity
        yd = 0; //Y velocity
        zd = 0; //Z velocity
        lifetime = MAX_AGE;
        setSpriteFromAge(spriteSet);
    }

    @Override
    public void tick() {
        if (age > 5) {
            setAlpha(1 - (float) age / lifetime / 2.0f);
        }
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientLevel level, double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new GoldGlitterParticle(level, x, y, z, spriteSet);
        }
    }
}

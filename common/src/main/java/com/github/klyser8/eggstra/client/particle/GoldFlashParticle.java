package com.github.klyser8.eggstra.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class GoldFlashParticle extends TextureSheetParticle {

    protected GoldFlashParticle(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f);
        this.lifetime = 4;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
        this.setAlpha(0.6f - ((float)this.age + partialTicks - 1.0f) * 0.25f * 0.5f);
        super.render(buffer, renderInfo, partialTicks);
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return 3f * Mth.sin(((float)this.age + scaleFactor - 1.0f) * 0.25f * (float)Math.PI);
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            GoldFlashParticle overlayParticle = new GoldFlashParticle(level, x, y, z);
            overlayParticle.pickSprite(this.sprite);
            return overlayParticle;
        }
    }
}

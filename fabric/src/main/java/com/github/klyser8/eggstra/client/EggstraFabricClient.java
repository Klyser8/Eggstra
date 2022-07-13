package com.github.klyser8.eggstra.client;

import com.github.klyser8.eggstra.client.particle.GoldGlitterParticle;
import com.github.klyser8.eggstra.registry.EggstraParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class EggstraFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerParticleFactories();
    }

    private void registerParticleFactories() {
        ParticleFactoryRegistry.getInstance().register(EggstraParticles.GOLD_GLITTER.get(), GoldGlitterParticle.Factory::new);
    }
}

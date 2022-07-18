package com.github.klyser8.eggstra.fabric.client;

import com.github.klyser8.eggstra.client.EggstraClient;
import com.github.klyser8.eggstra.client.particle.GoldFlashParticle;
import com.github.klyser8.eggstra.client.particle.GoldGlitterParticle;
import com.github.klyser8.eggstra.registry.EggstraParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class EggstraFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EggstraClient.init();
        registerParticleFactories();
    }

    private void registerParticleFactories() {
        ParticleFactoryRegistry.getInstance().register(EggstraParticles.GOLD_GLITTER.get(), GoldGlitterParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(EggstraParticles.GOLD_FLASH.get(), GoldFlashParticle.Factory::new);
    }
}

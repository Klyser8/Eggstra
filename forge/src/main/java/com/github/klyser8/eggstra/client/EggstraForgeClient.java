package com.github.klyser8.eggstra.client;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.client.particle.GoldGlitterParticle;
import com.github.klyser8.eggstra.registry.EggstraParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EggstraForgeClient {

    public static void subscribeClientEvents() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(EggstraForgeClient::onParticleSetup);
    }

    private static void onParticleSetup(RegisterParticleProvidersEvent event) {
        event.register(EggstraParticles.GOLD_GLITTER.get(), GoldGlitterParticle.Factory::new);
    }



}

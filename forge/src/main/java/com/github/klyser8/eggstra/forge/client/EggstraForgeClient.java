package com.github.klyser8.eggstra.forge.client;

import com.github.klyser8.eggstra.client.EggstraClient;
import com.github.klyser8.eggstra.client.particle.GoldFlashParticle;
import com.github.klyser8.eggstra.client.particle.GoldGlitterParticle;
import com.github.klyser8.eggstra.entity.GoldenEggEntity;
import com.github.klyser8.eggstra.registry.EggstraEntityTypes;
import com.github.klyser8.eggstra.registry.EggstraParticles;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EggstraForgeClient {

    /**
     * When registering entity renderers, Forge requires you to subscribe to the
     * RegisterParticleProvidersEvent event, unlike Fabric. REMEMBER!
     * TODO due to the code in {@link EggstraClient}, there's unwanted repetition. Fix!
     */
    public static void subscribeClientEvents() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(EggstraForgeClient::onParticleSetup);
        modEventBus.addListener(EggstraForgeClient::onRenderersSetup);
    }

    private static void onParticleSetup(RegisterParticleProvidersEvent event) {
        event.register(EggstraParticles.GOLD_GLITTER.get(), GoldGlitterParticle.Factory::new);
        event.register(EggstraParticles.GOLD_FLASH.get(), GoldFlashParticle.Factory::new);
    }

    private static void onRenderersSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EggstraEntityTypes.GOLDEN_EGG_ENTITY.get(), ThrownItemRenderer::new);
    }



}

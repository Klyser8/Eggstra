package com.github.klyser8.eggstra.forge;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.client.EggstraForgeClient;
import com.github.klyser8.eggstra.platform.forge.CommonPlatformHelperImpl;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Eggstra.MOD_ID)
public class EggstraForge {
    public EggstraForge() {
        Eggstra.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CommonPlatformHelperImpl.ITEMS.register(bus);
        CommonPlatformHelperImpl.BLOCKS.register(bus);
        CommonPlatformHelperImpl.PARTICLE_TYPES.register(bus);
        CommonPlatformHelperImpl.SOUND_EVENTS.register(bus);
        CommonPlatformHelperImpl.STRUCTURE_PROCESSOR_TYPES.register(bus);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            EggstraForgeClient.subscribeClientEvents();
        }
    }
}
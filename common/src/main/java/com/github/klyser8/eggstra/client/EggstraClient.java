package com.github.klyser8.eggstra.client;

import com.github.klyser8.eggstra.platform.ClientPlatformHelper;
import com.github.klyser8.eggstra.registry.EggstraEntityTypes;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class EggstraClient {

    public static void init() {
        ClientPlatformHelper.registerEntityRenderer(EggstraEntityTypes.GOLDEN_EGG_ENTITY, ThrownItemRenderer::new);
    }

}

package com.github.klyser8.eggstra;

import com.github.klyser8.eggstra.registry.*;

public class Eggstra {
    public static final String MOD_ID = "eggstra";

    public static void init() {
        EggstraBlocks.register();
        EggstraItems.register();
        EggstraParticles.register();
        EggstraSounds.register();
        EggstraProcessors.register();
    }
}
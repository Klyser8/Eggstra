package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.platform.CommonPlatformHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class EggstraSounds {

    public static void register() {}

    public static final Supplier<SoundEvent> GOLD_CAKE_PLACE = CommonPlatformHelper.registerSoundEvent("gold_cake_place",
            () -> new SoundEvent(new ResourceLocation(Eggstra.MOD_ID, "block.golden_cake.place")));
    public static final Supplier<SoundEvent> GOLD_CAKE_BREAK = CommonPlatformHelper.registerSoundEvent("gold_cake_break",
            () -> new SoundEvent(new ResourceLocation(Eggstra.MOD_ID, "block.golden_cake.place")));
    public static final Supplier<SoundEvent> GOLDEN_CAKE_GLITTER = CommonPlatformHelper.registerSoundEvent("gold_glitter",
            () -> new SoundEvent(new ResourceLocation(Eggstra.MOD_ID, "block.golden_cake.glitter")));

}

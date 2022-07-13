package com.github.klyser8.eggstra;

import com.github.klyser8.eggstra.registry.EggstraSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class EggstraSoundTypes extends SoundType {

    public EggstraSoundTypes(float f, float g, SoundEvent soundEvent, SoundEvent soundEvent2, SoundEvent soundEvent3, SoundEvent soundEvent4, SoundEvent soundEvent5) {
        super(f, g, soundEvent, soundEvent2, soundEvent3, soundEvent4, soundEvent5);
    }

    public static final SoundType GOLDEN_CAKE =
            new EggstraSoundTypes(1.0f, 1.0f, EggstraSounds.GOLD_CAKE_BREAK.get(),
                    EggstraSounds.GOLD_CAKE_BREAK.get(), EggstraSounds.GOLD_CAKE_PLACE.get(),
                    SoundEvents.WOOL_HIT, SoundEvents.WOOL_FALL);

}

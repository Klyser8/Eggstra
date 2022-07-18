package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.advancement.CrackGoldenEggTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.resources.ResourceLocation;

public class EggstraAdvancementTriggers {

    public static final CrackGoldenEggTrigger CRACK_GOLDEN_EGG = new CrackGoldenEggTrigger();
    public static final PlayerTrigger EAT_GOLDEN_CAKE = new PlayerTrigger(new ResourceLocation(Eggstra.MOD_ID, "eat_golden_cake"));

    public static void register() {
        CriteriaTriggers.register(CRACK_GOLDEN_EGG);
        CriteriaTriggers.register(EAT_GOLDEN_CAKE);
    }

}

package com.github.klyser8.eggstra.event;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.registry.EggstraTrades;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Eggstra.MOD_ID)
public class EggstraEventBusEvents {

    @SubscribeEvent
    public static void addTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FARMER) {
            event.getTrades().get(5).add((arg, arg2) -> EggstraTrades.GOLD_EGG_FOR_EMERALDS);
        }

        if (event.getType() == VillagerProfession.CLERIC) {
            event.getTrades().get(3).add((arg, arg2) -> EggstraTrades.EGG_AND_EMERALD_BLOCK_FOR_GOLD_EGG);
        }
    }

}

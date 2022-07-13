package com.github.klyser8.eggstra.fabric;

import com.github.klyser8.eggstra.Eggstra;
import com.github.klyser8.eggstra.registry.EggstraTrades;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;

public class EggstraFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Eggstra.init();
        registerTrades();
    }

    private static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 5,
                factories -> {
                    factories.add((entity, randomSource) -> EggstraTrades.GOLD_EGG_FOR_EMERALDS);
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 3,
                factories -> {
                    factories.add((entity, randomSource) -> EggstraTrades.EGG_AND_EMERALD_BLOCK_FOR_GOLD_EGG);
        });
    }

}
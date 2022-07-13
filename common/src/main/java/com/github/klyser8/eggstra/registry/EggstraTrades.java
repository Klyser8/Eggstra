package com.github.klyser8.eggstra.registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public class EggstraTrades {

    public static final MerchantOffer GOLD_EGG_FOR_EMERALDS = new MerchantOffer(
            new ItemStack(EggstraItems.GOLDEN_EGG.get(), 1),
            new ItemStack(Items.EMERALD, 32),
            1, 5, 0.1f
    );
    public static final MerchantOffer EGG_AND_EMERALD_BLOCK_FOR_GOLD_EGG = new MerchantOffer(
            new ItemStack(Items.EMERALD_BLOCK, 8),
            new ItemStack(Items.EGG, 1),
            new ItemStack(EggstraItems.GOLDEN_EGG.get(), 1),
            1, 30, 0.1f
    );

}

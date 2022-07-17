package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.item.EggstraRecordItem;
import com.github.klyser8.eggstra.platform.CommonPlatformHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class EggstraItems {

    public static void register() {}

    public static final Supplier<Item> FRIED_EGG = CommonPlatformHelper.registerItem(
            "fried_egg", () -> new Item(new Item.Properties()
                    .food(EggstraFoods.FRIED_EGG)
                    .stacksTo(16)
                    .tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> GOLDEN_EGG = CommonPlatformHelper.registerItem(
            "golden_egg", () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.RARE)
                    .tab(CreativeModeTab.TAB_MISC)));
    public static final Supplier<Item> GOLDEN_CAKE = CommonPlatformHelper.registerItem(
            "golden_cake", () -> new BlockItem(EggstraBlocks.GOLDEN_CAKE.get(), new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.EPIC)
                    .tab(CreativeModeTab.TAB_FOOD)));
    public static final Supplier<Item> MUSIC_DISC_GOLD = CommonPlatformHelper.registerItem(
            "music_disc_gold", () -> new EggstraRecordItem(7, EggstraSounds.MUSIC_DISC_GOLD.get(),
                    new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

}

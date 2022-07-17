package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.EggstraSoundTypes;
import com.github.klyser8.eggstra.block.GoldenCandleCakeBlock;
import com.github.klyser8.eggstra.block.GoldenCakeBlock;
import com.github.klyser8.eggstra.platform.CommonPlatformHelper;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static com.github.klyser8.eggstra.platform.CommonPlatformHelper.registerBlock;

public class EggstraBlocks {

    public static void register() {}

    public static final Supplier<GoldenCakeBlock> GOLDEN_CAKE = registerBlock("golden_cake", () ->
            new GoldenCakeBlock(BlockBehaviour.Properties.copy(Blocks.CAKE).sound(EggstraSoundTypes.GOLDEN_CAKE)));

    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_CANDLE_CAKE = registerBlock("golden_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.CANDLE, BlockBehaviour.Properties.copy(Blocks.CAKE).sound(EggstraSoundTypes.GOLDEN_CAKE).lightLevel(litBlockEmission(3))));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_WHITE_CANDLE_CAKE = registerBlock("golden_white_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.WHITE_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_ORANGE_CANDLE_CAKE = registerBlock("golden_orange_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.ORANGE_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_MAGENTA_CANDLE_CAKE = registerBlock("golden_magenta_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.MAGENTA_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_LIGHT_BLUE_CANDLE_CAKE = registerBlock("golden_light_blue_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_YELLOW_CANDLE_CAKE = registerBlock("golden_yellow_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.YELLOW_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_LIME_CANDLE_CAKE = registerBlock("golden_lime_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.LIME_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_PINK_CANDLE_CAKE = registerBlock("golden_pink_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.PINK_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_GRAY_CANDLE_CAKE = registerBlock("golden_gray_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.GRAY_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_LIGHT_GRAY_CANDLE_CAKE = registerBlock("golden_light_gray_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_CYAN_CANDLE_CAKE = registerBlock("golden_cyan_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.CYAN_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_PURPLE_CANDLE_CAKE = registerBlock("golden_purple_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.PURPLE_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_BLUE_CANDLE_CAKE = registerBlock("golden_blue_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.BLUE_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_BROWN_CANDLE_CAKE = registerBlock("golden_brown_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.BROWN_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_GREEN_CANDLE_CAKE = registerBlock("golden_green_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.GREEN_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_RED_CANDLE_CAKE = registerBlock("golden_red_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.RED_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));
    public static final Supplier<GoldenCandleCakeBlock> GOLDEN_BLACK_CANDLE_CAKE = registerBlock("golden_black_candle_cake", () ->
            new GoldenCandleCakeBlock(Blocks.BLACK_CANDLE, BlockBehaviour.Properties.copy(GOLDEN_CANDLE_CAKE.get())));

    private static ToIntFunction<BlockState> litBlockEmission(int i) {
        return blockState -> blockState.getValue(BlockStateProperties.LIT) ? i : 0;
    }

}

package com.github.klyser8.eggstra.world.processors;

import com.github.klyser8.eggstra.registry.EggstraProcessors;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class WaterlogBlocksProcessor extends StructureProcessor {

    /**
     * Registry.BLOCK.byNameCodec() - the processor will expect the json to have a list of block names.
     * xmap - takes a high-level codec and converts it to a low-level codec.
     *        E.G., below converts a hash set to an array list
     * fieldOf -
     */
    public static final Codec<WaterlogBlocksProcessor> CODEC = (BlockState.CODEC
            .xmap(BlockBehaviour.BlockStateBase::getBlock, Block::defaultBlockState)
            .listOf().fieldOf("blocks"))
            .xmap(WaterlogBlocksProcessor::new, waterLogprocessor -> waterLogprocessor.blocksToWaterlog).codec();
    private final ImmutableList<Block> blocksToWaterlog;

    public WaterlogBlocksProcessor(List<Block> blocksToWaterlog) {
        this.blocksToWaterlog = ImmutableList.copyOf(blocksToWaterlog);
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader levelReader, BlockPos originPos, BlockPos otherPos, //These stay the same all the time
            StructureTemplate.StructureBlockInfo blockInfoLocal, //Block info relative to structure coords
            StructureTemplate.StructureBlockInfo blockInfoWorld, StructurePlaceSettings structurePlaceSettings) {
        BlockPos blockPos = blockInfoWorld.pos;
        if (levelReader.isWaterAt(blockPos)) {
            if (levelReader.isWaterAt(blockPos.above())
                    && blockInfoWorld.state.hasProperty(BlockStateProperties.WATERLOGGED)
                    && blocksToWaterlog.contains(blockInfoWorld.state.getBlock())) {
                blockInfoWorld = new StructureTemplate.StructureBlockInfo(blockPos,
                        blockInfoWorld.state.setValue(BlockStateProperties.WATERLOGGED, true), null);
            } else if (blockInfoWorld.state.isAir()) {
                BlockState posState = levelReader.getBlockState(blockPos);
                blockInfoWorld = new StructureTemplate.StructureBlockInfo(blockPos, posState, null);
            }
        }
        return blockInfoWorld;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return EggstraProcessors.WATERLOG_BLOCKS.get();
    }
}

package com.github.klyser8.eggstra.world.processors;

import com.github.klyser8.eggstra.registry.EggstraProcessors;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

public class WaterloggingFixProcessor extends StructureProcessor {

    public static final Codec<WaterloggingFixProcessor> CODEC = Codec.unit(WaterloggingFixProcessor::new);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader levelReader, BlockPos blockPos, BlockPos blockPos2,
            StructureTemplate.StructureBlockInfo structureBlockInfo,
            StructureTemplate.StructureBlockInfo structureBlockInfo2, StructurePlaceSettings structurePlaceSettings) {
        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return EggstraProcessors.WATERLOGGING_FIX_PROCESSOR.get();
    }
}

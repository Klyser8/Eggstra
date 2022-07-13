package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.world.processors.WaterlogBlocksProcessor;
import com.github.klyser8.eggstra.world.processors.WaterloggingFixProcessor;
import com.github.klyser8.eggstra.platform.CommonPlatformHelper;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

public class EggstraProcessors {

    public static void register() {}

    public static final Supplier<StructureProcessorType<WaterloggingFixProcessor>> WATERLOGGING_FIX_PROCESSOR =
            CommonPlatformHelper.registerStructureProcessor("waterlog_fix", () -> () -> WaterloggingFixProcessor.CODEC);
    public static final Supplier<StructureProcessorType<WaterlogBlocksProcessor>> WATERLOG_BLOCKS =
            CommonPlatformHelper.registerStructureProcessor("waterlog_blocks", () -> () -> WaterlogBlocksProcessor.CODEC);

}

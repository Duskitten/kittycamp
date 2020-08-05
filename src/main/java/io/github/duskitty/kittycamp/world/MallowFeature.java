package io.github.duskitty.kittycamp.world;

import com.mojang.serialization.Codec;
import io.github.duskitty.kittycamp.init.Block_Init;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class MallowFeature extends Feature<MallowFeatureConfig> {

    public MallowFeature(Codec<MallowFeatureConfig> featureConfig) {
        super(featureConfig);
    }

    @Override
    public boolean generate(ServerWorldAccess serverWorldAccess, StructureAccessor accessor, ChunkGenerator generator, Random random, BlockPos pos, MallowFeatureConfig config) {
        boolean gen = false;
        int distance = Math.min(8, Math.max(1, config.getPatchSize()));
        BlockState mallowplant = Block_Init.MALLOWPLANT.getDefaultState();
        for (int i = 0; i < config.getPatchQuantity(); i++) {
            if (random.nextInt(config.getPatchChance()) == 0) {
                int x = pos.getX() + random.nextInt(16);
                int z = pos.getZ() + random.nextInt(16);
                int y = serverWorldAccess.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos).getY();

                for (int j = 0; j < config.getPatchDensity() * config.getPatchChance(); j++) {
                    int x1 = x + random.nextInt(distance * 2) - distance;
                    int y1 = y + random.nextInt(4) - random.nextInt(4);
                    int z1 = z + random.nextInt(distance * 2) - distance;
                    BlockPos pos2 = new BlockPos(x1, y1, z1);
                    if (serverWorldAccess.isAir(pos2) && (!serverWorldAccess.getDimension().isPiglinSafe() || y1 < 127) &&mallowplant.canPlaceAt(serverWorldAccess, pos2)) {
                        serverWorldAccess.setBlockState(pos2, mallowplant, 2);
                        gen = true;
                    }
                }
            }
        }

        return gen;
    }

}
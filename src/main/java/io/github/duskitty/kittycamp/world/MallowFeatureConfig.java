package io.github.duskitty.kittycamp.world;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.FeatureConfig;


import static io.github.duskitty.kittycamp.properties.MallowPlantProperties.*;

public class MallowFeatureConfig implements FeatureConfig {
    public int getPatchSize() {
        return flonterPatchSize;
    }

    public int getPatchQuantity() {
        return flonterPatchQuantity;
    }

    public int getPatchDensity() {
        return flonterPatchDensity;
    }

    public int getPatchChance() {
        return flonterPatchChance;
    }

    public double getTallChance() {
        return tallFlonterChance;
    }

    public static final Codec<MallowFeatureConfig> CODEC = Codec.unit(new MallowFeatureConfig());
}
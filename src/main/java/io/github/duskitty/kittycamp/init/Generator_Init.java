package io.github.duskitty.kittycamp.init;

import io.github.duskitty.kittycamp.world.MallowFeature;
import io.github.duskitty.kittycamp.world.MallowFeatureConfig;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.feature.*;

public class Generator_Init {
    public static <F extends FeatureConfig, D extends DecoratorConfig> ConfiguredFeature<?, ?> configureFeature(Feature<F> feature, F featureConfig, Decorator<D> decorator, D decoratorConfig) {
        Feature<DecoratedFeatureConfig> feature2 = feature instanceof FlowerFeature ? Feature.DECORATED_FLOWER : Feature.DECORATED;
        return new ConfiguredFeature<>(feature2, new DecoratedFeatureConfig(feature.configure(featureConfig), decorator.configure(decoratorConfig)));
    }

    public static final Feature<MallowFeatureConfig> SMALL_FLONTERS = register("small_flonters", new MallowFeature(MallowFeatureConfig.CODEC));

    public static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, "kittycamp", feature);
    }

    private static void handleBiome(Biome biome) {
        if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                    configureFeature(SMALL_FLONTERS,
                            new MallowFeatureConfig(),
                            Decorator.NOPE,
                            NopeDecoratorConfig.DEFAULT));
        }
    }

    public static void init() {
        for (Biome biome : Registry.BIOME) {
            handleBiome(biome);
        }
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }
}

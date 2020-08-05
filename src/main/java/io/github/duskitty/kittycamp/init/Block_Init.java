package io.github.duskitty.kittycamp.init;

import io.github.duskitty.kittycamp.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Block_Init {

    public static final Block MALLOWSTICK = new Block_Mallow(FabricBlockSettings.of(Material.WOOD).hardness(0.0f).nonOpaque().collidable(false).ticksRandomly()) {};
    public static final Block NOMALLOWSTICK = new Block_NoMallow(FabricBlockSettings.of(Material.WOOD).hardness(0.0f).nonOpaque().collidable(false)) {};
    public static final Block COOKEDMALLOWSTICK = new Block_Cooked(FabricBlockSettings.of(Material.WOOD).hardness(0.0f).nonOpaque().collidable(false)) {};
    public static final Block BURNEDMALLOWSTICK = new Block_Burned(FabricBlockSettings.of(Material.WOOD).hardness(0.0f).nonOpaque().collidable(false)) {};
    public static final Block MALLOWPLANT = new Mallow_Plant(FabricBlockSettings.of(Material.WOOD).hardness(0.0f).nonOpaque().collidable(false)) {};

    public static void InitBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("kittycamp", "stick_mallow"), MALLOWSTICK);
        Registry.register(Registry.BLOCK, new Identifier("kittycamp", "stick_nomallow"), NOMALLOWSTICK);
        Registry.register(Registry.BLOCK, new Identifier("kittycamp", "stick_cooked"), COOKEDMALLOWSTICK);
        Registry.register(Registry.BLOCK, new Identifier("kittycamp", "stick_burned"), BURNEDMALLOWSTICK);
        Registry.register(Registry.BLOCK, new Identifier("kittycamp", "mallow_plant"), MALLOWPLANT);
    }
}

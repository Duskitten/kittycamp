package io.github.duskitty.kittycamp;

import io.github.duskitty.kittycamp.init.Block_Init;
import io.github.duskitty.kittycamp.init.Generator_Init;
import io.github.duskitty.kittycamp.init.Item_Init;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Block_Init.InitBlocks();
		Item_Init.InitItems();
		Generator_Init.init();
		System.out.println("Hello Fabric world!");
	}

	public static final ItemGroup KITTYCAMP = FabricItemGroupBuilder.build(
			new Identifier("kittycamp", "kittycamptab"),
			() -> new ItemStack(Blocks.CAMPFIRE));

}

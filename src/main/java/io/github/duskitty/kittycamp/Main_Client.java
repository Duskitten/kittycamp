package io.github.duskitty.kittycamp;

import io.github.duskitty.kittycamp.init.Block_Init;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.blockrenderlayer.BlockRenderLayerMapImpl;
import net.minecraft.client.render.RenderLayer;

public class Main_Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //BlockRenderLayerMap.INSTANCE.putBlock(Block_Init.MALLOWSTICK, RenderLayer.getCutout());
        BlockRenderLayerMapImpl.INSTANCE.putBlocks(RenderLayer.getCutout(),Block_Init.MALLOWPLANT);
    }
}

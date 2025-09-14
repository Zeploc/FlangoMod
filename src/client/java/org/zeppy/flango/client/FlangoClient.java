package org.zeppy.flango.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import org.zeppy.flango.FizzleBlock.FizzleBlockScreenHandler;
import org.zeppy.flango.client.Fizzle.FizzleScreen;
import org.zeppy.flango.core.FlangoBlockEntities;
import org.zeppy.flango.client.Fizzle.FizzleBlockEntityRenderer;
import org.zeppy.flango.core.FlangoScreens;

public class FlangoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(FlangoBlockEntities.FIZZLE_BLOCK_ENTITY, FizzleBlockEntityRenderer::new);
        HandledScreens.register(FlangoScreens.FIZZLE_BLOCK_SCREEN_HANDLER, FizzleScreen::new);
    }
}

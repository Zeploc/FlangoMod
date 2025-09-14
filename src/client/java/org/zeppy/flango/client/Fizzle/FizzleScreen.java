package org.zeppy.flango.client.Fizzle;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.zeppy.flango.FizzleBlock.FizzleBlockScreenHandler;

public class FizzleScreen extends HandledScreen<FizzleBlockScreenHandler> {
    // A path to the gui texture. In this example we use the texture from the dispenser

    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/dispenser.png");

    public FizzleScreen(FizzleBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
//        context.drawCenteredTextWithShadow(textRenderer, title, titleX, 8, 0xFFFFFF);
//        RenderSystem.setShaderTexture();
//        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//        RenderSystem.setShaderTexture(0, new Identifier("mymod", "textures/gui/my_block.png"));
//        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
//        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        RenderSystem.setShaderTexture(0, TEXTURE);

//        int x = (width - backgroundWidth) / 2;
//        int y = (height - backgroundHeight) / 2;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}

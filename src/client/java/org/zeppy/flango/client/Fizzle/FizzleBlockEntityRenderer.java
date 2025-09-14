package org.zeppy.flango.client.Fizzle;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.zeppy.flango.FizzleBlock.FizzleBlockEntity;

public class FizzleBlockEntityRenderer implements BlockEntityRenderer<FizzleBlockEntity> {
    public FizzleBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(FizzleBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {

    }
}

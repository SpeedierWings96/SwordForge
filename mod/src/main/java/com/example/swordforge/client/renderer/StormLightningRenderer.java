package com.example.swordforge.client.renderer;

import com.example.swordforge.entity.StormLightningEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class StormLightningRenderer extends EntityRenderer<StormLightningEntity> {
    public StormLightningRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(StormLightningEntity entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        VertexConsumer consumer = buffer.getBuffer(RenderType.lightning());
        poseStack.pushPose();
        Random rand = new Random(entity.getId());
        Vec3 prev = Vec3.ZERO;
        for (int i = 0; i < 5; i++) {
            Vec3 next = prev.add(rand.nextFloat() - 0.5F, 0.5F, rand.nextFloat() - 0.5F);
            drawSegment(poseStack, consumer, prev, next, 0.1F, 0.75F);
            if (i == 2 && rand.nextBoolean()) {
                Vec3 fork = next.add(rand.nextFloat() - 0.5F, 0.5F, rand.nextFloat() - 0.5F);
                drawSegment(poseStack, consumer, next, fork, 0.05F, 0.5F);
            }
            prev = next;
        }
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, buffer, packedLight);
    }

    private static void drawSegment(PoseStack poseStack, VertexConsumer consumer, Vec3 start, Vec3 end, float width, float alpha) {
        PoseStack.Pose pose = poseStack.last();
        Vec3 diff = end.subtract(start);
        Vec3 perpendicular = diff.cross(new Vec3(0.0D, 1.0D, 0.0D)).normalize().scale(width);
        float r = 0.8F;
        float g = 0.9F;
        float b = 1.0F;
        consumer.vertex(pose.pose(), (float)(start.x - perpendicular.x), (float)start.y, (float)(start.z - perpendicular.z)).color(r, g, b, alpha).endVertex();
        consumer.vertex(pose.pose(), (float)(start.x + perpendicular.x), (float)start.y, (float)(start.z + perpendicular.z)).color(r, g, b, alpha).endVertex();
        consumer.vertex(pose.pose(), (float)(end.x + perpendicular.x), (float)end.y, (float)(end.z + perpendicular.z)).color(r, g, b, alpha).endVertex();
        consumer.vertex(pose.pose(), (float)(end.x - perpendicular.x), (float)end.y, (float)(end.z - perpendicular.z)).color(r, g, b, alpha).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(StormLightningEntity entity) {
        return null;
    }
}

package com.DragonFire.render.entity.layer;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.EntityDraug;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerDraugClothing implements LayerRenderer<EntityDraug> {
    private static final ResourceLocation DRAUG_CLOTHES = new ResourceLocation(DragonFire.MODID, "textures/entity/draug_overlay.png");
    private final RenderLivingBase<?> renderer;
    private final ModelSkeleton modelSkeleton = new ModelSkeleton(0.25F, true);
    
    public LayerDraugClothing(RenderLivingBase<?> renderer) {this.renderer = renderer;}
    
    @Override
    public void doRenderLayer(EntityDraug en, float swing, float swingAmount, float ticks, float age, float yaw, float pitch, float scale) {
        modelSkeleton.setModelAttributes(renderer.getMainModel());
        modelSkeleton.setLivingAnimations(en, swing, swingAmount, ticks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        renderer.bindTexture(DRAUG_CLOTHES);
        modelSkeleton.render(en, swing, swingAmount, age, yaw, pitch, scale);
    }
    
    public boolean shouldCombineTextures() {return true;}
}
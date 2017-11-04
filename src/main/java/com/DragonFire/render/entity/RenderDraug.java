package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.render.entity.layer.LayerDraugClothing;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;

public class RenderDraug extends RenderSkeleton {
    public RenderDraug(RenderManager rm) {
        super(rm);
        addLayer(new LayerDraugClothing(this));
    }
    
    @Override
    public ResourceLocation getEntityTexture(AbstractSkeleton en) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, "textures/entity/draug.png");
        return rl;
    }
}
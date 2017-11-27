package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.living.EntityScarecrow;
import com.DragonFire.render.entity.model.ModelScarecrow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderScarecrow extends RenderLiving<EntityScarecrow> {
    public RenderScarecrow(RenderManager rm) {super(rm, new ModelScarecrow(), 0.5F);}

    @Override
    protected ResourceLocation getEntityTexture(EntityScarecrow en) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, "textures/entity/scarecrow.png");
        return rl;
    }
    
    @Override
    public ModelScarecrow getMainModel() {
        ModelBase mb = super.getMainModel();
        return (ModelScarecrow) mb;
    }
}
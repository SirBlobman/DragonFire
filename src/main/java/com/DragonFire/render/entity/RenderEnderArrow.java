package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.projectile.EntityEnderArrow;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEnderArrow extends RenderArrow<EntityEnderArrow> {
    private static final ResourceLocation ENDER_ARROW_TEXTURES = new ResourceLocation(DragonFire.MODID, "textures/entity/ender_arrow.png");
    public RenderEnderArrow(RenderManager rm) {super(rm);}

    @Override
    protected ResourceLocation getEntityTexture(EntityEnderArrow eea) {return ENDER_ARROW_TEXTURES;}
}
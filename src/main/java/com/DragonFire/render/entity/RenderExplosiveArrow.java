package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.projectile.EntityExplosiveArrow;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderExplosiveArrow extends RenderArrow<EntityExplosiveArrow> {
    private static final ResourceLocation EXPLOSIVE_ARROW_TEXTURES = new ResourceLocation(DragonFire.MODID, "textures/entity/explosive_arrow.png");
    public RenderExplosiveArrow(RenderManager rm) {super(rm);}

    @Override
    protected ResourceLocation getEntityTexture(EntityExplosiveArrow eea) {return EXPLOSIVE_ARROW_TEXTURES;}
}
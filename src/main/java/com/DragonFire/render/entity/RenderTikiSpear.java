package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.projectile.EntityTikiSpear;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTikiSpear extends RenderArrow<EntityTikiSpear> {
    private static final ResourceLocation TIKI_SPEAR_TEXTURES = new ResourceLocation(DragonFire.MODID, "textures/entity/tiki_spear.png");
    public RenderTikiSpear(RenderManager rm) {super(rm);}

    @Override
    protected ResourceLocation getEntityTexture(EntityTikiSpear ets) {return TIKI_SPEAR_TEXTURES;}
}

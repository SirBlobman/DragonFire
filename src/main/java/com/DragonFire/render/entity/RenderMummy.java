package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderMummy extends RenderZombie {
    private static final ResourceLocation MUMMY_TEXTURES = new ResourceLocation(DragonFire.MODID, "textures/entity/mummy.png");
    public RenderMummy(RenderManager rm) {super(rm);}
    
    @Override
    public ResourceLocation getEntityTexture(EntityZombie ez) {return MUMMY_TEXTURES;}
}
package com.DragonFire.render.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.living.EntityJungleSpider;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.util.ResourceLocation;

public class RenderJungleSpider extends RenderSpider<EntityJungleSpider> {
    public static final ResourceLocation JUNGLE_SPIDER_TEXTURES = new ResourceLocation(DragonFire.MODID, "textures/entity/jungle_spider.png");
    public RenderJungleSpider(RenderManager rm) {super(rm);}
    
    @Override
    public ResourceLocation getEntityTexture(EntityJungleSpider ejs) {
        return JUNGLE_SPIDER_TEXTURES;
    }
}
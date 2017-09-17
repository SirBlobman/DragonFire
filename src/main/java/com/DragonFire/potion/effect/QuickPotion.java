package com.DragonFire.potion.effect;

import com.DragonFire.DragonFire;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class QuickPotion extends Potion {
    private static final ResourceLocation DF_POTIONS = new ResourceLocation(DragonFire.MODID, "textures/potions/potions.png");
    
    public QuickPotion(String name, boolean isBad, int color, int textureX, int textureY) {
        super(isBad, color);
        setRegistryName(name);
        setPotionName("dragonfire.effect." + name);
        setIconIndex(textureX, textureY);
    }
    
    @Override
    public boolean hasStatusIcon() {
        Minecraft mc = Minecraft.getMinecraft();
        TextureManager tm = mc.getTextureManager();
        tm.bindTexture(DF_POTIONS);
        return super.hasStatusIcon();
    }
}
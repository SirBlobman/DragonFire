package com.DragonFire.entity;

import com.DragonFire.DragonFire;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class DFEntities {
    public static void entities() {
        reg("ender_arrow", EntityEnderArrow.class);
        reg("mummy", EntityMummy.class, rgb(195, 171, 135), rgb(221, 204, 182));
    }
    
    private static int id = 0;
    private static void reg(String name, Class<? extends Entity> clazz) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, name);
        DragonFire df = DragonFire.INSTANCE;
        EntityRegistry.registerModEntity(rl, clazz, name, id, df, 64, 1, true);
        id = (id + 1);
    }
    
    private static void reg(String name, Class<? extends Entity> clazz, int egg1, int egg2) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, name);
        reg(name, clazz);
        EntityRegistry.registerEgg(rl, egg1, egg2);
    }
    
    public static int rgb(int red, int green, int blue) {
        int r = (red << 16) & 0x00FF0000;
        int g = (green << 8) & 0x0000FF00;
        int b = (blue) & 0x000000FF;
        int color = (0xFF000000 | r | g | b);
        return color;
    }
}
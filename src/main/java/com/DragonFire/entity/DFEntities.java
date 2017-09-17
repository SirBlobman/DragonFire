package com.DragonFire.entity;

import com.DragonFire.DragonFire;
import com.DragonFire.utility.Util;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class DFEntities {
    public static void entities() {
        reg("ender_arrow", EntityEnderArrow.class);
        reg("tiki_spear", EntityTikiSpear.class);
        reg("mummy", EntityMummy.class, Util.getRGB(243, 226, 175), Util.getRGB(204, 189, 146));
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
}
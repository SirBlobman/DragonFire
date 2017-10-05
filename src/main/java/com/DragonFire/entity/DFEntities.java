package com.DragonFire.entity;

import static com.DragonFire.utility.Util.getRGB;

import com.DragonFire.DragonFire;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class DFEntities {
    public static void entities() {
        reg("ender_arrow", EntityEnderArrow.class);
        reg("tiki_spear", EntityTikiSpear.class);
        reg("mummy", EntityMummy.class, getRGB(243, 226, 175), getRGB(204, 189, 146));
        
        //Extra Eggs
        regEgg(EntityIronGolem.class, getRGB(225, 221, 219), getRGB(191, 162, 142));
        regEgg(EntitySnowman.class, getRGB(255, 255, 255), getRGB(227, 144, 29));
        regEgg(EntityWither.class, getRGB(26, 26, 26), getRGB(92, 92, 92));
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
    
    private static void regEgg(Class<? extends Entity> clazz, int egg1, int egg2) {
        EntityEntry ee = EntityRegistry.getEntry(clazz);
        ResourceLocation rl = ee.getRegistryName();
        EntityRegistry.registerEgg(rl, egg1, egg2);
    }
}
package com.DragonFire.entity;

import static com.DragonFire.utility.Util.getRGB;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.projectile.*;
import com.DragonFire.utility.Util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class DFEntities {
    public static void entities() {
        reg("ender_arrow", EntityEnderArrow.class, 10);
        reg("tiki_spear", EntityTikiSpear.class, 10);
        reg("mummy", EntityMummy.class, 10, getRGB(243, 226, 175), getRGB(204, 189, 146));
        reg("dynamite", EntityDynamite.class, 10);
        reg("explosive_arrow", EntityExplosiveArrow.class, 10);
        reg("nether_fish_hook", EntityNetherFishHook.class, 10);
        
        //Extra Eggs
        regEgg(EntityIronGolem.class, getRGB(225, 221, 219), getRGB(191, 162, 142));
        regEgg(EntitySnowman.class, getRGB(255, 255, 255), getRGB(227, 144, 29));
        regEgg(EntityWither.class, getRGB(26, 26, 26), getRGB(92, 92, 92));
        regEgg(EntityIllusionIllager.class, getRGB(19, 88, 147), getRGB(149, 155, 155));
        
        //Spawning
        regSpawn(EntityMummy.class, EnumCreatureType.MONSTER, Util.getBiomes("desert"));
    }
    
    private static int id = 0;
    private static void reg(String name, Class<? extends Entity> clazz, int updateSpeed) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, name);
        DragonFire df = DragonFire.INSTANCE;
        EntityRegistry.registerModEntity(rl, clazz, name, id, df, 64, updateSpeed, true);
        id = (id + 1);
    }
    
    private static void reg(String name, Class<? extends Entity> clazz, int updateSpeed, int egg1, int egg2) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, name);
        reg(name, clazz, updateSpeed);
        EntityRegistry.registerEgg(rl, egg1, egg2);
    }
    
    private static void regEgg(Class<? extends Entity> clazz, int egg1, int egg2) {
        EntityEntry ee = EntityRegistry.getEntry(clazz);
        ResourceLocation rl = ee.getRegistryName();
        EntityRegistry.registerEgg(rl, egg1, egg2);
    }
    
    private static void regSpawn(Class<? extends EntityLiving> clazz, EnumCreatureType type, Biome... biomes) {
        EntityRegistry.addSpawn(clazz, 70, 1, 2, type, biomes);
    }
}
package com.DragonFire.entity;

import static com.DragonFire.utility.Util.getRGB;

import com.DragonFire.DragonFire;
import com.DragonFire.entity.projectile.*;

import java.util.Set;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class DFEntities {
    private static final EnumCreatureType WATER_DRAUG = EnumHelper.addCreatureType("WATER_DRAUG", EntityDraug.class, 70, Material.WATER, false, false);
    public static void entities() {
        reg("ender_arrow", EntityEnderArrow.class);
        reg("tiki_spear", EntityTikiSpear.class);
        reg("mummy", EntityMummy.class, getRGB(243, 226, 175), getRGB(204, 189, 146));
        reg("dynamite", EntityDynamite.class);
        reg("explosive_arrow", EntityExplosiveArrow.class);
        reg("nether_fish_hook", EntityNetherFishHook.class);
        reg("draug", EntityDraug.class, getRGB(92, 143, 120), getRGB(146, 212, 223));
        
        //Extra Eggs
        regEgg(EntityIronGolem.class, getRGB(225, 221, 219), getRGB(191, 162, 142));
        regEgg(EntitySnowman.class, getRGB(255, 255, 255), getRGB(227, 144, 29));
        regEgg(EntityWither.class, getRGB(26, 26, 26), getRGB(92, 92, 92));
        regEgg(EntityIllusionIllager.class, getRGB(19, 88, 147), getRGB(149, 155, 155));
        
        //Spawning
        regSpawn(EntityMummy.class, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(Type.SANDY), 70);
        regSpawn(EntityMummy.class, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(Type.WASTELAND), 70);
        regSpawn(EntityMummy.class, EnumCreatureType.MONSTER, BiomeDictionary.getBiomes(Type.DRY), 70);
        regSpawn(EntityDraug.class, WATER_DRAUG, BiomeDictionary.getBiomes(Type.OCEAN), 100);
        regSpawn(EntityDraug.class, WATER_DRAUG, BiomeDictionary.getBiomes(Type.WET), 100);
        regSpawn(EntityDraug.class, WATER_DRAUG, BiomeDictionary.getBiomes(Type.WATER), 100);
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
    
    private static void regSpawn(Class<? extends EntityLiving> clazz, EnumCreatureType type, Set<Biome> bb, int weight) {
        Biome[] biomes = bb.toArray(new Biome[0]);
        EntityRegistry.addSpawn(clazz, weight, 1, 2, type, biomes);
    }
}
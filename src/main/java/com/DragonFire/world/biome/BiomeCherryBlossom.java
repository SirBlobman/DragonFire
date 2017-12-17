package com.DragonFire.world.biome;

import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.utility.Util;
import com.DragonFire.world.DFWorldGenCustomTree;

import java.util.Random;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeCherryBlossom extends Biome {
    public static final int GRASS_COLOR = Util.getRGB(55, 183, 63);
    public static final int LEAVES_COLOR = Util.getRGB(255, 127, 255);
    
    public BiomeCherryBlossom(BiomeProperties properties) {
        super(properties);
        setRegistryName("cherry_blossom");
        this.decorator.treesPerChunk = 1;
        this.decorator.grassPerChunk = 2;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }
    
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        DFWorldGenCustomTree tree = new DFWorldGenCustomTree(false, DFWoodType.CHERRY);
        return tree;
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos bp) {
        return GRASS_COLOR;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos bp) {
        return LEAVES_COLOR;
    }
}
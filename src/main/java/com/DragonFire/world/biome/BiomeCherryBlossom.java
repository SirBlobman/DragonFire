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
        int color = Util.getRGB(119, 211, 57);
        return color;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos bp) {
        int color = Util.getRGB(255, 181, 216);
        return color;
    }
}
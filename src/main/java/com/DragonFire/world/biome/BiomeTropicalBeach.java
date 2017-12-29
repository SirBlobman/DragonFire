package com.DragonFire.world.biome;

import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.world.DFWorldGenCustomTree;

import java.util.Random;

import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeTropicalBeach extends BiomeBeach {
    public BiomeTropicalBeach(BiomeProperties bp) {
        super(bp);
        setRegistryName("tropical_beach");
        this.decorator.treesPerChunk = 1;
    }
    
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        DFWorldGenCustomTree tree = new DFWorldGenCustomTree(false, DFWoodType.PALM);
        return tree;
    }
}
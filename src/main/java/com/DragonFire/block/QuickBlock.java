package com.DragonFire.block;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class QuickBlock extends Block {
    public QuickBlock(String name) {this(name, Material.ROCK);}
    public QuickBlock(String name, Material mat) { this(name, mat, 1.0F, 1.0F);}
    
    public QuickBlock(String name, Material mat, float hardness, float resistance) {
        super(mat);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    public QuickBlock(String name, Material mat, float hardness, float resistance, String tool, int harvestLevel) {
        this(name, mat, hardness, resistance);
        setHarvestLevel(tool, harvestLevel);
    }
}
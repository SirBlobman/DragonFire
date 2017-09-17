package com.DragonFire.block;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class QuickBlock extends Block {
    public QuickBlock(String name) {this(name, Material.ROCK);}
    public QuickBlock(String name, Material mat) {
        super(mat);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
package com.DragonFire.block.tree;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

public class BlockDFWoodFence extends BlockFence {
    public BlockDFWoodFence(DFWoodType wood) {
        super(Material.WOOD, wood.getMapColor());
        setRegistryName(wood.getName() + "_fence");
        setUnlocalizedName(wood.getName() + "Fence");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
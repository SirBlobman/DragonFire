package com.DragonFire.block.tree;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockStairs;

public class BlockDFWoodStairs extends BlockStairs {
    public BlockDFWoodStairs(DFWoodType wood) {
        super(DFBlocks.PLANKS.getDefaultState().withProperty(BlockDFPlanks.TYPE, wood));
        this.useNeighborBrightness = true;
        setRegistryName(wood.getName() + "_wooden_stairs");
        setUnlocalizedName("stairsWood");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
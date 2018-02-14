package com.DragonFire.block.plant;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.creative.DFTabs;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.block.BlockStairs;

public class BlockDFWoodStairs extends BlockStairs {
    public BlockDFWoodStairs(DFWoodType wood) {
        super(DFBlocks.PLANKS.getDefaultState().withProperty(BlockDFPlanks.TYPE, wood));
        this.useNeighborBrightness = true;
        setRegistryName(wood.getName() + "_wooden_stairs");
        setUnlocalizedName("stairsWood" + StringUtils.capitalize(wood.getName()));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
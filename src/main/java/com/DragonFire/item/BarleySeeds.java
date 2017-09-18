package com.DragonFire.item;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.creative.DFTabs;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class BarleySeeds extends ItemSeeds {
    public BarleySeeds() {
        super(DFBlocks.BARLEY_CROP, Blocks.FARMLAND);
        setUnlocalizedName("barley_seeds");
        setRegistryName("barley_seeds");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
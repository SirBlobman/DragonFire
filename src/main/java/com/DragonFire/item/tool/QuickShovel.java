package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemSpade;

public class QuickShovel extends ItemSpade {
    public QuickShovel(ToolMaterial tm) {
        super(tm);
        String name = tm.name().toLowerCase();
        setRegistryName(name + "_shovel");
        setUnlocalizedName(name + "_shovel");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
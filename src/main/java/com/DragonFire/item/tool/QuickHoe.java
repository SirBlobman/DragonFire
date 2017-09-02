package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemHoe;

public class QuickHoe extends ItemHoe {
    public QuickHoe(ToolMaterial tm) {
        super(tm);
        String name = tm.name().toLowerCase();
        setRegistryName(name + "_hoe");
        setUnlocalizedName(name + "_hoe");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
}
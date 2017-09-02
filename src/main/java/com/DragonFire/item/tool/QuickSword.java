package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemSword;

public class QuickSword extends ItemSword {
    public QuickSword(ToolMaterial tm) {
        super(tm);
        String name = tm.name().toLowerCase();
        setRegistryName(name + "_sword");
        setUnlocalizedName(name + "_sword");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
}
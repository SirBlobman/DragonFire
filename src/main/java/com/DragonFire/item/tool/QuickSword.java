package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemSword;

public class QuickSword extends ItemSword {
    public QuickSword(ToolMaterial tm) {this(tm, tm.name().toLowerCase() + "_sword");}
    public QuickSword(ToolMaterial tm, String customName) {
        super(tm);
        setRegistryName(customName);
        setUnlocalizedName(customName);
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
}
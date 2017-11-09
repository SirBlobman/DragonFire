package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemAxe;

public class QuickAxe extends ItemAxe {
    public QuickAxe(ToolMaterial tm) {this(tm, tm.name().toLowerCase() + "_axe");}
    public QuickAxe(ToolMaterial tm, String name) {
        super(tm, 8, -3.0F);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
}
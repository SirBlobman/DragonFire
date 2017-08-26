package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemAxe;

public class QuickAxe extends ItemAxe {
    public QuickAxe(ToolMaterial tm) {
        super(tm, 8, -3.0F);
        String name = tm.name().toLowerCase();
        setRegistryName(name + "_axe");
        setUnlocalizedName(name + "_axe");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
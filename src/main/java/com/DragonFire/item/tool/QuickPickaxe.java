package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemPickaxe;

public class QuickPickaxe extends ItemPickaxe {
    public QuickPickaxe(ToolMaterial tm) {
        super(tm);
        String name = tm.name().toLowerCase();
        setRegistryName(name + "_pickaxe");
        setUnlocalizedName(name + "_pickaxe");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
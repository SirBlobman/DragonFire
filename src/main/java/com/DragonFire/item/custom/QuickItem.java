package com.DragonFire.item.custom;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.Item;

public class QuickItem extends Item {
    public QuickItem(String name) {
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemFishingRod;

public class NetherRod extends ItemFishingRod {
    public NetherRod() {
        super();
        setRegistryName("nether_fishing_rod");
        setUnlocalizedName("nether_fishing_rod");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
}
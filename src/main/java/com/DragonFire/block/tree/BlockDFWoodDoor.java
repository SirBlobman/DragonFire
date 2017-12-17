package com.DragonFire.block.tree;

import com.DragonFire.creative.DFTabs;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;

public class BlockDFWoodDoor extends BlockDoor {
    public BlockDFWoodDoor(DFWoodType wood) {
        super(Material.WOOD);
        disableStats();
        setRegistryName(wood.getName() + "_wood_door");
        setUnlocalizedName("door" + StringUtils.capitalize(wood.getName()));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
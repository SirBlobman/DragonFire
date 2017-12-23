package com.DragonFire.block.item;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockDoor;
import net.minecraft.item.ItemDoor;

public class WoodDoorItemBlock extends ItemDoor {
    public WoodDoorItemBlock(BlockDoor door) {
        super(door);
        setRegistryName(door.getRegistryName());
        setUnlocalizedName(door.getUnlocalizedName().substring(5));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
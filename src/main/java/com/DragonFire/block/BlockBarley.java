package com.DragonFire.block;

import com.DragonFire.item.DFItems;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockBarley extends BlockCrops {
    public BlockBarley() {
        super();
        setRegistryName("barley_crop");
    }
    public Item getSeed() {return DFItems.BARLEY_SEEDS;}
    public Item getCrop() {return DFItems.BARLEY;}
}
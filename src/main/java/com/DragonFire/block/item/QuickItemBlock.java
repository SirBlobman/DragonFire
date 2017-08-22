package com.DragonFire.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class QuickItemBlock extends ItemBlock {
    public QuickItemBlock(Block block) {
        super(block);
        ResourceLocation rl = block.getRegistryName();
        String name = block.getUnlocalizedName().substring(5);
        setRegistryName(rl);
        setUnlocalizedName(name);
    }
}
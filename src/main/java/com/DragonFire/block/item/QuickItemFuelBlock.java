package com.DragonFire.block.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class QuickItemFuelBlock extends QuickItemBlock {
    private final int burnTime;
    public QuickItemFuelBlock(Block block, int burnTime) {
        super(block);
        this.burnTime = burnTime;
    }
    
    @Override
    public int getItemBurnTime(ItemStack is) {return burnTime;}
}
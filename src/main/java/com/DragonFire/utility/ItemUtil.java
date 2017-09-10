package com.DragonFire.utility;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ItemUtil extends Util {
    public static boolean isAir(ItemStack is) {
        if(is == null) return true;
        if(is == ItemStack.EMPTY) return true;
        Item i = is.getItem();
        if(i == null) return true;
        if(i instanceof ItemBlock) {
            ItemBlock ib = (ItemBlock) i;
            Block b = ib.getBlock();
            return isAir(b);
        }
        
        boolean air = (i == Items.AIR);
        return air;
    }
    
    public static boolean isAir(Block b) {
        if(b == null) return true;
        boolean air = (b == Blocks.AIR);
        return air;
    }
    
    public static ItemStack smelt(ItemStack is) {
        FurnaceRecipes furnace = FurnaceRecipes.instance();
        ItemStack cook = furnace.getSmeltingResult(is);
        return cook;
    }
}
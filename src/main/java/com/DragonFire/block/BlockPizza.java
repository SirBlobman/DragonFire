package com.DragonFire.block;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPizza extends BlockCake {
    public BlockPizza() {
        super();
        setRegistryName("pizza");
        setUnlocalizedName("pizza");
        setCreativeTab(DFTabs.FOOD_AND_DRINK);
    }
    
    @Override
    public ItemStack getItem(World w, BlockPos bp, IBlockState ibs) {
        Item i = DFBlocks.ITEM_PIZZA;
        ItemStack is = new ItemStack(i);
        return is;
    }
}
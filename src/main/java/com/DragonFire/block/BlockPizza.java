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
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public ItemStack getItem(World w, BlockPos bp, IBlockState ibs) {
        Item i = DFBlocks.PIZZA_ITEM;
        ItemStack is = new ItemStack(i);
        return is;
    }
}
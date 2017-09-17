package com.DragonFire.block;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChocolateCake extends BlockCake {
    public BlockChocolateCake() {
        super();
        setRegistryName("chocolate_cake");
        setUnlocalizedName("chocolate_cake");
        setCreativeTab(DFTabs.FOOD_AND_DRINK);
    }
    
    @Override
    public ItemStack getItem(World w, BlockPos bp, IBlockState ibs) {
        Item i = DFBlocks.CHOCOLATE_CAKE_ITEM;
        ItemStack is = new ItemStack(i);
        return is;
    }
}
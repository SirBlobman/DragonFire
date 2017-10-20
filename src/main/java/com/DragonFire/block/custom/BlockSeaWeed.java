package com.DragonFire.block.custom;

import com.DragonFire.item.DFItems;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSeaWeed extends QuickBlock {
    public BlockSeaWeed() {
        super("sea_weed");
        setSoundType(SoundType.PLANT);
    }
    
    public boolean isPassable(IBlockAccess iba, BlockPos bp) {return true;}
    public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.CUTOUT;}
    public boolean isFullCube(IBlockState ibs) {return false;}
    public boolean isOpaqueCube(IBlockState ibs) {return false;}
    
    @Override
    public Item getItemDropped(IBlockState ibs, Random rand, int fortune) {
        Item item = DFItems.SEA_WEED;
        return item;
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState ibs, RayTraceResult rtr, World ww, BlockPos bp, EntityPlayer ep) {
        Item item = DFItems.SEA_WEED;
        ItemStack is = new ItemStack(item, 1, 0);
        return is;
    }
    
    @Override
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState ibs, IBlockAccess iba, BlockPos bp, EnumFacing ef) {
        if(ef == EnumFacing.UP) return false;
        else return super.shouldSideBeRendered(ibs, iba, bp, ef);
    }
}
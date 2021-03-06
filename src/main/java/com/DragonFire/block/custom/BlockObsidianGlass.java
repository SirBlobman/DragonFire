package com.DragonFire.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockObsidianGlass extends QuickBlock {
    public BlockObsidianGlass() {
        super("obsidian_glass", Material.GLASS);
        setSoundType(SoundType.GLASS);
        setResistance(6000001.0F);
    }
    
    public BlockRenderLayer getBlockLayer() {return BlockRenderLayer.CUTOUT;}
    public boolean isFullCube(IBlockState ibs) {return false;}
    public boolean isOpaqueCube(IBlockState ibs) {return false;}
    public boolean canEntityDestroy(IBlockState ibs, IBlockAccess iba, BlockPos bp, Entity en) {return false;}
    
    @Override
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState ibs, IBlockAccess iba, BlockPos bp, EnumFacing ef) {
        IBlockState bs = iba.getBlockState(bp.offset(ef));
        Block b = bs.getBlock();
        if(ibs != bs) return true;
        if(b == this) return false;
        return ((b == this) ? false : super.shouldSideBeRendered(ibs, iba, bp, ef));
    }
}
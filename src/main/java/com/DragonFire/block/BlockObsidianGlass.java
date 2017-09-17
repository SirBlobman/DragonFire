package com.DragonFire.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
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
    public boolean canEntityDestroy(IBlockState ibs, IBlockAccess iba, BlockPos bp, Entity en) {return false;}
}
package com.DragonFire.item.custom;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.block.tree.BlockDFLeaves;
import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.creative.DFTabs;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCoconut extends Item {
    public ItemCoconut() {
        super();
        setUnlocalizedName("coconut");
        setRegistryName("coconut");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer ep, World world, BlockPos bp, EnumHand eh, EnumFacing ef, float x, float y, float z) {
        ItemStack is = ep.getHeldItem(eh);
        if(!ep.canPlayerEdit(bp.offset(ef), ef, is)) {
            return EnumActionResult.FAIL;
        } else {
            IBlockState ibs = world.getBlockState(bp);
            Block block = ibs.getBlock();
            if(block == DFBlocks.LEAVES && ibs.getValue(BlockDFLeaves.TYPE) == DFWoodType.PALM) {
                if(ef == EnumFacing.DOWN) {
                    bp = bp.offset(ef);
                    if(world.isAirBlock(bp)) {
                        IBlockState ibs1 = DFBlocks.COCONUT.getStateForPlacement(world, bp, ef, x, y, z, 0, ep, eh);
                        world.setBlockState(bp, ibs1, 10);
                        if(!ep.capabilities.isCreativeMode) {
                            is.shrink(1);
                        }
                        return EnumActionResult.SUCCESS;
                    }
                } else return EnumActionResult.FAIL;
            } return EnumActionResult.FAIL;
        }
    }
}
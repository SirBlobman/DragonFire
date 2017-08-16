package com.DragonFire.item;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.creative.DFTabs;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MummyHead extends Item {
    public MummyHead() {
        super();
        setRegistryName("mummy_head");
        setUnlocalizedName("mummy_head");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    public EnumActionResult onItemUse(EntityPlayer ep, World w, BlockPos bp, EnumHand eh, EnumFacing ef, float x, float y, float z) {
        if(ef == EnumFacing.DOWN) return EnumActionResult.FAIL;
        else {
            IBlockState ibs = w.getBlockState(bp);
            Block b = ibs.getBlock();
            if(b.isReplaceable(w, bp)) {
                ef = EnumFacing.UP;
                bp = bp.down();
            }
            
            ibs = w.getBlockState(bp);
            b = ibs.getBlock();
            boolean flag = b.isReplaceable(w, bp);
            if(!flag) {
                IBlockState ibs2 = w.getBlockState(bp);
                Material mat = ibs2.getMaterial();
                boolean solid1 = mat.isSolid();
                boolean solid2 = w.isSideSolid(bp, ef, true);
                if(!solid1 && !solid2) return EnumActionResult.FAIL;
                bp = bp.offset(ef);
            }
            
            ItemStack is = ep.getHeldItem(eh);
            if(ep.canPlayerEdit(bp, ef, is) && DFBlocks.MUMMY_HEAD.canPlaceBlockAt(w, bp)) {
                if(w.isRemote) return EnumActionResult.SUCCESS;
                else {
                    w.setBlockState(bp, DFBlocks.MUMMY_HEAD.getDefaultState().withProperty(BlockSkull.FACING, ef), 11);
                    if (ep instanceof EntityPlayerMP) CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) ep, bp, is);
                    is.shrink(1);
                    return EnumActionResult.SUCCESS;
                }
            } else return EnumActionResult.FAIL;
        }
    }
}
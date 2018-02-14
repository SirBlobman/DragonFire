package com.DragonFire.listener;

import com.DragonFire.DragonFire;
import com.DragonFire.item.DFItems;
import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=DragonFire.MODID)
public class ListenCustomItems {
    @SubscribeEvent
    public void treeAxe(BlockEvent.BreakEvent e) {
        EntityPlayer ep = e.getPlayer();
        if(ep != null) {
            ItemStack is = ep.getHeldItem(EnumHand.MAIN_HAND);
            if(is != null && !is.isEmpty()) {
                Item item = is.getItem();
                if(item == DFItems.TREE_AXE) {
                    BlockPos pos = e.getPos();
                    IBlockState ibs = e.getState();
                    Block block = ibs.getBlock();
                    if(block instanceof BlockLog) {
                        World world = e.getWorld();
                        if(!world.isRemote) {
                            List<BlockPos> list = getAdjacentTree(world, pos, 5);
                            for(BlockPos bp : list) {
                                boolean destroy = world.destroyBlock(bp, true);
                                if(destroy) is.damageItem(1, ep);
                            }
                        }
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void wetMoss(RightClickBlock e) {
        ItemStack is = e.getItemStack();
        if(!ItemUtil.isAir(is)) {
            Item item = is.getItem();
            if(item == DFItems.MOSS && is.getItemDamage() == 0) is.setItemDamage(1);
        }
    }
    
    public List<BlockPos> getAdjacentTree(World world, BlockPos pos, int radius) {
        List<BlockPos> list = Util.newList();
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        int x1 = (x - radius), y1 = (y - radius), z1 = (z - radius);
        int x2 = (x + radius), y2 = (y + radius), z2 = (z + radius);
        Iterable<BlockPos> positions = BlockPos.getAllInBox(x1, y1, z1, x2, y2, z2);
        for(BlockPos bp : positions) {
            IBlockState ibs = world.getBlockState(bp);
            Block block = ibs.getBlock();
            if(block instanceof BlockLog || block instanceof BlockLeaves) list.add(bp);
        }
        list.remove(pos);
        return list;
    }
}
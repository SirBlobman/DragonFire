package com.DragonFire.block.tile;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;
import com.DragonFire.creative.DFTabs;
import com.DragonFire.network.gui.DFGuiHandler;

import java.util.Random;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNetherBrickFurnace extends BlockFurnace {
    public BlockNetherBrickFurnace() {
        super(true);
        setRegistryName("nether_brick_furnace");
        setUnlocalizedName("nether_brick_furnace");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        TileEntityNetherBrickFurnace te = new TileEntityNetherBrickFurnace();
        return te;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, EnumFacing ef, float hitX, float hitY, float hitZ) {
        if(world.isRemote) return true;
        else {
            ep.openGui(DragonFire.INSTANCE, DFGuiHandler.NETHER_BRICK_FURNACE, world, bp.getX(), bp.getY(), bp.getZ());
            return true;
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof IInventory) {
            IInventory ii = (IInventory) tileentity;
            InventoryHelper.dropInventoryItems(worldIn, pos, ii);
            worldIn.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(worldIn, pos, state);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {return DFBlocks.ITEM_NETHER_BRICK_FURNACE;}
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {return new ItemStack(DFBlocks.NETHER_BRICK_FURNACE);}
}
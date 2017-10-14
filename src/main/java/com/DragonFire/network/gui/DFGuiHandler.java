package com.DragonFire.network.gui;

import com.DragonFire.block.tile.TileEntityNetherBrickFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class DFGuiHandler implements IGuiHandler {
    public static final int
    NETHER_BRICK_FURNACE = 0;
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer ep, World world, int x, int y, int z) {
        BlockPos bp = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(bp);
        if(id == NETHER_BRICK_FURNACE) {
            InventoryPlayer ip = ep.inventory;
            TileEntityNetherBrickFurnace nbf = (TileEntityNetherBrickFurnace) te;
            ContainerNetherBrickFurnace cnbf = new ContainerNetherBrickFurnace(ip, nbf);
            return cnbf;
        }
        
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int id, EntityPlayer ep, World world, int x, int y, int z) {
        BlockPos bp = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(bp);
        if(te instanceof TileEntityNetherBrickFurnace) {
            TileEntityNetherBrickFurnace nbf = (TileEntityNetherBrickFurnace) te;
            InventoryPlayer ip = ep.inventory;
            GuiNetherBrickFurnace gnbf = new GuiNetherBrickFurnace(ip, nbf);
            return gnbf;
        }
        
        return null;
    }
}
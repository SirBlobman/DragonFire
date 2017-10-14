package com.DragonFire.network.gui;

import com.DragonFire.block.tile.TileEntityNetherBrickFurnace;
import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNetherBrickFurnace extends Container {
    private TileEntityNetherBrickFurnace tileEntity;
    private int[] cachedFields;
    
    private static final int
    HOT_BAR_SLOTS = 9,
    PLAYER_ROWS = 3,
    PLAYER_COLUMNS = 9,
    PLAYER_SLOTS = (PLAYER_COLUMNS * PLAYER_ROWS),
    VANILLA_SLOTS = (PLAYER_SLOTS + HOT_BAR_SLOTS),
    
    INPUT_SLOTS = 1,
    OUTPUT_SLOTS = 1,
    FURNACE_SLOTS = (INPUT_SLOTS + OUTPUT_SLOTS),
    
    VANILLA_FIRST_I = 0,
    INPUT_FIRST_I = (VANILLA_FIRST_I + VANILLA_SLOTS),
    
    INPUT_FIRST = 0,
    OUTPUT_FIRST = (INPUT_FIRST + INPUT_SLOTS),
    
    X_SPACING = 18,
    Y_SPACING = 18,
    HOT_BAR_X = 8,
    HOT_BAR_Y = 142,
    
    PLAYER_X = 8,
    PLAYER_Y = 84,
    
    INPUT_X = 47,
    INPUT_Y = 34,
    
    OUTPUT_X = 109,
    OUTPUT_Y = 35;
    
    public ContainerNetherBrickFurnace(InventoryPlayer ip, TileEntityNetherBrickFurnace te) {
        this.tileEntity = te;
        
        for(int x = 0; x < HOT_BAR_SLOTS; x++) {
            Slot slot = new Slot(ip, x, HOT_BAR_X + X_SPACING * x, HOT_BAR_Y);
            addSlotToContainer(slot);
        }
        
        for(int y = 0; y < PLAYER_ROWS; y++) {
            for(int x = 0; x < PLAYER_COLUMNS; x++) {
                int i = (HOT_BAR_SLOTS + y * PLAYER_COLUMNS + x);
                int xp = (PLAYER_X + x * X_SPACING);
                int yp = (PLAYER_Y + y * Y_SPACING);
                Slot slot = new Slot(ip, i, xp, yp);
                addSlotToContainer(slot);
            }
        }
        
        for(int y = 0; y < INPUT_SLOTS; y++) {
            int i = (y + INPUT_FIRST);
            SlotInput slot = new SlotInput(tileEntity, i, INPUT_X, INPUT_Y + Y_SPACING * y);
            addSlotToContainer(slot);
        }
        
        for(int y = 0; y < OUTPUT_SLOTS; y++) {
            int i = (y + OUTPUT_FIRST);
            SlotOutput slot = new SlotOutput(tileEntity, i, OUTPUT_X, OUTPUT_Y + Y_SPACING * y);
            addSlotToContainer(slot);
        }
    }
    
    public boolean canInteractWith(EntityPlayer ep) {return tileEntity.isUsableByPlayer(ep);}
    public void updateProgressBar(int id, int data) {tileEntity.setField(id, data);}
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer ep, int i) {
        Slot slot = inventorySlots.get(i);
        if(slot == null || !slot.getHasStack()) return null;
        
        ItemStack is = slot.getStack();
        ItemStack copy = is.copy();
        
        boolean b1 = (i >= VANILLA_FIRST_I);
        boolean b2 = (i < VANILLA_FIRST_I + VANILLA_SLOTS);
        boolean b3 = (i > INPUT_FIRST_I);
        boolean b4 = (i < INPUT_FIRST_I + FURNACE_SLOTS);
        if(b1 && b2) {
            if(ItemUtil.smelt(is) != null && !ItemUtil.smelt(is).isEmpty()) {
                int i1 = INPUT_FIRST_I;
                int i2 = (INPUT_FIRST_I + INPUT_SLOTS);
                if(!mergeItemStack(is, i1, i2, false)) return ItemStack.EMPTY;
            } else return ItemStack.EMPTY;
        } else if(b3 && b4) {
            int i5 = VANILLA_FIRST_I;
            int i6 = (VANILLA_FIRST_I + VANILLA_SLOTS);
            if(!mergeItemStack(is, i5, i6, false)) return ItemStack.EMPTY;
        } else {
            Util.print("Invalid Slot Index:" + i);
            return ItemStack.EMPTY;
        }
        
        if(is.getCount() == 0) slot.putStack(ItemStack.EMPTY);
        else slot.onSlotChanged();
        
        slot.onTake(ep, is);
        return copy;
    }
    
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        boolean allChanged = false;
        boolean fieldChanged[] = new boolean[tileEntity.getFieldCount()];
        
        if(cachedFields == null) {
            cachedFields = new int[tileEntity.getFieldCount()];
            allChanged = true;
        }
        
        for(int i = 0; i < cachedFields.length; ++i) {
            if(allChanged || cachedFields[i] != tileEntity.getField(i)) {
                cachedFields[i] = tileEntity.getField(i);
                fieldChanged[i] = true;
            }
        }
        
        for(int i = 0; i < listeners.size(); ++i) {
            IContainerListener icl = listeners.get(i);
            for(int j = 0; j < tileEntity.getFieldCount(); ++j) {
                if(fieldChanged[j]) icl.sendWindowProperty(this, j, cachedFields[j]);
            }
        }
    }
    
    public static class SlotInput extends Slot {
        public SlotInput(TileEntityNetherBrickFurnace te, int i, int x, int y) {super(te, i, x, y);}      
        public boolean isItemValid(ItemStack is) {return TileEntityNetherBrickFurnace.isValidInput(is);}
    }
    
    public static class SlotOutput extends Slot {
        public SlotOutput(IInventory ii, int i, int x, int y) {super(ii, i, x, y);}
        public boolean isItemValid(ItemStack is) {return false;}
    }
}
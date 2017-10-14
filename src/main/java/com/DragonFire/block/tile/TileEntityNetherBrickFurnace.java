package com.DragonFire.block.tile;

import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityNetherBrickFurnace extends TileEntity implements IInventory, ITickable {
    public static final int
    INPUT_SLOTS = 1,
    OUTPUT_SLOTS = 1,
    TOTAL_SLOTS = (INPUT_SLOTS + OUTPUT_SLOTS),
    
    FIRST_INPUT = 0,
    FIRST_OUTPUT = (FIRST_INPUT + INPUT_SLOTS);
    
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);
    private short cookTime = 0;
    
    public double fuelRemainingFraction() {return 1.0F;}
    public String getName() {return "dragonfire.container.nether_brick_furnace";}
    public boolean hasCustomName() {return false;}
    public int getSizeInventory() {return itemStacks.size();}
    public ItemStack getStackInSlot(int slot) {return itemStacks.get(slot);}
    public ItemStack removeStackFromSlot(int i) {return null;}
    public int getInventoryStackLimit() {return 64;}
    public void openInventory(EntityPlayer ep) {}
    public void closeInventory(EntityPlayer ep) {}
    public boolean isItemValidForSlot(int slot, ItemStack is) {return false;}
    public int getFieldCount() {return 1;}
    public void clear() {}
    
    private short getCookTimeNeeded() {
        if(isInNether()) return 20;
        else return 200;
    }
    
    public boolean isInNether() {
        int id = world.provider.getDimension();
        return (id == -1);
    }
    
    @Override
    public boolean isEmpty() {
        if(itemStacks == null) return true;
        else {
            boolean empty = true;
            for(ItemStack is : itemStacks) {
                if(is != null && !is.isEmpty()) empty = false;
            } return empty;
        }
    }
    
    public double cookTimeCompleteFraction() {
        double fraction = ((double) cookTime / (double) getCookTimeNeeded());
        return MathHelper.clamp(fraction, 0.0D, 1.0F);
    }
    
    @Override
    public ITextComponent getDisplayName() {
        String name = getName();
        TextComponentTranslation text = new TextComponentTranslation(name);
        return text;
    }
    
    @Override
    public ItemStack decrStackSize(int i, int count) {
        ItemStack is = getStackInSlot(i);
        if(is == null || is.isEmpty()) return ItemStack.EMPTY;
        
        ItemStack removed;
        if(is.getCount() <= count) {
            removed = is;
            setInventorySlotContents(i, ItemStack.EMPTY);
        } else {
            removed = is.splitStack(count);
            if(is.getCount() == 0) setInventorySlotContents(i, ItemStack.EMPTY);
        }
        
        markDirty();
        return removed;
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack is) {
        itemStacks.set(i, is);
        if(is != null && is.getCount() > getInventoryStackLimit()) is.setCount(getInventoryStackLimit());
        markDirty();
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer ep) {
        if(world.getTileEntity(pos) != this) return false;
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.5D;
        double z = pos.getZ() + 0.5D;
        
        double distance = ep.getDistanceSq(x, y, z);
        return (distance < 64.0D);
    }
    
    private final byte
    FIELD_ID = 0;
    
    @Override
    public int getField(int id) {
        if(id == FIELD_ID) return cookTime;
        Util.print("Invalid Field ID: " + id);
        return 0;
    }
    
    @Override
    public void setField(int id, int value) {
        if(id == FIELD_ID) cookTime = (short) value;
        else Util.print("Invalid Field ID:" + id);
    }
    
    @Override
    public void update() {
        if(canSmelt()) {
            cookTime += 2;        
            if(cookTime >= getCookTimeNeeded()) {
                smelt();
                cookTime = 0;
            }
        } else cookTime = 0;
        markDirty();
    }
    
    private boolean canSmelt() {
        if(itemStacks.get(0) == null || itemStacks.get(0).isEmpty()) return false;
        else {
            ItemStack result = getResult(itemStacks.get(0));
            if(result == null || result.isEmpty()) return false;
            else {
                ItemStack is1 = itemStacks.get(1);
                if(is1 == null || is1.isEmpty()) return true;
                else if(!is1.isItemEqual(result)) return false;
                else if(is1.getCount() + result.getCount() <= getInventoryStackLimit()) return true;
                else return is1.getCount() + result.getCount() <= result.getMaxStackSize();
            }
        }
    }
    
    public void smelt() {
        if(canSmelt()) {
            ItemStack is = itemStacks.get(0);
            ItemStack is1 = getResult(is);
            ItemStack is2 = itemStacks.get(1);
            if(is2.isEmpty()) itemStacks.set(1, is1.copy());
            else if(is2.getItem() == is1.getItem()) is2.grow(is1.getCount());
            is.shrink(1);
        }
    }
    
    public static ItemStack getResult(ItemStack is) {return ItemUtil.smelt(is);}
    public static boolean isValidInput(ItemStack is) {return true;}
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList tagList = new NBTTagList();
        for(int i = 0; i < itemStacks.size(); ++i) {
            if(itemStacks.get(i) != null && !itemStacks.get(i).isEmpty()) {
                NBTTagCompound nbt1 = new NBTTagCompound();
                nbt1.setByte("Slot", (byte) i);
                itemStacks.get(i).writeToNBT(nbt1);
                tagList.appendTag(nbt1);
            }
        }
        
        nbt.setTag("Items", tagList);
        nbt.setShort("Cook Time", cookTime);
        return nbt;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList tagList = nbt.getTagList("Items", 10);
        itemStacks.clear();
        for(int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound nbt1 = tagList.getCompoundTagAt(i);
            byte slot = nbt1.getByte("Slot");
            if(slot >= 0 && slot < itemStacks.size()) itemStacks.set(slot, new ItemStack(nbt1));
        }
        
        cookTime = nbt.getShort("cookTime");
    }
    
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        int meta = 0;
        SPacketUpdateTileEntity sput = new SPacketUpdateTileEntity(pos, meta, nbt);
        return sput;
    }
    
    @Override
    public void onDataPacket(NetworkManager nm, SPacketUpdateTileEntity sput) {
        NBTTagCompound nbt = sput.getNbtCompound();
        readFromNBT(nbt);
    }
}
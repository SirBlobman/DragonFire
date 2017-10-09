package com.DragonFire.item.armor.backpack;

import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryBackpack extends InventoryBasic {
    private final ItemStack backpack;
    public InventoryBackpack(ItemStack backpack) {
        super("container.backpack", false, 54);
        this.backpack = backpack;
   }
    
    public boolean isUseableByPlayer(EntityPlayer ep) {return true;}

    public void loadInventoryFromNBT(NBTTagList tagList) {
        for(int i = 0; i < getSizeInventory(); ++i) {
            ItemStack empty = ItemStack.EMPTY;
            setInventorySlotContents(i, empty);
        }

        for(int k = 0; k < tagList.tagCount(); ++k) {
            NBTTagCompound nbt = tagList.getCompoundTagAt(k);
            int j = nbt.getByte("Slot") & 255;
            if(j >= 0 && j < getSizeInventory()) {
                ItemStack is = new ItemStack(nbt);
                setInventorySlotContents(j, is);
            }
        }
    }

    public NBTTagList saveInventoryToNBT(EntityPlayer ep) {
        NBTTagList tagList = new NBTTagList();
        List<ItemStack> toDrop = Util.newList();
        for(int i = 0; i < getSizeInventory(); ++i) {
            ItemStack is = getStackInSlot(i);
            if(!is.isEmpty()) {
                if(is.getItem() instanceof DyableBackpack) toDrop.add(is);
                else {
                    NBTTagCompound nbt = new NBTTagCompound();
                    nbt.setByte("Slot", (byte) i);
                    is.writeToNBT(nbt);
                    tagList.appendTag(nbt);
                }
            }
        } 
        for(ItemStack is : toDrop) ep.entityDropItem(is, 0);
        return tagList;
    }

    @Override
    public void closeInventory(EntityPlayer ep) {
        if(!backpack.isEmpty()) {
            NBTTagCompound nbt = backpack.getTagCompound();
            if(nbt == null) {
                nbt = new NBTTagCompound();
                backpack.setTagCompound(nbt);
            }
            nbt.setTag("Inventory", saveInventoryToNBT(ep));
            backpack.setTagCompound(nbt);
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack is) {
        Item i = is.getItem();
        if(i instanceof DyableBackpack) return false;
        else return super.isItemValidForSlot(slot, is);
    }
    
    @Override
    public ItemStack addItem(ItemStack is) {
        if(!is.isEmpty()) {
            Item i = is.getItem();
            if(i instanceof DyableBackpack) return is;
        } return super.addItem(is);
    }
}
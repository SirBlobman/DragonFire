package com.DragonFire.block.item;

import com.DragonFire.block.DFBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemMummyHead extends QuickItemBlock {
    public ItemMummyHead() {super(DFBlocks.MUMMY_HEAD);}
    
    @Override
    public boolean isValidArmor(ItemStack is, EntityEquipmentSlot slot, Entity e) {
        if(slot == EntityEquipmentSlot.HEAD) return true;
        else return false;
    }
}
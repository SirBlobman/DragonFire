package com.DragonFire.block.item;

import com.DragonFire.block.MummyHead;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemMummyHead extends QuickItemBlock {
    public ItemMummyHead(MummyHead mh) {super(mh);}
    
    @Override
    public boolean isValidArmor(ItemStack is, EntityEquipmentSlot slot, Entity e) {
        if(slot == EntityEquipmentSlot.HEAD) return true;
        else return false;
    }
}
package com.DragonFire.block.item;

import com.DragonFire.block.MummyHead;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMummyHead extends ItemBlock {
    public ItemMummyHead(MummyHead mh) {
        super(mh);
        setRegistryName("mummy_head");
        setUnlocalizedName("mummy_head");
    }
    
    @Override
    public boolean isValidArmor(ItemStack is, EntityEquipmentSlot slot, Entity e) {
        if(slot == EntityEquipmentSlot.HEAD) return true;
        else return false;
    }
}
package com.DragonFire.item.armor;

import com.DragonFire.creative.DFTabs;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class QuickArmor extends ItemArmor {
    public QuickArmor(String type, ArmorMaterial am, EntityEquipmentSlot slot) {
        super(am, ((slot == EntityEquipmentSlot.LEGS) ? 2 : 1), slot);
        String name = type + "_";
        switch(slot) {
            case HEAD: 
                name = name + "helmet";
                break;
            case CHEST:
                name = name + "chestplate";
                break;
            case LEGS:
                name = name + "leggings";
                break;
            case FEET:
                name = name + "boots";
                break;
            case MAINHAND:
                name = name + "glove";
                break;
            case OFFHAND:
                name = name + "pendant";
                break;
            default:
                name = name + "armor";
                break;
        }
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(DFTabs.ARMOR);
    }
}
package com.DragonFire.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentAutoSmelt extends Enchantment {
    public EnchantmentAutoSmelt() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setRegistryName("auto_smelt");
        setName("auto_smelt");
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        return ((e != Enchantments.SILK_TOUCH) && (e != this));
    }
    
    @Override
    public int getMinEnchantability(int level) {return 15;}
    public int getMaxEnchantability(int level) {return 65;}
}
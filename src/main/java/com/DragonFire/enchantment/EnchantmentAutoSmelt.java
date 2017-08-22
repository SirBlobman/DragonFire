package com.DragonFire.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentAutoSmelt extends Enchantment {
    public EnchantmentAutoSmelt() {
        super(Rarity.RARE, EnumEnchantmentType.DIGGER, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setRegistryName("auto_smelt");
        setName("auto_smelt");
    }
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        return super.canApplyTogether(e) && (e != Enchantments.SILK_TOUCH);
    }
}
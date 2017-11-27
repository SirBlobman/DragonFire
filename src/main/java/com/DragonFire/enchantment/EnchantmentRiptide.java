package com.DragonFire.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentRiptide extends Enchantment {
    public EnchantmentRiptide() {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setName("riptide");
        setRegistryName("riptide");
    }
    
    public int getMaxLevel() {return 3;}
    public int getMinEnchantability(int level) {return 15;}
    public int getMaxEnchantability(int level) {return 65;} 
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        if(e instanceof EnchantmentDamage || e == DFEnchants.BANE_OF_HUMANOIDS) return false;
        else return super.canApplyTogether(e);
    }
    
    public static float getDamageWithEnchant(Entity hit, int level, float originalDamage) {
        if(hit.isInWater()) {
            float newDamage = (originalDamage + (2 * level));
            return newDamage;
        } else return originalDamage;
    }
}
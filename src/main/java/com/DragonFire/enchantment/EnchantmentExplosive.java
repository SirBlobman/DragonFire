package com.DragonFire.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.world.World;

public class EnchantmentExplosive extends Enchantment {
    public EnchantmentExplosive() {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setName("explosive");
        setRegistryName("explosive");
    }
    
    public int getMaxLevel() {return 2;}
    public int getMinEnchantability(int level) {return 15;}
    public int getMaxEnchantability(int level) {return 65;} 
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        if(e instanceof EnchantmentDamage || e == DFEnchants.BANE_OF_HUMANOIDS || e == DFEnchants.RIPTIDE) return false;
        else return super.canApplyTogether(e);
    }
    
    public static void onEntityDeath(Entity en, int level) {
        double random = Math.random();
        double chance = (random * 100.0D);
        if(chance <= 50.0D) {
            World world = en.getEntityWorld();
            double x = en.posX;
            double y = en.posY;
            double z = en.posZ;
            float strength = (level * 1.1F);
            world.createExplosion(null, x, y, z, strength, true);
        } else return;
    }
}
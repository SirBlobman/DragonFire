package com.DragonFire.enchantment;

import com.DragonFire.config.DFConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EnchantmentBaneOfHumanoids extends Enchantment {
    public EnchantmentBaneOfHumanoids() {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setRegistryName("bane_of_humanoids");
        setName("bane_of_humanoids");
    }
    
    public int getMaxLevel() {return 5;}
    public int getMinEnchantability(int level) {return 15;}
    public int getMaxEnchantability(int level) {return 65;} 
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        if(e == this) return false;
        else if(e == Enchantments.BANE_OF_ARTHROPODS || e == Enchantments.SMITE || e == Enchantments.SHARPNESS) return false;
        else return super.canApplyTogether(e);
    }
    
    public static float getDamageWithEnchant(Entity hit, int level, float originalDamage) {
        if(isHumanoid(hit)) {
            float newDamage = (originalDamage + (2 * level));
            return newDamage;
        } else return originalDamage;
    }
    
    private static boolean isHumanoid(Entity en) {
        Class<? extends Entity> clazz = en.getClass();
        EntityEntry ee = EntityRegistry.getEntry(clazz);
        if(ee != null) {
            ResourceLocation rl = ee.getRegistryName();
            String name = rl.toString();
            if(DFConfig.HUMANOIDS.contains(name)) return true;
        } return false;
    }
}
package com.DragonFire.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFEnchants {
    public static EnchantmentAutoSmelt AUTO_SMELT = new EnchantmentAutoSmelt();
    public static EnchantmentExtinguish EXTINGUISH = new EnchantmentExtinguish();
    public static EnchantmentBaneOfHumanoids BANE_OF_HUMANOIDS = new EnchantmentBaneOfHumanoids();
    public static EnchantmentPickup PICKUP = new EnchantmentPickup();
    public static EnchantmentRiptide RIPTIDE = new EnchantmentRiptide();
    public static EnchantmentExplosive EXPLOSIVE = new EnchantmentExplosive();
    public static EnchantmentLifeSteal LIFE_STEAL = new EnchantmentLifeSteal();
    
    public static void register(IForgeRegistry<Enchantment> ifr) {
        ifr.registerAll(
            AUTO_SMELT, EXTINGUISH, BANE_OF_HUMANOIDS, PICKUP, 
            RIPTIDE, EXPLOSIVE, LIFE_STEAL
        );
    }
}
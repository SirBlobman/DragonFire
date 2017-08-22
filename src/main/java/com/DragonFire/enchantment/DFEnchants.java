package com.DragonFire.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFEnchants {
    public static EnchantmentAutoSmelt AUTO_SMELT = new EnchantmentAutoSmelt();
    
    public static void register(IForgeRegistry<Enchantment> ifr) {
        ifr.register(AUTO_SMELT);
    }
}
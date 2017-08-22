package com.DragonFire.potion.type;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFPotionTypes {
    public static final PotionType LEVITATION = new QuickPotionType("levitation", "levitation", new PotionEffect(MobEffects.LEVITATION, 900, 0));
    public static final PotionType LONG_LEVITATION = new QuickPotionType("long_levitation", "levitation", new PotionEffect(MobEffects.LEVITATION, 1800, 0));
    public static final PotionType STRONG_LEVITATION = new QuickPotionType("strong_levitation", "levitation", new PotionEffect(MobEffects.LEVITATION, 432, 1));
    
    public static void register(IForgeRegistry<PotionType> ifr) {
        ifr.registerAll(LEVITATION, LONG_LEVITATION, STRONG_LEVITATION);
    }
}

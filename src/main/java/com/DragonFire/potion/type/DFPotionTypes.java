package com.DragonFire.potion.type;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFPotionTypes {
    public static final PotionType LEVITATION = new QuickPotionType("levitation", new PotionEffect(MobEffects.LEVITATION, 100, 10));
    
    public static void register(IForgeRegistry<PotionType> ifr) {
        ifr.register(LEVITATION);
    }
}

package com.DragonFire.potion.effect;

import net.minecraft.potion.Potion;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFPotions {
    public static final Potion CONFUSION = new PotionConfusion();
    
    public static void register(IForgeRegistry<Potion> ifr) {
        ifr.register(CONFUSION);
    }
}
package com.DragonFire.potion.effect;

import com.DragonFire.utility.Util;

import net.minecraft.potion.Potion;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFPotions {
    public static final Potion CONFUSION = new PotionConfusion();
    public static final Potion RADIATION = new PotionRadiation();
    public static final Potion SCARECROW = new QuickPotion("scarecrow", true, Util.getRGB(255, 0, 0), 0, 2);
    
    public static void register(IForgeRegistry<Potion> ifr) {
        ifr.registerAll(CONFUSION, RADIATION, SCARECROW);
    }
}
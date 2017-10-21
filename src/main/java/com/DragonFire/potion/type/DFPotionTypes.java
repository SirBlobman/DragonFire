package com.DragonFire.potion.type;

import com.DragonFire.potion.effect.DFPotions;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFPotionTypes {
    public static final PotionType LEVITATION = new QuickPotionType("levitation", "levitation", new PotionEffect(MobEffects.LEVITATION, 900, 0));
    public static final PotionType LONG_LEVITATION = new QuickPotionType("long_levitation", "levitation", new PotionEffect(MobEffects.LEVITATION, 1800, 0));
    public static final PotionType STRONG_LEVITATION = new QuickPotionType("strong_levitation", "levitation", new PotionEffect(MobEffects.LEVITATION, 432, 1));

    public static final PotionType BLINDING = new QuickPotionType("blindness", "blindness", new PotionEffect(MobEffects.BLINDNESS, 900, 0));
    public static final PotionType LONG_BLINDING = new QuickPotionType("long_blindness", "blindness", new PotionEffect(MobEffects.BLINDNESS, 1800, 0));
    public static final PotionType STRONG_BLINDING = new QuickPotionType("strong_blindness", "blindness", new PotionEffect(MobEffects.BLINDNESS, 432, 1));
    
    public static final PotionType DECAY = new QuickPotionType("wither", "wither", new PotionEffect(MobEffects.WITHER, 900, 0));
    
    public static final PotionType RADIATION = new QuickPotionType("radiation", "radiation", new PotionEffect(DFPotions.RADIATION, 900, 0));
    
    public static void register(IForgeRegistry<PotionType> ifr) {
        ifr.registerAll(
            LEVITATION, LONG_LEVITATION, STRONG_LEVITATION,
            BLINDING, LONG_BLINDING, STRONG_BLINDING,
            DECAY, 
            RADIATION
        );
    }
}

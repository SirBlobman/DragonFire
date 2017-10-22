package com.DragonFire.potion.effect;

import com.DragonFire.utility.Util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionRadiation extends QuickPotion {
    private static final DamageSource RADIATION = new DamageSource("radiation").setDamageBypassesArmor();
    public PotionRadiation() {super("radiation", true, Util.getRGB(200, 244, 66), 0, 1);}
    public boolean isReady(int duration, int amplifier) {return (duration % 20 == 0) && (duration > 0);}
    
    @Override
    public void performEffect(EntityLivingBase elb, int amplifier) {
        elb.attackEntityFrom(RADIATION, 2.0F * (amplifier + 1));
    }
}
package com.DragonFire.potion.effect;

import com.DragonFire.utility.Util;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.entity.EntityLivingBase;

public class PotionConfusion extends QuickPotion {
    public PotionConfusion() {super("confusion", true, Util.getRGB(0, 255, 0), 0, 0);}
    public boolean isReady(int duration, int amplifier) {return (duration > 0);}
    
    @Override
    public void performEffect(EntityLivingBase elb, int amplifier) {
        double amp = (amplifier + 1.01);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        elb.setVelocity(random.nextDouble(-amp, +amp), 0, random.nextDouble(-amp, +amp));
    }
}
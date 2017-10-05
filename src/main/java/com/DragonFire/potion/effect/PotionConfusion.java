package com.DragonFire.potion.effect;

import com.DragonFire.utility.Util;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.entity.EntityLivingBase;

public class PotionConfusion extends QuickPotion {
    public PotionConfusion() {super("confusion", true, Util.getRGB(244, 124, 12), 0, 0);}
    public boolean isReady(int duration, int amplifier) {return (duration % 2 == 0) && (duration > 0);}
    
    @Override
    public void performEffect(EntityLivingBase elb, int amplifier) {
        double amp = (amplifier + 1.01);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        elb.motionX = (0.2 * random.nextDouble(-amp, +amp));
        elb.motionZ = (0.2 * random.nextDouble(-amp, +amp));
    }
}
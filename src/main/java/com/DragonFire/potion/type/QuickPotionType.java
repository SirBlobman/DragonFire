package com.DragonFire.potion.type;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

public class QuickPotionType extends PotionType {
    public QuickPotionType(String registry, String name, PotionEffect pe) {
        super(name, pe);
        setRegistryName(registry);
    }
}
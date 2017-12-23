package com.DragonFire.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DiamondApple extends QuickFood {
    public DiamondApple() {
        super("diamond_apple", 4, 9.6F, false);
        setAlwaysEdible();
    }
    
    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer ep) {
        if(!w.isRemote) {
            PotionEffect regen2 = new PotionEffect(MobEffects.REGENERATION, 90 * 20, 1, false, true);
            PotionEffect resis3 = new PotionEffect(MobEffects.RESISTANCE, 90 * 20, 2, false, true);
            PotionEffect speed2 = new PotionEffect(MobEffects.SPEED, 90 * 20, 1, false, true);
            PotionEffect fresi1 = new PotionEffect(MobEffects.FIRE_RESISTANCE, 30 * 20, 0, false, true);
            PotionEffect absrb1 = new PotionEffect(MobEffects.ABSORPTION, 60 * 20, 1, false, true);
            ep.addPotionEffect(regen2); ep.addPotionEffect(resis3); ep.addPotionEffect(speed2);
            ep.addPotionEffect(fresi1); ep.addPotionEffect(absrb1);
        }
    }
}
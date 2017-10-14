package com.DragonFire.item.food;

import com.DragonFire.creative.DFTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SugarCookie extends QuickFood {
    public SugarCookie() {
        super("sugar_cookie", 2, 0.4F, false);
        setCreativeTab(DFTabs.COOKIES);
    }
    
    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer ep) {
        super.onFoodEaten(is, w, ep);
        PotionEffect jump = new PotionEffect(MobEffects.JUMP_BOOST, 15 * 20, 0, true, false);
        PotionEffect speed = new PotionEffect(MobEffects.SPEED, 15 * 20, 0, true, false);
        ep.addPotionEffect(jump);
        ep.addPotionEffect(speed);
    }
}
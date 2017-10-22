package com.DragonFire.item.drink;

import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpoiledMilk extends QuickDrink {
    public SpoiledMilk() {
        super("spoiled_milk", 64, 2);
        setContainerItem(Items.BUCKET);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack is, World world, EntityLivingBase elb) {
        List<PotionEffect> potionEffects = Util.newList(elb.getActivePotionEffects());
        for(PotionEffect pe : potionEffects) {
            Potion p = pe.getPotion();
            if(p.isBadEffect()) elb.removePotionEffect(p);
        }
        PotionEffect nausea = new PotionEffect(MobEffects.NAUSEA, 20 * 60, 2, true, false);
        elb.addPotionEffect(nausea);
        return super.onItemUseFinish(is, world, elb);
    }
}
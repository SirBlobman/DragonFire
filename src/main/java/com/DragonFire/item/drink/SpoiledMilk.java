package com.DragonFire.item.drink;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class SpoiledMilk extends QuickDrink {
    public SpoiledMilk() {
        super("spoiled_milk", 64);
        setContainerItem(Items.BUCKET);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack is, World world, EntityLivingBase elb) {
        for(PotionEffect pe : elb.getActivePotionEffects()) {
            Potion p = pe.getPotion();
            if(p.isBadEffect()) elb.removePotionEffect(p);
        }
        PotionEffect nausea = new PotionEffect(MobEffects.NAUSEA, 20 * 60, 2, true, false);
        elb.addPotionEffect(nausea);
        return super.onItemUseFinish(is, world, elb);
    }
}
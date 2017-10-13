package com.DragonFire.item.drink;

import com.DragonFire.potion.effect.DFPotions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Beer extends QuickDrink {
    private static final DamageSource BEER = new DamageSource("drunk").setDamageAllowedInCreativeMode().setDamageBypassesArmor();
    public Beer() {
        super("beer", 32, 1);
        setContainerItem(Items.GLASS_BOTTLE);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack is, World w, EntityLivingBase elb) {
        if(elb instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer) elb;
            NBTTagCompound nbt = ep.getEntityData();
            int beersDrunk = nbt.getInteger("BeersDrunk");
            nbt.setInteger("BeersDrunk", beersDrunk + 1);
            if(beersDrunk > 2) {
                PotionEffect pe = new PotionEffect(MobEffects.NAUSEA, 600, 1, false, true);
                ep.addPotionEffect(pe);
            }
            
            if(beersDrunk > 5) {
                PotionEffect pe = new PotionEffect(MobEffects.BLINDNESS, 600, 1, false, true);
                ep.addPotionEffect(pe);
            }
            
            if(beersDrunk > 8) {
                PotionEffect pe = new PotionEffect(DFPotions.CONFUSION, 600, 1, false, true);
                ep.addPotionEffect(pe);
            }
            
            if(beersDrunk > 9) {
                nbt.setInteger("BeersDrunk", 0);
                ep.attackEntityFrom(BEER, Float.MAX_VALUE);
            }
        }
        return super.onItemUseFinish(is, w, elb);
    }
}
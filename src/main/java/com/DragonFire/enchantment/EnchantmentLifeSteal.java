package com.DragonFire.enchantment;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EnchantmentLifeSteal extends Enchantment {
    public EnchantmentLifeSteal() {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setName("life_steal");
        setRegistryName("life_steal");
    }
    
    public int getMaxLevel() {return 4;}
    public int getMinEnchantability(int level) {return 15;}
    public int getMaxEnchantability(int level) {return 65;} 
    
    @Override
    public boolean canApplyTogether(Enchantment e) {
        if(e instanceof EnchantmentDamage || e == DFEnchants.BANE_OF_HUMANOIDS || e == DFEnchants.RIPTIDE) return false;
        else return super.canApplyTogether(e);
    }
    
    public static void onEntityDeath(EntityLivingBase killed, EntityLivingBase killer, int level) {
        double random = Math.random();
        double chance = (random * 100.0D);
        
        switch(level) {
            case 1: 
            case 2:
            case 3: {
                if(chance <= 5.0D) {
                    killer.heal(level);
                }
                break;
            }
            case 4:
            default: {
                if(chance <= 5.0D) {
                    killer.heal(level);
                    double range = 5;
                    AxisAlignedBB aabb = new AxisAlignedBB(-range, -range, -range, range, range, range);
                    World world = killed.getEntityWorld();
                    List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(killer, aabb);
                    for(Entity en : list) {
                        if(en instanceof EntityLivingBase) {
                            EntityLivingBase elb = (EntityLivingBase) en;
                            PotionEffect poison = new PotionEffect(MobEffects.POISON, 20 * level);
                            elb.addPotionEffect(poison);
                        }
                    }
                }
                break;
            }
        }
    }
}
package com.DragonFire.entity.living;

import com.DragonFire.entity.loot.DFLootTables;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityJungleSpider extends EntityCaveSpider {
    public EntityJungleSpider(World world) {
        super(world);
        setSize(0.35F, 0.25F);
    }

    @Override
    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity en) {
        if(super.attackEntityAsMob(en)) {
            if(en instanceof EntityLivingBase) {
                int i = 0;
                if(world.getDifficulty() == EnumDifficulty.NORMAL) i = 7;
                else if(world.getDifficulty() == EnumDifficulty.HARD) i = 15;
                if(i > 0) {
                    EntityLivingBase elb = (EntityLivingBase) en;
                    PotionEffect pe1 = new PotionEffect(MobEffects.POISON, i * 20, 0);
                    PotionEffect pe2 = new PotionEffect(MobEffects.NAUSEA, i * 20, 0);
                    elb.addPotionEffect(pe1);
                    elb.addPotionEffect(pe2);
                }
            } return true;
        } else return false;
    }
    
    @Override
    public ResourceLocation getLootTable() {
        return DFLootTables.JUNGLE_SPIDER;
    }
}
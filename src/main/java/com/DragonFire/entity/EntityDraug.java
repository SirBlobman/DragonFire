package com.DragonFire.entity;

import com.DragonFire.entity.loot.DFLootTables;
import com.DragonFire.potion.effect.DFPotions;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDraug extends AbstractSkeleton {
    public EntityDraug(World world) {super(world);}

    public ResourceLocation getLootTable() {return DFLootTables.DRAUG;}
    public SoundEvent getAmbientSound() {return SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT;}
    public SoundEvent getHurtSound(DamageSource ds) {return SoundEvents.ENTITY_WITHER_SKELETON_HURT;}
    public SoundEvent getDeathSound() {return SoundEvents.ENTITY_WITHER_SKELETON_DEATH;}
    public SoundEvent getStepSound() {return SoundEvents.ENTITY_WITHER_SKELETON_STEP;}
    public boolean canBreatheUnderwater() {return true;}
    public boolean getCanSpawnHere() {return true;}
    public boolean isPushedByWater() {return false;}
    public boolean isNotColliding() {return world.checkNoEntityCollision(this.getEntityBoundingBox(), this);}
    public boolean canDespawn() {return true;}

    @Override
    public void initEntityAI() {
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityIronGolem.class, true));
    }

    @Override
    public EntityArrow getArrow(float f) {
        EntityArrow ea = super.getArrow(f);
        if (ea instanceof EntityTippedArrow) {
            EntityTippedArrow eta = (EntityTippedArrow) ea;
            PotionEffect corrosion = new PotionEffect(DFPotions.CORROSION, 600);
            eta.addEffect(corrosion);
        }
        return ea;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (world.isRemote && !isInWater()) {
            for (int i = 0; i < 2; ++i) {
                world.spawnParticle(EnumParticleTypes.DRIP_WATER, posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
            }
        }
    }
}
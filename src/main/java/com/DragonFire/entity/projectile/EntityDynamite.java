package com.DragonFire.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDynamite extends EntityThrowable {
    private static final DataParameter<Boolean> NUCLEAR = EntityDataManager.createKey(EntityDynamite.class, DataSerializers.BOOLEAN);
    public EntityDynamite(World w) {super(w);}
    public EntityDynamite(World w, EntityLivingBase elb) {super(w, elb);}
    public EntityDynamite(World w, double x, double y, double z) {super(w, x, y, z);}
    
    @Override
    public void entityInit() {
        super.entityInit();
        dataManager.register(NUCLEAR, false);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        dataManager.set(NUCLEAR, nbt.getBoolean("nuclear"));
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        if(dataManager.get(NUCLEAR)) nbt.setBoolean("nuclear", true);
    }

    @Override
    public void handleStatusUpdate(byte id) {
        if(id == 3) {
            for(int i = 0; i < 8; ++i) {
                EnumParticleTypes particles = (isNuclear() ? EnumParticleTypes.FLAME : EnumParticleTypes.CLOUD);
                world.spawnParticle(particles, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onImpact(RayTraceResult rtr) {
        if(!world.isRemote) {
            float explosionSize = (isNuclear() ? 25.0F : 2.0F);
            world.createExplosion(this, posX, posY, posZ, explosionSize, true);
            world.setEntityState(this, (byte) 3);
            setDead();
        }
    }

    public void setNuclear(boolean nuclear) {dataManager.set(NUCLEAR, nuclear);}
    public boolean isNuclear() {return dataManager.get(NUCLEAR);}
}
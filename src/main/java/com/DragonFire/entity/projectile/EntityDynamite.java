package com.DragonFire.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDynamite extends EntityThrowable {
    private boolean nuclear = false;
    public EntityDynamite(World w) {super(w);}
    public EntityDynamite(World w, EntityLivingBase elb) {super(w, elb);}
    public EntityDynamite(World w, double x, double y, double z) {super(w, x, y, z);}

    @Override
    public void handleStatusUpdate(byte id) {
        if(id == 3) {
            for(int i = 0; i < 8; ++i) {
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onImpact(RayTraceResult rtr) {
        float explosionSize = (nuclear ? 25.0F : 2.0F);
        world.createExplosion(this, posX, posY, posZ, explosionSize, true);
        world.setEntityState(this, (byte) 3);
        setDead();
    }

    public void setNuclear(boolean nuclear) {this.nuclear = nuclear;}
    public boolean isNuclear() {return nuclear;}
}
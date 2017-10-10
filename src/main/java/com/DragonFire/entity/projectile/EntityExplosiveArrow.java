package com.DragonFire.entity.projectile;

import com.DragonFire.item.DFItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityExplosiveArrow extends EntityArrow {
    public EntityExplosiveArrow(World w) {super(w);}
    public EntityExplosiveArrow(World w, EntityLivingBase elb) {super(w, elb);}

    @Override
    protected ItemStack getArrowStack() {
        ItemStack is = new ItemStack(DFItems.EXPLOSIVE_ARROW);
        return is;
    }
    
    @Override
    public void onHit(RayTraceResult rtr) {
        super.onHit(rtr);
        if(!world.isRemote) {
            float explosionSize = 2.0F;
            world.createExplosion(this, posX, posY, posZ, explosionSize, true);
            setDead();
        }
    }
}

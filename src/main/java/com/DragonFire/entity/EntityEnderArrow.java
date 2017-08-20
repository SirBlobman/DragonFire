package com.DragonFire.entity;

import com.DragonFire.item.DFItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityEnderArrow extends EntityArrow {
    public EntityEnderArrow(World w) {super(w);}
    public EntityEnderArrow(World w, EntityLivingBase shooter) {super(w, shooter);}

    @Override
    protected ItemStack getArrowStack() {
        ItemStack is = new ItemStack(DFItems.ENDER_ARROW);
        return is;
    }

    @Override
    public void onHit(RayTraceResult rtr) {
        super.onHit(rtr);
        Entity shooter = this.shootingEntity;
        if(shooter != null) {
            double x = posX;
            double y = posY + 1.0D;
            double z = posZ;
            float yaw = shooter.rotationYaw;
            float pit = shooter.rotationPitch;
            if(shooter instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP) shooter;
                NetHandlerPlayServer server = player.connection;
                server.setPlayerLocation(x, y, z, yaw, pit);
            } else shooter.setLocationAndAngles(x, y, z, yaw, pit);
            setDead();
        }
    }
}
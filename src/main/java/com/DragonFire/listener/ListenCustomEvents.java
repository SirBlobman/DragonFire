package com.DragonFire.listener;

import com.DragonFire.event.PlayerMoveEvent;
import com.DragonFire.utility.Util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomEvents {
    @SubscribeEvent
    public void moveEvent(LivingUpdateEvent e) {
        EntityLivingBase elb = e.getEntityLiving();
        if(elb instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer) elb;
            double lx = ep.prevPosX, cx = ep.posX;
            double ly = ep.prevPosY, cy = ep.posY;
            double lz = ep.prevPosZ, cz = ep.posZ;
            if((lx != cx) || (ly != cy) || (lz != cz)) {
                BlockPos from = new BlockPos(lx, ly, lz);
                BlockPos to = new BlockPos(cx, cy, cz);
                PlayerMoveEvent event = new PlayerMoveEvent(ep, from, to);
                boolean cancelled = Util.call(event);
                if(cancelled) {
                    
                }
            }
        }
    }
}
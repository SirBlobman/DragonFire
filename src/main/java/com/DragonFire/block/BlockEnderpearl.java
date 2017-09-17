package com.DragonFire.block;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;

public class BlockEnderpearl extends QuickBlock {
    public BlockEnderpearl() {super("ender_pearl_block");}
    
    @Override
    public void onLanded(World w, Entity en) {
        super.onLanded(w, en);
        if(!w.isRemote) {
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            double negChance1 = rand.nextDouble();
            double negChance2 = rand.nextDouble();
            int random1 = rand.nextInt(16) + 1;
            int random2 = rand.nextInt(16) + 1;
            double ox = en.posX;
            double oy = en.posY;
            double oz = en.posZ;
            double nx = ((negChance1 <= 0.50) ? ox - random1 : ox + random1);
            double nz = ((negChance2 <= 0.50) ? oz - random2 : oz + random2);
            float yaw = en.rotationYaw;
            float pitch = en.rotationPitch;
            if(en instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP) en;
                NetHandlerPlayServer server = player.connection;
                server.setPlayerLocation(nx, oy, nz, yaw, pitch);
            } else en.setLocationAndAngles(nx, oy, nz, yaw, pitch);
        }
    }
}
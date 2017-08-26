package com.DragonFire.block;

import com.DragonFire.block.item.ItemMummyHead;
import com.DragonFire.block.item.QuickItemBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFBlocks {
    public static final Random rand = new Random();

    //Mob Stuff
    public static final MummyHead MUMMY_HEAD = new MummyHead();
    public static final ItemBlock MUMMY_HEAD_ITEM = new ItemMummyHead();

    //Food
    public static final BlockChocolateCake CHOCOLATE_CAKE = new BlockChocolateCake();
    public static final ItemBlock CHOCOLATE_CAKE_ITEM = new QuickItemBlock(CHOCOLATE_CAKE);

    //Random
    public static final Block ENDERPEARL_BLOCK = new QuickBlock("ender_pearl_block") {
        @Override
        public void onLanded(World w, Entity en) {
            super.onLanded(w, en);
            if(!w.isRemote) {
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
    };
    public static final ItemBlock ENDERPEARL_BLOCK_ITEM = new QuickItemBlock(ENDERPEARL_BLOCK);

    public static void register1(IForgeRegistry<Block> ifr) {
        ifr.registerAll(MUMMY_HEAD, CHOCOLATE_CAKE, ENDERPEARL_BLOCK);
    }

    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(MUMMY_HEAD_ITEM, CHOCOLATE_CAKE_ITEM, ENDERPEARL_BLOCK_ITEM);
    }
}
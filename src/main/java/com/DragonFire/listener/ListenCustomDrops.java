package com.DragonFire.listener;

import com.DragonFire.items.DFItems;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomDrops {
    public static Random RANDOM = new Random();
    
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void squid(LivingDropsEvent e) {
        Entity en = e.getEntity();
        World w = en.getEntityWorld();
        if(!w.isRemote) {
            if(en instanceof EntitySquid) {
                EntitySquid es = (EntitySquid) en;
                Item item = en.isBurning() ? DFItems.COOKED_CALAMARI : DFItems.RAW_CALAMARI;
                int loot = e.getLootingLevel();
                int rand = (RANDOM.nextInt(2) + 1);
                int amount = (rand * ((loot > 0) ? loot : 1));
                ItemStack is = new ItemStack(item, amount);
                es.entityDropItem(is, 0);
            }
        }
    }
}
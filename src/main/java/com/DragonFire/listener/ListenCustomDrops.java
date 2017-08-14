package com.DragonFire.listener;

import com.DragonFire.item.DFItems;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
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
    public void drops(LivingDropsEvent e) {
        Entity en = e.getEntity();
        World w = en.getEntityWorld();
        int loot = e.getLootingLevel();
        if(!w.isRemote) {
            if(en instanceof EntitySquid) {
                Item item = en.isBurning() ? DFItems.COOKED_CALAMARI : DFItems.RAW_CALAMARI;
                int rand = (RANDOM.nextInt(2) + 1);
                int amount = (rand * ((loot > 0) ? loot : 1));
                ItemStack is = new ItemStack(item, amount);
                dropItem(en, is);
            } else if(en instanceof EntityBat) {
                Item item = DFItems.BAT_WINGS;
                int amount = ((loot > 0) ? loot : 1);
                ItemStack is = new ItemStack(item, amount);
                dropItem(en, is);
            }
        }
    }
    
    private void dropItem(Entity dropper, ItemStack is) {
        dropper.entityDropItem(is, 0);
    }
}
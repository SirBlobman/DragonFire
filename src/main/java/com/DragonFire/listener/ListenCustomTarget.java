package com.DragonFire.listener;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=DragonFire.MODID)
public class ListenCustomTarget {
    @SubscribeEvent
    public void target(LivingSetAttackTargetEvent e) {
        EntityLivingBase elb = e.getEntityLiving();
        EntityLivingBase target = e.getTarget();
        if(target != null && elb instanceof EntityEnderman) {
            EntityEnderman ee = (EntityEnderman) elb;
            EntityEquipmentSlot HELMET = EntityEquipmentSlot.HEAD;
            ItemStack helm = target.getItemStackFromSlot(HELMET);
            if(helm != null) {
                Item i = helm.getItem();
                if(i == DFBlocks.ITEM_MUMMY_HEAD) {
                    ee.setAttackTarget(null);
                }
            }
        }
    }
}
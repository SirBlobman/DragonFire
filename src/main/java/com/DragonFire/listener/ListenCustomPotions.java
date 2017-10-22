package com.DragonFire.listener;

import com.DragonFire.potion.effect.DFPotions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomPotions {
    @SubscribeEvent
    public void updateScreen(FogColors e) {
        Entity en = e.getEntity();
        if(en instanceof EntityLivingBase) {
            EntityLivingBase elb = (EntityLivingBase) en;
            if(elb.isPotionActive(DFPotions.RADIATION)) {
                e.setRed((0.0F / 255.0F));
                e.setGreen((255.0F / 255.0F));
                e.setBlue((0.0F / 255.0F));
            }
        }
    }
}
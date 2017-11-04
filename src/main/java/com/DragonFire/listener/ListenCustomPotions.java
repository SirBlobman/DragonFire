package com.DragonFire.listener;

import com.DragonFire.DragonFire;
import com.DragonFire.potion.effect.DFPotions;
import com.DragonFire.utility.Util;

import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@EventBusSubscriber(modid=DragonFire.MODID)
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
    
    @SubscribeEvent
    public void updateOverlay(RenderGameOverlayEvent.Post e) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer ep = mc.player;
        ElementType type = e.getType();
        if(type == ElementType.HELMET) {
            if(ep.isPotionActive(DFPotions.SCARECROW)) {
                GuiIngame gi = mc.ingameGUI;
                ScaledResolution sr = e.getResolution();
                try {
                    Method method = ReflectionHelper.findMethod(GuiIngame.class, "renderPumpkinOverlay", "func_180476_e", sr.getClass());
                    method.invoke(gi, sr);
                } catch(Throwable ex) {
                    String error = "Failed to invoke method renderPumpkinOverlay()";
                    Util.print(error);
                    ex.printStackTrace();
                }
            }
        }
    }
}
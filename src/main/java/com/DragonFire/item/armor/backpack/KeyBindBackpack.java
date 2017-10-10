package com.DragonFire.item.armor.backpack;

import com.DragonFire.DragonFire;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindBackpack {
    public static KeyBinding backpack = new KeyBinding("key.dragonfire.backpack", 48, "key.categories.DragonFire");
    
    @SubscribeEvent
    public void keyPress(KeyInputEvent e) {
        if(backpack.isPressed()) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            if(mc.inGameHasFocus) {
                DragonFire.PACKET_HANDLER.network.sendToServer(new MessageOpenBackpack());
            }
        }
    }
}
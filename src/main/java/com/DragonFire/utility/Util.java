package com.DragonFire.utility;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class Util {
    public static void regEvents(Object... oo) {
        for(Object o : oo) {
            if(o != null) {
                EventBus eb = MinecraftForge.EVENT_BUS;
                eb.register(o);
            }
        }
    }
}
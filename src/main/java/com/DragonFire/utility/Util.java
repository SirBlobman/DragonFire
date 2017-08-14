package com.DragonFire.utility;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class Util {
    public static String color(String o) {
        char[] cc = o.toCharArray();
        for(int i = 0; i < cc.length - 1; i++) {
            char c = cc[i];
            char d = cc[i + 1];
            String s = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
            boolean b1 = (c == '&');
            boolean b2 = (s.indexOf(d) > -1);
            if(b1 && b2) {
                cc[i] = '\u00a7';
                cc[i + 1] = Character.toLowerCase(d);
            }
        }

        String s = new String(cc);
        return s;
    }

    public static void regEvents(Object... oo) {
        for(Object o : oo) {
            if(o != null) {
                EventBus eb = MinecraftForge.EVENT_BUS;
                eb.register(o);
            }
        }
    }
}
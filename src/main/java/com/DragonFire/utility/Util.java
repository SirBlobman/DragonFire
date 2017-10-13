package com.DragonFire.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.Event;
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

    public static void print(Object... oo) {
        for(Object o : oo) {
            String s = o.toString();
            String c = color(s);
            Logger log = LogManager.getLogger("Dragon Fire");
            log.info(c);
        }
    }

    public static void regEvents(Object... oo) {
        for(Object o : oo) {
            if(o != null) {
                EventBus eb = MinecraftForge.EVENT_BUS;
                eb.register(o);
            }
        }
    }
    
    public static boolean call(Event e) {
       EventBus eb = MinecraftForge.EVENT_BUS;
       return eb.post(e);
    }

    @SafeVarargs
    public static <L> List<L> newList(L... ll) {
        List<L> list = new ArrayList<L>();
        for(L l : ll) list.add(l);
        return list;
    }

    public static <L> List<L> newList(Collection<L> ll) {
        List<L> list = new ArrayList<L>();
        for(L l : ll) list.add(l);
        return list;
    }
    
    public static int getRGB(int red, int green, int blue) {
        int r = (red << 16) & 0x00FF0000;
        int g = (green << 8) & 0x0000FF00;
        int b = (blue) & 0x000000FF;
        int color = (0xFF000000 | r | g | b);
        return color;
    }
    
    public static Biome[] getBiomes(String matcher) {
        List<Biome> list = newList();
        for(Biome b : Biome.REGISTRY) {
            String name = b.getBiomeName().toLowerCase();
            if(name.contains(matcher.toLowerCase())) {
                print("Found biome match for '" + matcher + "': " + b.getBiomeName());
                list.add(b);
            }
        }
        
        return list.toArray(new Biome[0]);
    }

    public static void sendMessage(ICommandSender cs, String error) {
        TextComponentString text = new TextComponentString(Util.color(error));
        cs.sendMessage(text);
    }
    
    public static boolean isModLoaded(String mod) {
        boolean loaded = Loader.isModLoaded(mod);
        return loaded;
    }
}
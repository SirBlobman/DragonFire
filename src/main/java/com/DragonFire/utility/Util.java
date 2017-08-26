package com.DragonFire.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
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
    
    public static ItemStack getSpawnEgg(String entityID) {
        try {
            String p1 = "{id:\"minecraft:spawn_egg\", Count:1b, tag:{EntityTag:{id:\"";
            String p2 = "\"}}}";
            String json = p1 + entityID + p2;
            NBTTagCompound nbt = JsonToNBT.getTagFromJson(json);
            ItemStack is = new ItemStack(nbt);
            return is;
        } catch(Throwable ex) {
            Item egg = Items.EGG;
            ItemStack is = new ItemStack(egg);
            return is;
        }
    }
}
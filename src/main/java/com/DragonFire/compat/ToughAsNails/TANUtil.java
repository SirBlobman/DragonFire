package com.DragonFire.compat.ToughAsNails;

import com.DragonFire.utility.Util;

import net.minecraft.entity.player.EntityPlayer;
import toughasnails.api.TANCapabilities;
import toughasnails.api.stat.capability.IThirst;

public class TANUtil extends Util {
    private static boolean isLoaded() {
        boolean loaded = isModLoaded("toughasnails");
        return loaded;
    }
    
    public static void addThirst(EntityPlayer ep, int amount) {
        if(isLoaded()) {
            IThirst thirst = ep.getCapability(TANCapabilities.THIRST, null);
            int current = thirst.getThirst();
            thirst.setThirst(current + amount);
        }
    }
}
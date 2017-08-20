package com.DragonFire.entity.loot;

import com.DragonFire.DragonFire;

import net.minecraft.util.ResourceLocation;

public final class DFLootTables {
    public static ResourceLocation MUMMY = register("mummy");
    
    private static ResourceLocation register(String name) {
        String modid = DragonFire.MODID;
        String rname = "entities/" + name;
        ResourceLocation rl = new ResourceLocation(modid, rname);
        return rl;
    }
}
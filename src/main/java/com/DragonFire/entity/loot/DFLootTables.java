package com.DragonFire.entity.loot;

import com.DragonFire.DragonFire;

import net.minecraft.util.ResourceLocation;

public final class DFLootTables {
    public static ResourceLocation MUMMY = register("mummy");
    public static ResourceLocation DRAUG = register("draug");
    public static ResourceLocation NETHER_FISHING = register("fishing", "nether_fishing");
    
    private static ResourceLocation register(String name) {return register("entities", name);}
    private static ResourceLocation register(String path, String name) {
        String modid = DragonFire.MODID;
        String rname = path + "/" + name;
        ResourceLocation rl = new ResourceLocation(modid, rname);
        return rl;
    }
}
package com.DragonFire.block.tree;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum DFWoodType implements IStringSerializable {
    CHERRY("cherry", 0, MapColor.RED);
    
    private final String name;
    private final int meta;
    private final MapColor color;
    private DFWoodType(String name, int meta, MapColor mc) {
        this.name = name;
        this.meta = meta;
        this.color = mc;
    }
    
    public String getName() {return name;}
    public int getMetadata() {return meta;}
    public MapColor getMapColor() {return color;}
    
    private static final DFWoodType[] META_LOOKUP = new DFWoodType[values().length];
    public static DFWoodType byMetadata(int meta) {
        if(meta < 0 || meta >= META_LOOKUP.length) meta = 0;
        return META_LOOKUP[meta];
    }
    
    static {
        for(DFWoodType type : values()) {
            int meta = type.getMetadata();
            META_LOOKUP[meta] = type;
        }
    }
}
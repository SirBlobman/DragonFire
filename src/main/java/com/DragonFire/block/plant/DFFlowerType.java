package com.DragonFire.block.plant;

import net.minecraft.util.IStringSerializable;

public enum DFFlowerType implements IStringSerializable {
    BLUE_BELL(0, "blue_bell", "blueBell");
    
    private final int meta;
    private final String name, unlocalizedName;
    private DFFlowerType(int meta, String name, String unlocalizedName) {
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }
    
    public int getMeta() {return meta;}
    public String toString() {return name;}
    public String getName() {return name;}
    public String getUnlocalizedName() {return unlocalizedName;}
    
    private static final DFFlowerType[] META_LOOKUP = new DFFlowerType[values().length];
    public static DFFlowerType byMetadata(int meta) {
        if(meta < 0 || meta >= META_LOOKUP.length) meta = 0;
        return META_LOOKUP[meta];
    }
    
    static {
        for(DFFlowerType type : values()) {
            int meta = type.getMeta();
            META_LOOKUP[meta] = type;
        }
    }
}
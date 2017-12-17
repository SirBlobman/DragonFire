package com.DragonFire.utility;

import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMap.Builder;

public class BlockUtil extends Util {    
    public static IStateMapper buildStateMap(IProperty<?> name, String suffix, IProperty<?>... ignore) {
        Builder builder = new Builder();
        builder = builder.withName(name);
        builder = builder.withSuffix(suffix);
        if(ignore != null && ignore.length > 0) builder = builder.ignore(ignore);
        StateMap sm = builder.build();
        return sm;
    }
    
    public static IStateMapper buildIgnoreMap(IProperty<?>... ignore) {
        Builder builder = new Builder();
        if(ignore != null && ignore.length > 0) builder = builder.ignore(ignore);
        StateMap sm = builder.build();
        return sm;
    }
}
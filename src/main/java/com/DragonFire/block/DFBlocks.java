package com.DragonFire.block;

import net.minecraft.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFBlocks {
    public static final MummyHead MUMMY_HEAD = new MummyHead();
    
    public static void register(IForgeRegistry<Block> ifr) {
        ifr.registerAll(MUMMY_HEAD);
    }
}
package com.DragonFire.block;

import com.DragonFire.block.item.ItemMummyHead;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFBlocks {
    public static final MummyHead MUMMY_HEAD = new MummyHead();
    public static final ItemBlock MUMMY_HEAD_ITEM = new ItemMummyHead(MUMMY_HEAD);
    
    public static void register1(IForgeRegistry<Block> ifr) {
        ifr.registerAll(MUMMY_HEAD);
    }
    
    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(MUMMY_HEAD_ITEM);
    }
}
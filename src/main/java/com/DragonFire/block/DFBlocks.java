package com.DragonFire.block;

import com.DragonFire.block.item.ItemMummyHead;
import com.DragonFire.block.item.QuickItemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFBlocks {
    //Mob Stuff
    public static final MummyHead MUMMY_HEAD = new MummyHead();
    public static final ItemBlock MUMMY_HEAD_ITEM = new ItemMummyHead();
    
    //Food
    public static final BlockChocolateCake CHOCOLATE_CAKE = new BlockChocolateCake();
    public static final ItemBlock CHOCOLATE_CAKE_ITEM = new QuickItemBlock(CHOCOLATE_CAKE);
    
    //Random
    public static final Block ENDERPEARL_BLOCK = new QuickBlock("enderpearl_block");
    public static final ItemBlock ENDERPEARL_BLOCK_ITEM = new QuickItemBlock(ENDERPEARL_BLOCK);
    
    public static void register1(IForgeRegistry<Block> ifr) {
        ifr.registerAll(MUMMY_HEAD, CHOCOLATE_CAKE, ENDERPEARL_BLOCK);
    }
    
    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(MUMMY_HEAD_ITEM, CHOCOLATE_CAKE_ITEM, ENDERPEARL_BLOCK_ITEM);
    }
}
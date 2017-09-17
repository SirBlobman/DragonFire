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
    public static final BlockPizza PIZZA = new BlockPizza();
    public static final ItemBlock PIZZA_ITEM = new QuickItemBlock(PIZZA);

    //Random
    public static final BlockEnderpearl ENDER_PEARL_BLOCK = new BlockEnderpearl();
    public static final ItemBlock ENDER_PEARL_BLOCK_ITEM = new QuickItemBlock(ENDER_PEARL_BLOCK);
    public static final BlockObsidianGlass OBSIDIAN_GLASS = new BlockObsidianGlass();
    public static final ItemBlock OBSIDIAN_GLASS_ITEM = new QuickItemBlock(OBSIDIAN_GLASS);

    public static void register1(IForgeRegistry<Block> ifr) {
        //Mob Blocks
        ifr.registerAll(MUMMY_HEAD);
        
        //Food
        ifr.registerAll(CHOCOLATE_CAKE, PIZZA);
        
        //Other
        ifr.registerAll(ENDER_PEARL_BLOCK, OBSIDIAN_GLASS);
    }

    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(
            MUMMY_HEAD_ITEM, 
            CHOCOLATE_CAKE_ITEM, PIZZA_ITEM, 
            ENDER_PEARL_BLOCK_ITEM, OBSIDIAN_GLASS_ITEM
        );
    }
}
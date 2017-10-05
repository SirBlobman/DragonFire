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

    //Food
    public static final BlockChocolateCake CHOCOLATE_CAKE = new BlockChocolateCake();
    public static final BlockPizza PIZZA = new BlockPizza();

    //Redstone
    public static final BlockPlayerPressurePlate PLAYER_PLATE = new BlockPlayerPressurePlate();
    public static final BlockNuclearTNT NUCLEAR_TNT = new BlockNuclearTNT();
    
    //Random
    public static final BlockEnderpearl ENDER_PEARL_BLOCK = new BlockEnderpearl();
    public static final BlockObsidianGlass OBSIDIAN_GLASS = new BlockObsidianGlass();

    //Item Blocks
    public static final ItemBlock ITEM_MUMMY_HEAD = new ItemMummyHead();
    public static final ItemBlock ITEM_CHOCOLATE_CAKE = new QuickItemBlock(CHOCOLATE_CAKE);
    public static final ItemBlock ITEM_PIZZA = new QuickItemBlock(PIZZA);
    public static final ItemBlock ITEM_PLAYER_PLATE = new QuickItemBlock(PLAYER_PLATE);
    public static final ItemBlock ITEM_ENDER_PEARL_BLOCK = new QuickItemBlock(ENDER_PEARL_BLOCK);
    public static final ItemBlock ITEM_OBSIDIAN_GLASS = new QuickItemBlock(OBSIDIAN_GLASS);
    public static final ItemBlock ITEM_NUCLEAR_TNT = new QuickItemBlock(NUCLEAR_TNT);


    public static void register1(IForgeRegistry<Block> ifr) {
        //Mob Blocks
        ifr.registerAll(MUMMY_HEAD);
        
        //Food
        ifr.registerAll(CHOCOLATE_CAKE, PIZZA);
        
        //Redstone
        ifr.registerAll(PLAYER_PLATE, NUCLEAR_TNT);
        
        //Other
        ifr.registerAll(ENDER_PEARL_BLOCK, OBSIDIAN_GLASS);
    }

    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(
            ITEM_MUMMY_HEAD,
            ITEM_CHOCOLATE_CAKE, ITEM_PIZZA,
            ITEM_PLAYER_PLATE, ITEM_NUCLEAR_TNT,
            ITEM_ENDER_PEARL_BLOCK, ITEM_OBSIDIAN_GLASS
        );
    }
}
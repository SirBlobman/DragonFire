package com.DragonFire.block;

import com.DragonFire.block.item.ItemMummyHead;
import com.DragonFire.block.item.QuickItemBlock;
import com.DragonFire.block.ore.QuickOre;
import com.DragonFire.block.tile.BlockNetherBrickFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
    
    //Machines
    public static final BlockNetherBrickFurnace NETHER_BRICK_FURNACE = new BlockNetherBrickFurnace();
    
    //Ores
    public static final QuickOre COPPER_ORE = new QuickOre("copper", "dragonfire:copper_ore", 2.25F, 5.0F, 1);
    public static final Block COPPER_BLOCK = new QuickBlock("copper_block", Material.IRON, 3.0F, 10.0F, "pickaxe", 1);
    public static final QuickOre NETHER_GOLD_ORE = new QuickOre("nether_gold", "dragonfire:nether_gold_ore", 3.0F, 10.0F, 2);

    //Item Blocks
    public static final ItemBlock ITEM_MUMMY_HEAD = new ItemMummyHead();
    public static final ItemBlock ITEM_CHOCOLATE_CAKE = new QuickItemBlock(CHOCOLATE_CAKE);
    public static final ItemBlock ITEM_PIZZA = new QuickItemBlock(PIZZA);
    public static final ItemBlock ITEM_PLAYER_PLATE = new QuickItemBlock(PLAYER_PLATE);
    public static final ItemBlock ITEM_ENDER_PEARL_BLOCK = new QuickItemBlock(ENDER_PEARL_BLOCK);
    public static final ItemBlock ITEM_OBSIDIAN_GLASS = new QuickItemBlock(OBSIDIAN_GLASS);
    public static final ItemBlock ITEM_NUCLEAR_TNT = new QuickItemBlock(NUCLEAR_TNT);
    public static final ItemBlock ITEM_NETHER_BRICK_FURNACE = new QuickItemBlock(NETHER_BRICK_FURNACE);
    public static final ItemBlock ITEM_COPPER_ORE = new QuickItemBlock(COPPER_ORE);
    public static final ItemBlock ITEM_COPPER_BLOCK = new QuickItemBlock(COPPER_BLOCK);
    public static final ItemBlock ITEM_NETHER_GOLD_ORE = new QuickItemBlock(NETHER_GOLD_ORE);

    public static void register1(IForgeRegistry<Block> ifr) {
        //Mob Blocks
        ifr.registerAll(MUMMY_HEAD);
        
        //Food
        ifr.registerAll(CHOCOLATE_CAKE, PIZZA);
        
        //Redstone
        ifr.registerAll(PLAYER_PLATE, NUCLEAR_TNT);
        
        ifr.register(NETHER_BRICK_FURNACE);
        
        //Ores
        ifr.registerAll(COPPER_ORE, COPPER_BLOCK, NETHER_GOLD_ORE);
        
        //Other
        ifr.registerAll(ENDER_PEARL_BLOCK, OBSIDIAN_GLASS);
    }

    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(
            ITEM_MUMMY_HEAD,
            ITEM_CHOCOLATE_CAKE, ITEM_PIZZA,
            ITEM_PLAYER_PLATE, ITEM_NUCLEAR_TNT,
            ITEM_NETHER_BRICK_FURNACE,
            ITEM_COPPER_ORE, ITEM_COPPER_BLOCK, ITEM_NETHER_GOLD_ORE,
            ITEM_ENDER_PEARL_BLOCK, ITEM_OBSIDIAN_GLASS
        );
    }
}
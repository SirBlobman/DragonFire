package com.DragonFire.block;

import com.DragonFire.block.custom.*;
import com.DragonFire.block.item.*;
import com.DragonFire.block.ore.QuickOre;
import com.DragonFire.block.plant.*;
import com.DragonFire.block.plant.slab.BlockDFDoubleWoodSlab;
import com.DragonFire.block.plant.slab.BlockDFHalfWoodSlab;
import com.DragonFire.block.plant.slab.BlockDFWoodSlab;
import com.DragonFire.block.tile.BlockNetherBrickFurnace;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
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
    public static final BlockRadioactiveMushroom RADIOACTIVE_MUSHROOM = new BlockRadioactiveMushroom();
    
    //Machines
    public static final BlockNetherBrickFurnace NETHER_BRICK_FURNACE = new BlockNetherBrickFurnace();
    
    //Ores
    public static final QuickOre COPPER_ORE = new QuickOre("copper", "dragonfire:copper_ore", 2.25F, 5.0F, 1);
    public static final Block COPPER_BLOCK = new QuickBlock("copper_block", Material.IRON, 3.0F, 10.0F, "pickaxe", 1);
    public static final QuickOre NETHER_GOLD_ORE = new QuickOre("nether_gold", "dragonfire:nether_gold_ore", 3.0F, 10.0F, 2);
    public static final QuickBlock CHARCOAL_BLOCK = new QuickBlock("charcoal_block", Material.ROCK, 5.0F, 10.0F, "pickaxe", 0);

    //Tree Related
    public static final BlockDFLeaves LEAVES = new BlockDFLeaves();
    public static final BlockDFLog LOG = new BlockDFLog();
    public static final BlockDFSapling SAPLING = new BlockDFSapling();
    public static final BlockCoconut COCONUT = new BlockCoconut();
    
    //Wood
    public static final BlockDFPlanks PLANKS = new BlockDFPlanks();
    public static final BlockDFWoodSlab WOODEN_SLAB = new BlockDFHalfWoodSlab();
    public static final BlockDFWoodSlab DOUBLE_WOODEN_SLAB = new BlockDFDoubleWoodSlab();
    
    public static final BlockStairs CHERRY_STAIRS = new BlockDFWoodStairs(DFWoodType.CHERRY);
    public static final BlockFence CHERRY_FENCE = new BlockDFWoodFence(DFWoodType.CHERRY);
    public static final BlockDFWoodFenceGate CHERRY_FENCE_GATE = new BlockDFWoodFenceGate(DFWoodType.CHERRY);
    public static final BlockDoor CHERRY_DOOR = new BlockDFWoodDoor(DFWoodType.CHERRY);
    
    public static final BlockStairs PALM_STAIRS = new BlockDFWoodStairs(DFWoodType.PALM);
    public static final BlockFence PALM_FENCE = new BlockDFWoodFence(DFWoodType.PALM);
    public static final BlockDFWoodFenceGate PALM_FENCE_GATE = new BlockDFWoodFenceGate(DFWoodType.PALM);
    public static final BlockDoor PALM_DOOR = new BlockDFWoodDoor(DFWoodType.PALM);
    
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
    public static final ItemBlock ITEM_CHARCOAL_BLOCK = new QuickItemFuelBlock(CHARCOAL_BLOCK, 16000);
    public static final ItemBlock ITEM_RADIOACTIVE_MUSHROOM = new QuickItemBlock(RADIOACTIVE_MUSHROOM);
    
    public static final ItemBlock ITEM_LEAVES = new LeavesItemBlock(LEAVES);
    public static final ItemBlock ITEM_LOG = new TreeItemBlock(LOG, "log");
    public static final ItemBlock ITEM_PLANKS = new TreeItemBlock(PLANKS, "planks");
    public static final ItemBlock ITEM_SAPLING = new TreeItemBlock(SAPLING, "sapling");
    public static final ItemBlock ITEM_WOODEN_SLAB = new WoodSlabItemBlock(WOODEN_SLAB, DOUBLE_WOODEN_SLAB);
    
    public static final ItemBlock ITEM_CHERRY_STAIRS = new QuickItemBlock(CHERRY_STAIRS);
    public static final ItemBlock ITEM_CHERRY_FENCE = new QuickItemBlock(CHERRY_FENCE);
    public static final ItemBlock ITEM_CHERRY_FENCE_GATE = new QuickItemBlock(CHERRY_FENCE_GATE);
    public static final ItemDoor ITEM_CHERRY_DOOR = new WoodDoorItemBlock(CHERRY_DOOR);
    
    public static final ItemBlock ITEM_PALM_STAIRS = new QuickItemBlock(PALM_STAIRS);
    public static final ItemBlock ITEM_PALM_FENCE = new QuickItemBlock(PALM_FENCE);
    public static final ItemBlock ITEM_PALM_FENCE_GATE = new QuickItemBlock(PALM_FENCE_GATE);
    public static final ItemDoor ITEM_PALM_DOOR = new WoodDoorItemBlock(PALM_DOOR);
    
    public static void register1(IForgeRegistry<Block> ifr) {
        //Mob Blocks
        ifr.registerAll(MUMMY_HEAD);
        
        //Food
        ifr.registerAll(CHOCOLATE_CAKE, PIZZA);
        
        //Redstone
        ifr.registerAll(PLAYER_PLATE, NUCLEAR_TNT);
        
        //Machines
        ifr.registerAll(NETHER_BRICK_FURNACE);
        
        //Ores
        ifr.registerAll(COPPER_ORE, COPPER_BLOCK, NETHER_GOLD_ORE, CHARCOAL_BLOCK);
        
        //Other
        ifr.registerAll(ENDER_PEARL_BLOCK, OBSIDIAN_GLASS, RADIOACTIVE_MUSHROOM);
        
        //Wood
        ifr.registerAll(
            LEAVES, LOG, PLANKS, SAPLING, WOODEN_SLAB, DOUBLE_WOODEN_SLAB,
            CHERRY_STAIRS, CHERRY_FENCE, CHERRY_FENCE_GATE, CHERRY_DOOR,
            PALM_STAIRS, PALM_FENCE, PALM_FENCE_GATE, PALM_DOOR, COCONUT
        );
    }

    public static void register2(IForgeRegistry<Item> ifr) {
        ifr.registerAll(
            ITEM_MUMMY_HEAD,
            ITEM_CHOCOLATE_CAKE, ITEM_PIZZA,
            ITEM_PLAYER_PLATE, ITEM_NUCLEAR_TNT,
            ITEM_NETHER_BRICK_FURNACE,
            ITEM_COPPER_ORE, ITEM_COPPER_BLOCK, ITEM_NETHER_GOLD_ORE, ITEM_CHARCOAL_BLOCK,
            ITEM_ENDER_PEARL_BLOCK, ITEM_OBSIDIAN_GLASS, ITEM_RADIOACTIVE_MUSHROOM,
            ITEM_LEAVES, ITEM_LOG, ITEM_PLANKS, ITEM_SAPLING, ITEM_WOODEN_SLAB,
            ITEM_CHERRY_STAIRS, ITEM_CHERRY_FENCE, ITEM_CHERRY_FENCE_GATE, ITEM_CHERRY_DOOR,
            ITEM_PALM_STAIRS, ITEM_PALM_FENCE, ITEM_PALM_FENCE_GATE, ITEM_PALM_DOOR
        );
    }
}
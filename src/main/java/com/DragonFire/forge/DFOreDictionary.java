package com.DragonFire.forge;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.item.DFItems;
import com.DragonFire.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class DFOreDictionary {
    public static void registerBlocks() {
        reg(DFBlocks.COPPER_BLOCK, "blockCopper");
        reg(DFBlocks.COPPER_ORE, "oreCopper");
        reg(DFBlocks.NETHER_GOLD_ORE, "oreGold");
        reg(DFBlocks.OBSIDIAN_GLASS, "blockGlass");
        reg(DFBlocks.OBSIDIAN_GLASS, "blockGlassColorless");
        reg(DFBlocks.CHERRY_STAIRS, "stairWood");
        reg(DFBlocks.PALM_STAIRS, "stairWood");
        
        //Special Blocks
        reg(new ItemStack(DFBlocks.PLANKS, 1, OreDictionary.WILDCARD_VALUE), "plankWood");
        reg(new ItemStack(DFBlocks.LOG, 1, OreDictionary.WILDCARD_VALUE), "logWood");
        reg(new ItemStack(DFBlocks.WOODEN_SLAB, 1, OreDictionary.WILDCARD_VALUE), "slabWood");
        reg(new ItemStack(DFBlocks.LEAVES, 1, OreDictionary.WILDCARD_VALUE), "treeLeaves");
        reg(new ItemStack(DFBlocks.SAPLING, 1, OreDictionary.WILDCARD_VALUE), "treeSapling");
    }
    
    public static void registerItems() {
        reg(DFItems.COPPER_NUGGET, "nuggetCopper");
        reg(DFItems.COPPER_INGOT, "ingotCopper");
    }
    
    private static void reg(Object o, String oreName) {
        if(o instanceof Block) {
            Block block = (Block) o;
            OreDictionary.registerOre(oreName, block);
        } else if(o instanceof Item) {
            Item item = (Item) o;
            OreDictionary.registerOre(oreName, item);
        } else if(o instanceof ItemStack) {
            ItemStack stack = (ItemStack) o;
            OreDictionary.registerOre(oreName, stack);
        } else {
            String error = "Invalid OreDictionary type '" + o.getClass().getSimpleName() + "'. The object you are registering must be a block or an item!";
            Util.print(error);
        }
    }
}
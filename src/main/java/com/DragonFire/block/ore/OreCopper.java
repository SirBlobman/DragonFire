package com.DragonFire.block.ore;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.creative.DFTabs;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class OreCopper extends BlockOre {
    public OreCopper() {
        super();
        setHardness(2.25F);
        setResistance(5.0F);
        setSoundType(SoundType.STONE);
        setRegistryName("copper_ore");
        setUnlocalizedName("oreCopper");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    public Item getItemDropped(IBlockState ibs, Random rand, int fortune) {return DFBlocks.ITEM_COPPER_ORE;}
}
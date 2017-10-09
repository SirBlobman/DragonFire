package com.DragonFire.block.ore;

import com.DragonFire.creative.DFTabs;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class QuickOre extends BlockOre {
    private final String drop;
    public QuickOre(String name, String drop, float hardness, float resistance, int harvestLevel) {
        super();
        this.drop = drop;
        name = name + "_ore";
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(DFTabs.DRAGONFIRE);
        setSoundType(SoundType.STONE);
        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel("pickaxe", harvestLevel);
    }
    
    @Override
    public Item getItemDropped(IBlockState ibs, Random rand, int fortune) {
        ResourceLocation rl = new ResourceLocation(drop);
        Item i = ForgeRegistries.ITEMS.getValue(rl);
        if(i != null) return i;
        else return super.getItemDropped(ibs, rand, fortune);
    }
}
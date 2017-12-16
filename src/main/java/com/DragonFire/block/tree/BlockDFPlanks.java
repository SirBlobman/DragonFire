package com.DragonFire.block.tree;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockDFPlanks extends Block {
    public static final PropertyEnum<DFWoodType> TYPE = PropertyEnum.create("type", DFWoodType.class);
    public BlockDFPlanks() {
        super(Material.WOOD);
        setRegistryName("planks");
        setUnlocalizedName("dragonfire.planks");
        setDefaultState(blockState.getBaseState().withProperty(TYPE, DFWoodType.CHERRY));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public int damageDropped(IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        return meta;
    }
    
    @Override
    public void getSubBlocks(CreativeTabs tabs, NonNullList<ItemStack> list) {
        for(DFWoodType type : DFWoodType.values()) {
            int meta = type.getMetadata();
            ItemStack is = new ItemStack(this, 1, meta);
            list.add(is);
        }
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        return meta;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState ibs = getDefaultState();
        ibs = ibs.withProperty(TYPE, DFWoodType.byMetadata(meta));
        return ibs;
    }
    
    @Override
    public MapColor getMapColor(IBlockState ibs, IBlockAccess world, BlockPos bp) {
        DFWoodType type = ibs.getValue(TYPE);
        MapColor mc = type.getMapColor();
        return mc;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        BlockStateContainer bsc = new BlockStateContainer(this, TYPE);
        return bsc;
    }
}
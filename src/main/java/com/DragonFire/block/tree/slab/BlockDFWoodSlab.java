package com.DragonFire.block.tree.slab;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.creative.DFTabs;

import java.util.Random;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockDFWoodSlab extends BlockSlab {
    public static final PropertyEnum<DFWoodType> TYPE = PropertyEnum.create("type", DFWoodType.class);
    public BlockDFWoodSlab() {
        super(Material.WOOD);
        this.useNeighborBrightness = true;
        setRegistryName(isDouble() ? "double_wooden_slab" : "wooden_slab");
        IBlockState ibs = blockState.getBaseState();
        if(!isDouble()) ibs = ibs.withProperty(HALF, EnumBlockHalf.BOTTOM);
        setDefaultState(ibs.withProperty(TYPE, DFWoodType.CHERRY));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public String getUnlocalizedName(int meta) {
        DFWoodType type = DFWoodType.byMetadata(meta);
        String oriName = super.getUnlocalizedName();
        String addName = type.getName();
        return oriName + "." + addName;
    }
    
    @Override
    public MapColor getMapColor(IBlockState ibs, IBlockAccess world, BlockPos bp) {
        DFWoodType type = ibs.getValue(TYPE);
        MapColor mc = type.getMapColor();
        return mc;
    }
    
    @Override
    public Item getItemDropped(IBlockState ibs, Random rand, int fortune) {
        Item item = Item.getItemFromBlock(DFBlocks.WOODEN_SLAB);
        return item;
    }
    
    @Override
    public int damageDropped(IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        return meta;
    }
    
    @Override
    public ItemStack getItem(World world, BlockPos bp, IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        ItemStack is = new ItemStack(DFBlocks.WOODEN_SLAB, 1, meta);
        return is;
    }
    
    @Override
    public IProperty<?> getVariantProperty() {
        return TYPE;
    }
    
    @Override
    public Comparable<?> getTypeForItem(ItemStack is) {
        int meta = is.getMetadata();
        int meta1 = (meta & 7);
        DFWoodType type = DFWoodType.byMetadata(meta1);
        return type;
    }
    
    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for(DFWoodType type : DFWoodType.values()) {
            int meta = type.getMetadata();
            ItemStack is = new ItemStack(this, 1, meta);
            list.add(is);
        }
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        int meta1 = (meta & 7);
        DFWoodType type = DFWoodType.byMetadata(meta1);
        IBlockState ibs = getDefaultState().withProperty(TYPE, type);
        if(!isDouble()) {
            int meta2 = (meta & 8);
            ibs = ibs.withProperty(HALF, meta2 == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
        } return ibs;
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        int i = 0;
        i = i | ibs.getValue(TYPE).getMetadata();
        if(!isDouble() && ibs.getValue(HALF) == EnumBlockHalf.TOP) i |= 8;
        return i;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        IProperty<?>[] ip = isDouble() ? new IProperty[] {TYPE} : new IProperty[] {HALF, TYPE};
        BlockStateContainer bsc = new BlockStateContainer(this, ip);
        return bsc;
    }
}
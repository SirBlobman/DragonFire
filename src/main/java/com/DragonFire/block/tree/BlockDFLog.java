package com.DragonFire.block.tree;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockDFLog extends BlockLog {
    public static final PropertyEnum<DFWoodType> TYPE = PropertyEnum.create("type", DFWoodType.class);
    public BlockDFLog() {
        super();
        setRegistryName("log");
        setUnlocalizedName("log");
        setCreativeTab(DFTabs.DRAGONFIRE);
        setDefaultState(blockState.getBaseState().withProperty(TYPE, DFWoodType.CHERRY).withProperty(LOG_AXIS, EnumAxis.Y));
    }
    
    @Override
    public MapColor getMapColor(IBlockState ibs, IBlockAccess iba, BlockPos bp) {
        DFWoodType type = ibs.getValue(TYPE);
        MapColor mc = type.getMapColor();
        return mc;
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
        int meta1 = ((meta & 3) % 4);
        IBlockState ibs = getDefaultState().withProperty(TYPE, DFWoodType.byMetadata(meta1));
        switch(meta & 12) {
            case 0:
                ibs = ibs.withProperty(LOG_AXIS, EnumAxis.Y);
                break;
            case 4:
                ibs = ibs.withProperty(LOG_AXIS, EnumAxis.X);
                break;
            case 8:
                ibs = ibs.withProperty(LOG_AXIS, EnumAxis.Z);
                break;
            default:
                ibs = ibs.withProperty(LOG_AXIS, EnumAxis.NONE);
        } return ibs;
    }
    
    @Override
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState ibs) {
        int i = 0;
        i = i | ibs.getValue(TYPE).getMetadata();
        switch(ibs.getValue(LOG_AXIS)) {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        } return i;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        BlockStateContainer bsc = new BlockStateContainer(this, TYPE, LOG_AXIS);
        return bsc;
    }
    
    @Override
    public ItemStack getSilkTouchDrop(IBlockState ibs) {
        Item item = Item.getItemFromBlock(this);
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        ItemStack is = new ItemStack(item, 1, meta);
        return is;
    }
    
    @Override
    public int damageDropped(IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        return meta;
    }
}
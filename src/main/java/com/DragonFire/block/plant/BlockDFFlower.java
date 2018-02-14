package com.DragonFire.block.plant;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlowerPot.EnumFlowerType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.EnumHelper;

public class BlockDFFlower extends BlockBush {
    public static EnumFlowerType BLUE_BELL_POT = EnumHelper.addEnum(EnumFlowerType.class, "BLUE_BELL", new Class<?>[] {String.class}, "blue_bell");
    
    public static final PropertyEnum<DFFlowerType> TYPE = PropertyEnum.create("type", DFFlowerType.class);
    public BlockDFFlower() {
        super();
        setRegistryName("flower");
        setUnlocalizedName("flower");
        setCreativeTab(DFTabs.DRAGONFIRE);
        setDefaultState(blockState.getBaseState().withProperty(TYPE, DFFlowerType.BLUE_BELL));
    }
    
    @Override
    public int damageDropped(IBlockState ibs) {
        DFFlowerType type = ibs.getValue(TYPE);
        int meta = type.getMeta();
        return meta;
    }
    
    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for(DFFlowerType type : DFFlowerType.values()) {
            int meta = type.getMeta();
            ItemStack is = new ItemStack(this, 1, meta);
            list.add(is);
        }
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        DFFlowerType type = DFFlowerType.byMetadata(meta);
        IBlockState ibs = getDefaultState()
                .withProperty(TYPE, type);
        return ibs;
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        DFFlowerType type = ibs.getValue(TYPE);
        int meta = type.getMeta();
        return meta;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        BlockStateContainer bsc = new BlockStateContainer(this, TYPE);
        return bsc;
    }
}
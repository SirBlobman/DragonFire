package com.DragonFire.block.tree;

import com.DragonFire.item.DFItems;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDFLeaves extends BlockLeaves {
    public static final PropertyEnum<DFWoodType> TYPE = PropertyEnum.create("type", DFWoodType.class);
    public BlockDFLeaves() {
        super();
        setRegistryName("leaves");
    }
    
    @Override
    public void dropApple(World world, BlockPos bp, IBlockState ibs, int chance) {
        DFWoodType type = ibs.getValue(TYPE);
        if(type == DFWoodType.CHERRY && world.rand.nextInt(chance) == 0) {
            ItemStack is = new ItemStack(DFItems.CHERRY);
            spawnAsEntity(world, bp, is);
        }
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
    public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState ibs = world.getBlockState(pos);
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        ItemStack is = new ItemStack(this, 1, meta);
        return NonNullList.withSize(1, is);
    }

    @Override
    public EnumType getWoodType(int meta) {return null;}
    public DFWoodType getDragonFireWoodType(int meta) {
        int meta1 = ((meta & 3) % 4);
        DFWoodType type = DFWoodType.byMetadata(meta1);
        return type;
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
    public IBlockState getStateFromMeta(int meta) {
        IBlockState ibs = getDefaultState()
            .withProperty(TYPE, getDragonFireWoodType(meta))
            .withProperty(DECAYABLE, ((meta & 4) == 0))
            .withProperty(CHECK_DECAY, ((meta & 8) > 0));
        return ibs;
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        int i = 0;
        i = i | ibs.getValue(TYPE).getMetadata();
        if(!ibs.getValue(DECAYABLE)) i |= 4;
        if(ibs.getValue(CHECK_DECAY)) i |= 8;
        return i;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        BlockStateContainer bsc = new BlockStateContainer(this, TYPE, CHECK_DECAY, DECAYABLE);
        return bsc;
    }
    
    @Override
    public int damageDropped(IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        return meta;
    }
    
    @Override
    public void harvestBlock(World world, EntityPlayer ep, BlockPos bp, IBlockState ibs, TileEntity te, ItemStack is) {
        if(!world.isRemote && is.getItem() == Items.SHEARS) {
            StatBase stat = StatList.getBlockStats(this);
            ep.addStat(stat);
        } else super.harvestBlock(world, ep, bp, ibs, te, is);
    }
}
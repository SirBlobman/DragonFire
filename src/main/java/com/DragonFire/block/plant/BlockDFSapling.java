package com.DragonFire.block.plant;

import com.DragonFire.creative.DFTabs;
import com.DragonFire.world.DFWorldGenCustomTree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockDFSapling extends BlockBush implements IGrowable {
    public static final PropertyEnum<DFWoodType> TYPE = PropertyEnum.create("type", DFWoodType.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    public BlockDFSapling() {
        setRegistryName("sapling");
        setUnlocalizedName("sapling");
        setDefaultState(blockState.getBaseState().withProperty(TYPE, DFWoodType.CHERRY).withProperty(STAGE, 0));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState ibs, IBlockAccess iba, BlockPos bp) {
        return SAPLING_AABB;
    }
    
    @Override
    public void updateTick(World world, BlockPos bp, IBlockState ibs, Random rand) {
        if(!world.isRemote) {
            super.updateTick(world, bp, ibs, rand);
            if(world.getLightFromNeighbors(bp.up()) >= 9 && rand.nextInt(7) == 0) {
                grow(world, bp, ibs, rand);
            }
        }
    }
    
    @Override
    public int damageDropped(IBlockState ibs) {
        DFWoodType type = ibs.getValue(TYPE);
        int meta = type.getMetadata();
        return meta;
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
    public boolean canGrow(World world, BlockPos bp, IBlockState ibs, boolean client) {
        return true;
    }
    
    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos bp, IBlockState ibs) {
        double d = world.rand.nextFloat();
        boolean b = (d < 0.45D);
        return b;
    }
    
    @Override
    public void grow(World world, Random rand, BlockPos bp, IBlockState ibs) {
        grow(world, bp, ibs, rand);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState ibs = getDefaultState();
        ibs = ibs.withProperty(TYPE, DFWoodType.byMetadata(meta & 7));
        ibs = ibs.withProperty(STAGE, (meta & 8) >> 3);
        return ibs;
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        int i = 0;
        i = i | ibs.getValue(TYPE).getMetadata();
        i = i | ibs.getValue(STAGE) << 3;
        return i;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        BlockStateContainer bsc = new BlockStateContainer(this, TYPE, STAGE);
        return bsc;
    }
    
    public void grow(World world, BlockPos bp, IBlockState ibs, Random rand) {
        if(ibs.getValue(STAGE) == 0) {
            IBlockState nibs = ibs.cycleProperty(STAGE);
            world.setBlockState(bp, nibs, 4);
        } else generateTree(world, bp, ibs, rand);
    }
    
    public void generateTree(World world, BlockPos bp, IBlockState ibs, Random rand) {
        if(!TerrainGen.saplingGrowTree(world, rand, bp)) return;
        int i = 0;
        int j = 0;
        boolean flag = false;
        DFWoodType type = ibs.getValue(TYPE);
        WorldGenerator wg = new DFWorldGenCustomTree(true, type);
        
        IBlockState ibs2 = Blocks.AIR.getDefaultState();
        if(flag) {
            world.setBlockState(bp.add(i, 0, j), ibs2, 4);
            world.setBlockState(bp.add(i + 1, 0, j), ibs2, 4);
            world.setBlockState(bp.add(i, 0, j + 1), ibs2, 4);
            world.setBlockState(bp.add(i + 1, 0, j + 1), ibs2, 4);
        } else {
            world.setBlockState(bp, ibs2, 4);
        }
        
        if(!wg.generate(world, rand, bp.add(i, 0, j))) {
            if(flag) {
                world.setBlockState(bp.add(i, 0, j), ibs, 4);
                world.setBlockState(bp.add(i + 1, 0, j), ibs, 4);
                world.setBlockState(bp.add(i, 0, j + 1), ibs, 4);
                world.setBlockState(bp.add(i + 1, 0, j + 1), ibs, 4);
            } else {
                world.setBlockState(bp, ibs, 4);
            }
        }
    }
    
    public boolean isTwoByTwoOfType(World world, BlockPos bp, int i1, int i2, DFWoodType type) {
        boolean b1 = isTypeAt(world, bp.add(i1, 0, i2), type);
        boolean b2 = isTypeAt(world, bp.add(i1 + 1, 0, i2), type);
        boolean b3 = isTypeAt(world, bp.add(i1, 0, i2 + 1), type);
        boolean b4 = isTypeAt(world, bp.add(i1 + 1, 0, i2 + 1), type);
        return (b1 && b2 && b3 && b4);
    }
    
    public boolean isTypeAt(World world, BlockPos bp, DFWoodType type) {
        IBlockState ibs = world.getBlockState(bp);
        Block b = ibs.getBlock();
        return (b == this && ibs.getValue(TYPE) == type);
    }
}
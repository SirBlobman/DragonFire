package com.DragonFire.block.tree;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.item.DFItems;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockCoconut extends Block implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
    public static final AxisAlignedBB[] COCONUT_AABB = new AxisAlignedBB[] {};
    public BlockCoconut() {
        super(Material.PLANTS);
        setRegistryName("coconut");
        setUnlocalizedName("coconut_crop");
        setDefaultState(blockState.getBaseState().withProperty(AGE, 0));
        setTickRandomly(true);
    }

    @Override
    public boolean canGrow(World world, BlockPos bp, IBlockState ibs, boolean client) {
        int age = ibs.getValue(AGE);
        return (age < 2);
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos bp, IBlockState ibs) {
        return true;
    }

    @Override
    public void grow(World world, Random rand, BlockPos bp, IBlockState ibs) {
        int age = ibs.getValue(AGE);
        IBlockState nibs = ibs.withProperty(AGE, (age + 1));
        world.setBlockState(bp, nibs, 2);
    }
    
    @Override
    public void updateTick(World world, BlockPos bp, IBlockState ibs, Random rand) {
        if(!canBlockStay(world, bp, ibs)) {
            dropBlock(world, bp, ibs);
        } else {
            int age = ibs.getValue(AGE);
            if(age < 2 && ForgeHooks.onCropsGrowPre(world, bp, ibs, rand.nextInt(5) == 0)) {
                world.setBlockState(bp, ibs.withProperty(AGE, (age + 1)), 2);
                ForgeHooks.onCropsGrowPost(world, bp, ibs, world.getBlockState(bp));
            }
        }
    }
    
    public boolean canBlockStay(World world, BlockPos bp, IBlockState ibs) {
        bp = bp.offset(EnumFacing.UP);
        IBlockState ibs2 = world.getBlockState(bp);
        return ((ibs2 == DFBlocks.LEAVES) && (ibs2.getValue(BlockDFLeaves.TYPE) == DFWoodType.PALM));
    }
    
    @Override
    public boolean isFullCube(IBlockState ibs) {
        return false;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState ibs) {
        return false;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState ibs, IBlockAccess world, BlockPos bp) {
        int age = ibs.getValue(AGE);
        return COCONUT_AABB[age];
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos bp, EnumFacing ef, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
       IBlockState ibs = getDefaultState()
               .withProperty(AGE, 0);
       return ibs;
    }
    
    @Override
    public void neighborChanged(IBlockState ibs, World world, BlockPos bp, Block block, BlockPos from) {
        if(!canBlockStay(world, bp, ibs)) {
            dropBlock(world, bp, ibs);
        }
    }
    
    private void dropBlock(World world, BlockPos bp, IBlockState ibs) {
        world.setBlockState(bp, Blocks.AIR.getDefaultState(), 3);
        dropBlockAsItem(world, bp, ibs, 0);
    }
    
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos bp, IBlockState ibs, int fortune) {
        super.getDrops(drops, world, bp, ibs, fortune);
        ItemStack coconut = new ItemStack(DFItems.COCONUT, 1);
        drops.add(coconut);
    }
    
    @Override
    public ItemStack getItem(World world, BlockPos bp, IBlockState ibs) {
        ItemStack coconut = new ItemStack(DFItems.COCONUT, 1);
        return coconut;
    }
    
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState ibs = getDefaultState()
                .withProperty(AGE, meta);
        return ibs;
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        int meta = ibs.getValue(AGE);
        return meta;
    }
    
    @Override
    public BlockStateContainer createBlockState() {
        BlockStateContainer bsc = new BlockStateContainer(this, AGE);
        return bsc;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState ibs, BlockPos bp, EnumFacing ef) {
        return BlockFaceShape.UNDEFINED;
    }
}
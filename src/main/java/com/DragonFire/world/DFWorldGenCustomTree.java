package com.DragonFire.world;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.block.tree.BlockDFLeaves;
import com.DragonFire.block.tree.BlockDFLog;
import com.DragonFire.block.tree.BlockDFSapling;
import com.DragonFire.block.tree.DFWoodType;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class DFWorldGenCustomTree extends WorldGenAbstractTree {
    private final DFWoodType WOOD_TYPE;
    private final IBlockState LOG, LEAF;
    public DFWorldGenCustomTree(boolean notify, DFWoodType type) {
        super(notify);
        this.WOOD_TYPE = type;
        this.LOG  = DFBlocks.LOG.getDefaultState().withProperty(BlockDFLog.TYPE, type);
        this.LEAF = DFBlocks.LEAVES.getDefaultState().withProperty(BlockDFLeaves.TYPE, type);
    }
    
    @Override
    public boolean canGrowInto(Block type) {
        IBlockState ibs = type.getDefaultState();
        Material mat = ibs.getMaterial();
        boolean b1 = (mat == Material.AIR);
        boolean b2 = (mat == Material.LEAVES);
        boolean b3 = (type == Blocks.GRASS);
        boolean b4 = (type == Blocks.DIRT);
        boolean b5 = (type instanceof BlockDFLog);
        boolean b6 = (type instanceof BlockDFSapling);
        boolean b7 = (type == Blocks.VINE);
        boolean b8 = (type == Blocks.LOG || type == Blocks.LOG2 || type == Blocks.SAPLING);
        return (b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8);
    }
    
    @Override
    public boolean generate(World world, Random rand, BlockPos bp) {
        switch(WOOD_TYPE) {
            case CHERRY: 
            default:
                return generateCherry(world, rand, bp);
            case PALM:
                return generatePalm(world, rand, bp);
        }
    }
    
    private boolean generateCherry(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(3) + 5;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= 256) {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;

                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (j >= 0 && j < worldIn.getHeight()) {
                            if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, j, i1))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down,
                        net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING);

                if (isSoil && position.getY() < worldIn.getHeight() - i - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);

                    for (int i2 = position.getY() - 3 + i; i2 <= position.getY() + i; ++i2) {
                        int k2 = i2 - (position.getY() + i);
                        int l2 = 1 - k2 / 2;

                        for (int i3 = position.getX() - l2; i3 <= position.getX() + l2; ++i3) {
                            int j1 = i3 - position.getX();

                            for (int k1 = position.getZ() - l2; k1 <= position.getZ() + l2; ++k1) {
                                int l1 = k1 - position.getZ();

                                if (Math.abs(j1) != l2 || Math.abs(l1) != l2 || rand.nextInt(2) != 0 && k2 != 0) {
                                    BlockPos blockpos = new BlockPos(i3, i2, k1);
                                    IBlockState state2 = worldIn.getBlockState(blockpos);

                                    if (state2.getBlock().isAir(state2, worldIn, blockpos)
                                            || state2.getBlock().isAir(state2, worldIn, blockpos)) {
                                        this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
                                    }
                                }
                            }
                        }
                    }

                    for (int j2 = 0; j2 < i; ++j2) {
                        BlockPos upN = position.up(j2);
                        IBlockState state2 = worldIn.getBlockState(upN);

                        if (state2.getBlock().isAir(state2, worldIn, upN)
                                || state2.getBlock().isLeaves(state2, worldIn, upN)) {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(j2), LOG);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
    
    private boolean generatePalm(World worldIn, Random rand, BlockPos position) {
        int i = rand.nextInt(3) + rand.nextInt(3) + 5;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + i + 1 <= 256)
        {
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j)
            {
                int k = 1;

                if (j == position.getY())
                {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
                {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < 256)
                        {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling)Blocks.SAPLING));

                if (isSoil && position.getY() < worldIn.getHeight() - i - 1)
                {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);
                    EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(rand);
                    int k2 = i - rand.nextInt(4) - 1;
                    int l2 = 3 - rand.nextInt(3);
                    int i3 = position.getX();
                    int j1 = position.getZ();
                    int k1 = 0;

                    for (int l1 = 0; l1 < i; ++l1)
                    {
                        int i2 = position.getY() + l1;

                        if (l1 >= k2 && l2 > 0)
                        {
                            i3 += enumfacing.getFrontOffsetX();
                            j1 += enumfacing.getFrontOffsetZ();
                            --l2;
                        }

                        BlockPos blockpos = new BlockPos(i3, i2, j1);
                        state = worldIn.getBlockState(blockpos);

                        if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos))
                        {
                            this.placeLogAt(worldIn, blockpos);
                            k1 = i2;
                        }
                    }

                    BlockPos blockpos2 = new BlockPos(i3, k1, j1);

                    for (int j3 = -3; j3 <= 3; ++j3)
                    {
                        for (int i4 = -3; i4 <= 3; ++i4)
                        {
                            if (Math.abs(j3) != 3 || Math.abs(i4) != 3)
                            {
                                this.placeLeafAt(worldIn, blockpos2.add(j3, 0, i4));
                            }
                        }
                    }

                    blockpos2 = blockpos2.up();

                    for (int k3 = -1; k3 <= 1; ++k3)
                    {
                        for (int j4 = -1; j4 <= 1; ++j4)
                        {
                            this.placeLeafAt(worldIn, blockpos2.add(k3, 0, j4));
                        }
                    }

                    this.placeLeafAt(worldIn, blockpos2.east(2));
                    this.placeLeafAt(worldIn, blockpos2.west(2));
                    this.placeLeafAt(worldIn, blockpos2.south(2));
                    this.placeLeafAt(worldIn, blockpos2.north(2));
                    i3 = position.getX();
                    j1 = position.getZ();
                    EnumFacing enumfacing1 = EnumFacing.Plane.HORIZONTAL.random(rand);

                    if (enumfacing1 != enumfacing)
                    {
                        int l3 = k2 - rand.nextInt(2) - 1;
                        int k4 = 1 + rand.nextInt(3);
                        k1 = 0;

                        for (int l4 = l3; l4 < i && k4 > 0; --k4)
                        {
                            if (l4 >= 1)
                            {
                                int j2 = position.getY() + l4;
                                i3 += enumfacing1.getFrontOffsetX();
                                j1 += enumfacing1.getFrontOffsetZ();
                                BlockPos blockpos1 = new BlockPos(i3, j2, j1);
                                state = worldIn.getBlockState(blockpos1);

                                if (state.getBlock().isAir(state, worldIn, blockpos1) || state.getBlock().isLeaves(state, worldIn, blockpos1))
                                {
                                    this.placeLogAt(worldIn, blockpos1);
                                    k1 = j2;
                                }
                            }

                            ++l4;
                        }

                        if (k1 > 0)
                        {
                            BlockPos blockpos3 = new BlockPos(i3, k1, j1);

                            for (int i5 = -2; i5 <= 2; ++i5)
                            {
                                for (int k5 = -2; k5 <= 2; ++k5)
                                {
                                    if (Math.abs(i5) != 2 || Math.abs(k5) != 2)
                                    {
                                        this.placeLeafAt(worldIn, blockpos3.add(i5, 0, k5));
                                    }
                                }
                            }

                            blockpos3 = blockpos3.up();

                            for (int j5 = -1; j5 <= 1; ++j5)
                            {
                                for (int l5 = -1; l5 <= 1; ++l5)
                                {
                                    this.placeLeafAt(worldIn, blockpos3.add(j5, 0, l5));
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
    
    private void placeLeafAt(World world, BlockPos bp) {
        IBlockState ibs = world.getBlockState(bp);
        if(ibs.getBlock().isAir(ibs, world, bp) || ibs.getBlock().isLeaves(ibs, world, bp)) {
            setBlockAndNotifyAdequately(world, bp, LEAF);
        }
    }
    
    private void placeLogAt(World world, BlockPos bp) {
        setBlockAndNotifyAdequately(world, bp, LOG);
    }
}
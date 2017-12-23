package com.DragonFire.block.tree;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDFWoodFenceGate extends BlockHorizontal {
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyBool POWERED = PropertyBool.create("powered");
    public static final PropertyBool IN_WALL = PropertyBool.create("in_wall");
    protected static final AxisAlignedBB AABB_HITBOX_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    protected static final AxisAlignedBB AABB_HITBOX_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
    protected static final AxisAlignedBB AABB_HITBOX_ZAXIS_INWALL = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 0.8125D, 0.625D);
    protected static final AxisAlignedBB AABB_HITBOX_XAXIS_INWALL = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 0.8125D, 1.0D);
    protected static final AxisAlignedBB AABB_COLLISION_BOX_ZAXIS = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.5D, 0.625D);
    protected static final AxisAlignedBB AABB_COLLISION_BOX_XAXIS = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.5D, 1.0D);
    
    public BlockDFWoodFenceGate(DFWoodType wood) {
        super(Material.WOOD, wood.getMapColor());
        setRegistryName(wood.getName() + "_fence_gate");
        setUnlocalizedName(wood.getName() + "_fence_gate");
        setDefaultState(blockState.getBaseState().withProperty(OPEN, false).withProperty(POWERED, false).withProperty(IN_WALL, false));
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState ibs, IBlockAccess world, BlockPos bp) {
        ibs = getActualState(ibs, world, bp);
        if(ibs.getValue(IN_WALL)) {
            EnumFacing ef = ibs.getValue(FACING);
            Axis axis = ef.getAxis();
            return (axis == Axis.X) ? AABB_HITBOX_XAXIS_INWALL : AABB_HITBOX_ZAXIS_INWALL;
        } else {
            EnumFacing ef = ibs.getValue(FACING);
            Axis axis = ef.getAxis();
            return (axis == Axis.X) ? AABB_HITBOX_XAXIS : AABB_HITBOX_ZAXIS;
        }
    }
    
    @Override
    public IBlockState getActualState(IBlockState ibs, IBlockAccess world, BlockPos bp) {
        EnumFacing ef = ibs.getValue(FACING);
        Axis axis = ef.getAxis();
        if(axis == Axis.Z && (canFenceGateConnectTo(world, bp, EnumFacing.WEST) || canFenceGateConnectTo(world, bp, EnumFacing.EAST)) || axis == EnumFacing.Axis.X && (canFenceGateConnectTo(world, bp, EnumFacing.NORTH) || canFenceGateConnectTo(world, bp, EnumFacing.SOUTH))) {
            ibs = ibs.withProperty(IN_WALL, true);
        } return ibs;
    }
    
    @Override
    public IBlockState withRotation(IBlockState ibs, Rotation rot) {
        EnumFacing ef = ibs.getValue(FACING);
        EnumFacing nef = rot.rotate(ef);
        return ibs.withProperty(FACING, nef);
    }
    
    @Override
    public IBlockState withMirror(IBlockState ibs, Mirror mirror) {
        EnumFacing ef = ibs.getValue(FACING);
        Rotation rot = mirror.toRotation(ef);
        return ibs.withRotation(rot);
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos bp) {
        return world.getBlockState(bp.down()).getMaterial().isSolid() ? super.canPlaceBlockAt(world, bp) : false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState ibs, IBlockAccess world, BlockPos bp) {
        if(ibs.getValue(OPEN)) return NULL_AABB;
        else {
            EnumFacing ef = ibs.getValue(FACING);
            Axis axis = ef.getAxis();
            return (axis == Axis.Z) ? AABB_COLLISION_BOX_ZAXIS : AABB_COLLISION_BOX_XAXIS;
        }
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState ibs) {return false;}
    
    @Override
    public boolean isFullCube(IBlockState ibs) {return false;}
    
    @Override
    public boolean isPassable(IBlockAccess world, BlockPos bp) {
        IBlockState ibs = world.getBlockState(bp);
        boolean open = ibs.getValue(OPEN);
        return open;
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos bp, EnumFacing ef, float x, float y, float z, int meta, EntityLivingBase placer) {
        boolean flag = world.isBlockPowered(bp);
        return getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(OPEN, flag).withProperty(IN_WALL, false);
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos bp, IBlockState ibs, EntityPlayer ep, EnumHand eh, EnumFacing ef, float x, float y, float z) {
        if(ibs.getValue(OPEN)) {
            ibs = ibs.withProperty(OPEN, false);
            world.setBlockState(bp, ibs, 10);
        } else {
            EnumFacing ef1 = EnumFacing.fromAngle(ep.rotationYaw);
            if(ibs.getValue(FACING) == ef1.getOpposite()) {
                ibs = ibs.withProperty(FACING, ef1);
            }
            
            ibs = ibs.withProperty(OPEN, true);
            world.setBlockState(bp, ibs, 10);
        }
        
        world.playEvent(ep, ibs.getValue(OPEN) ? 1008 : 1014, bp, 0);
        return true;
    }
    
    @Override
    public void neighborChanged(IBlockState ibs, World world, BlockPos bp, Block block, BlockPos from) {
        if(!world.isRemote) {
            boolean flag = world.isBlockPowered(bp);
            if(ibs.getValue(POWERED) != flag) {
                world.setBlockState(bp, ibs.withProperty(POWERED, flag).withProperty(OPEN, flag), 2);
                if(ibs.getValue(OPEN) != flag) {
                    world.playEvent(null, flag ? 1008 : 1014, bp, 0);
                }
            }
        }
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockState ibs, IBlockAccess world, BlockPos bp, EnumFacing ef) {
        return true;
    }
    
    @Override
    public int getMetaFromState(IBlockState ibs) {
        int i = 0;
        i = i | ibs.getValue(FACING).getHorizontalIndex();
        if(ibs.getValue(POWERED)) i |= 8;
        if(ibs.getValue(OPEN)) i |= 4;
        return i;
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState ibs = getDefaultState()
                .withProperty(FACING, EnumFacing.getHorizontal(meta))
                .withProperty(OPEN, ((meta & 4) != 0))
                .withProperty(POWERED, ((meta & 8) != 0));
        return ibs;
    }
    
    @Override
    public BlockStateContainer createBlockState() { 
        BlockStateContainer bsc = new BlockStateContainer(this, FACING, OPEN, POWERED, IN_WALL);
        return bsc;
    }
    
    @Override
    public boolean canBeConnectedTo(IBlockAccess world, BlockPos bp, EnumFacing ef) {
        IBlockState ibs = world.getBlockState(bp.offset(ef));
        Block connect = ibs.getBlock();
        return (connect instanceof BlockFence || connect instanceof BlockWall);
    }
    
    private boolean canFenceGateConnectTo(IBlockAccess world, BlockPos bp, EnumFacing ef) {
        IBlockState ibs = world.getBlockState(bp.offset(ef));
        Block block = ibs.getBlock();
        EnumFacing opp = ef.getOpposite();
        return block.canBeConnectedTo(world, bp.offset(ef), opp);
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState ibs, BlockPos bp, EnumFacing ef) {
        if(ef != EnumFacing.UP && ef != EnumFacing.DOWN) {
            EnumFacing ef1 = ibs.getValue(FACING);
            Axis axis = ef1.getAxis();
            return (axis == ef.rotateY().getAxis()) ? BlockFaceShape.MIDDLE_POLE : BlockFaceShape.UNDEFINED;
        } else return BlockFaceShape.UNDEFINED;
    }
}
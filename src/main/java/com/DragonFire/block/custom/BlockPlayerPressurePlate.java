package com.DragonFire.block.custom;

import com.DragonFire.creative.DFTabs;

import java.util.List;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class BlockPlayerPressurePlate extends BlockPressurePlate {
    public static Sensitivity PLAYERS = EnumHelper.addSensitivity("PLAYERS");
    public BlockPlayerPressurePlate() {
        super(Material.CIRCUITS, PLAYERS);
        setRegistryName("player_pressure_plate");
        setUnlocalizedName("player_pressure_plate");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public int computeRedstoneStrength(World w, BlockPos bp) {
        AxisAlignedBB axis = PRESSURE_AABB.offset(bp);
        List<Entity> list = w.getEntitiesWithinAABB(EntityPlayer.class, axis);
        if(!list.isEmpty()) {
            for(Entity en : list) {
                if(!en.doesEntityNotTriggerPressurePlate()) return 15;
            }
        } return 0;
    }
}
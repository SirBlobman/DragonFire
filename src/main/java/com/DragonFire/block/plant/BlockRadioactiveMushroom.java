package com.DragonFire.block.plant;

import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockMushroom;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRadioactiveMushroom extends BlockMushroom {
    public BlockRadioactiveMushroom() {
        super();
        setRegistryName("radioactive_mushroom");
        setUnlocalizedName("radioactive_mushroom");
        setSoundType(SoundType.PLANT);
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public boolean removedByPlayer(IBlockState ibs, World world, BlockPos bp, EntityPlayer ep, boolean willHarvest) {
        ep.addPotionEffect(new PotionEffect(MobEffects.POISON, 30 * 20, 0, true, false));
        return super.removedByPlayer(ibs, world, bp, ep, willHarvest);
    }
}
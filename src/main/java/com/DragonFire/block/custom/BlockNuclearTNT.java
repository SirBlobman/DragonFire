package com.DragonFire.block.custom;

import com.DragonFire.creative.DFTabs;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNuclearTNT extends BlockTNT {
    public BlockNuclearTNT() {
        setRegistryName("nuclear_tnt");
        setUnlocalizedName("nuclear_tnt");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }

    @Override
    public void explode(World world, BlockPos bp, IBlockState ibs, EntityLivingBase igniter) {
        if(!world.isRemote) {
            if(ibs.getValue(EXPLODE)) {
                int i = 0;
                while(i < 50) {
                    i++;
                    EntityTNTPrimed tntPrimed = new EntityTNTPrimed(world, bp.getX() + 0.5, bp.getY(), bp.getZ() + 0.5, igniter);
                    world.spawnEntity(tntPrimed);
                    world.playSound(null, tntPrimed.posX, tntPrimed.posY, tntPrimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    tntPrimed.motionX = randomDouble();
                    tntPrimed.motionY = Math.abs(randomDouble());
                    tntPrimed.motionZ = randomDouble();
                }
            }
        }
    }

    private double randomDouble() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        boolean negative = rand.nextBoolean();
        if(negative) {
            double neg = rand.nextDouble(-.5, -Double.MIN_VALUE);
            return neg;
        } else {
            double pos = rand.nextDouble(Double.MIN_VALUE, .5);
            return pos;
        }
    }
}
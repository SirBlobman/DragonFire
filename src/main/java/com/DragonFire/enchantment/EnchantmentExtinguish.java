package com.DragonFire.enchantment;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

public class EnchantmentExtinguish extends Enchantment {
    public EnchantmentExtinguish() {
        super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[] {EntityEquipmentSlot.FEET});
        setRegistryName("extinguish");
        setName("extinguish");
    }
    
    public int getMaxLevel() {return 2;}
    
    public static void extinguishNearby(EntityLivingBase elb, World world, BlockPos bp, int level) {
        if(elb.onGround) {
            float min = Math.min(16, 2 + level);
            MutableBlockPos mbp = new MutableBlockPos(0, 0, 0);
            Iterable<MutableBlockPos> imbp = BlockPos.getAllInBoxMutable(bp.add(-min, 0.0D, -min), bp.add(min, 0.0D, min));
            for(MutableBlockPos mbp1 : imbp) {
                double sqrt = mbp1.distanceSqToCenter(elb.posX, elb.posY, elb.posZ);
                if(sqrt <= (min * min)) {
                    mbp.setPos(mbp1.getX(), mbp1.getY() + 1, mbp1.getZ());
                    IBlockState ibs = world.getBlockState(mbp);
                    Material mat = ibs.getMaterial();
                    if(mat == Material.AIR) {
                        IBlockState ibs1 = world.getBlockState(mbp1);
                        Material mat1 = ibs1.getMaterial();
                        Block b1 = ibs1.getBlock();
                        if((mat1 == Material.FIRE) && (b1 == Blocks.FIRE)) {
                            world.setBlockToAir(mbp1);
                        }
                    }
                }
            }
        }
    }
}
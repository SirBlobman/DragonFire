package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;
import com.DragonFire.entity.projectile.EntityExplosiveArrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosiveArrow extends ItemArrow {
    public ExplosiveArrow() {
        super();
        setRegistryName("explosive_arrow");
        setUnlocalizedName("explosive_arrow");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }
    
    @Override
    public EntityExplosiveArrow createArrow(World w, ItemStack is, EntityLivingBase elb) {
        EntityExplosiveArrow eea = new EntityExplosiveArrow(w, elb);
        return eea;
    }
}
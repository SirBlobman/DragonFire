package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;
import com.DragonFire.entity.EntityEnderArrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderArrow extends ItemArrow {
    public EnderArrow() {
        super();
        setUnlocalizedName("ender_arrow");
        setRegistryName("ender_arrow");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }

    @Override
    public EntityArrow createArrow(World w, ItemStack is, EntityLivingBase elb) {
        EntityEnderArrow eea = new EntityEnderArrow(w, elb);
        return eea;
    }
}
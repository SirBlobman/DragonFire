package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;
import com.DragonFire.entity.projectile.EntityEnderArrow;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderArrow extends Item {
    public EnderArrow() {
        super();
        setUnlocalizedName("ender_arrow");
        setRegistryName("ender_arrow");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }

    public EntityArrow createArrow(World w, ItemStack is, EntityLivingBase elb) {
        EntityEnderArrow eea = new EntityEnderArrow(w, elb);
        return eea;
    }
}
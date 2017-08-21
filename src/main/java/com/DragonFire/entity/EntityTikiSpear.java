package com.DragonFire.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTikiSpear extends EntityArrow {
    public EntityTikiSpear(World w) {super(w);}
    public EntityTikiSpear(World w, EntityLivingBase shooter) {super(w, shooter);}
    
    @Override
    public ItemStack getArrowStack() {return ItemStack.EMPTY;}
}
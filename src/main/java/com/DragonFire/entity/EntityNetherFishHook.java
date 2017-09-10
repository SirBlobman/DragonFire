package com.DragonFire.entity;

import com.DragonFire.utility.ItemUtil;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityNetherFishHook extends EntityFishHook {
    public EntityNetherFishHook(World world, EntityPlayer player) {super(world, player); this.isImmuneToFire = true;}
    public void setFire(int seconds) {}
    public boolean isBurning() {return false;}
    
    @Override
    protected void bringInHookedEntity() {
        super.bringInHookedEntity();
        if(caughtEntity instanceof EntityItem) {
            EntityItem ei = (EntityItem) caughtEntity;
            ItemStack is = ei.getItem();
            ItemStack smelt = ItemUtil.smelt(is);
            if(!ItemUtil.isAir(smelt)) ei.setItem(smelt);
        }
    }
}
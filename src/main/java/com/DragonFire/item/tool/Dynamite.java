package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;
import com.DragonFire.entity.projectile.EntityDynamite;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class Dynamite extends Item {
    private boolean nuclear;
    public Dynamite(boolean nuclear) {
        maxStackSize = 1;
        this.nuclear = nuclear;
        String name = (nuclear ? "nuclear_dynamite" : "dynamite");
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand eh) {
        ItemStack is = ep.getHeldItem(eh);
        if(!ep.capabilities.isCreativeMode) is.shrink(1);
        world.playSound(null, ep.posX, ep.posY, ep.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!world.isRemote) {
            EntityDynamite dynamite = new EntityDynamite(world, ep);
            dynamite.setHeadingFromThrower(ep, ep.rotationPitch, ep.rotationYaw, 0.0F, 1.0F, 1.0F);
            dynamite.setNuclear(nuclear);
            world.spawnEntity(dynamite);
        }
        
        ep.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, is);
    }
}
package com.DragonFire.item.tool;

import com.DragonFire.creative.DFTabs;
import com.DragonFire.entity.projectile.EntityNetherFishHook;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class NetherRod extends ItemFishingRod {
    public NetherRod() {
        super();
        setRegistryName("nether_fishing_rod");
        setUnlocalizedName("nether_fishing_rod");
        setCreativeTab(DFTabs.ARMOR_AND_TOOLS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if(player.fishEntity != null) {
            int i = player.fishEntity.handleHookRetraction();
            heldItem.damageItem(i, player);
            player.swingArm(hand);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.isRemote) {
                EntityNetherFishHook entityFishHook = new EntityNetherFishHook(world, player);
                int j = EnchantmentHelper.getFishingSpeedBonus(heldItem);
                if(j > 0) entityFishHook.setLureSpeed(j);
                
                int k = EnchantmentHelper.getFishingLuckBonus(heldItem);
                if(k > 0) entityFishHook.setLuck(k);
                world.spawnEntity(entityFishHook);
            }
            
            player.swingArm(hand);
            player.addStat(StatList.getObjectUseStats(this));
        }
        
        ActionResult<ItemStack> ar = new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
        return ar;
    }
}
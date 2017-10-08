package com.DragonFire.item.drink;

import com.DragonFire.creative.DFTabs;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class QuickDrink extends Item {
    private final int useDuration;
    public QuickDrink(String name, int useDuration) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setCreativeTab(DFTabs.FOOD_AND_DRINK);
        this.useDuration = useDuration;
    }
    
    public EnumAction getItemUseAction(ItemStack is) {return EnumAction.DRINK;}
    public int getMaxItemUseDuration(ItemStack is) {return useDuration;}
    
    @Override
    public ItemStack onItemUseFinish(ItemStack is, World w, EntityLivingBase elb) {
        EntityPlayer ep = ((elb instanceof EntityPlayer) ? (EntityPlayer) elb : null);
        if(ep instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP) ep;
            ConsumeItemTrigger cit = CriteriaTriggers.CONSUME_ITEM;
            cit.trigger(mp, is);
        }
        
        if(ep != null) ep.addStat(StatList.getObjectUseStats(this));
        if(ep == null || !ep.capabilities.isCreativeMode) {
          boolean container = is.getItem().hasContainerItem(is);
          if(container) is = is.getItem().getContainerItem(is);
          else is.shrink(1);
        } return is;
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer ep, EnumHand hand) {
        ep.setActiveHand(hand);
        ItemStack held = ep.getHeldItem(hand);
        ActionResult<ItemStack> ari = new ActionResult<ItemStack>(EnumActionResult.SUCCESS, held);
        return ari;
    }
}
package com.DragonFire.item.food;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMoss extends QuickFood {
    public ItemMoss() {super("moss", 1, 0.1F, false);}
    
    public EnumAction getItemUseAction(ItemStack is) {
        int meta = is.getItemDamage();
        if(meta == 0) return EnumAction.NONE;
        else return super.getItemUseAction(is);
    }
    
    @Override
    public ItemStack onItemUseFinish(ItemStack is, World world, EntityLivingBase elb) {
        int meta = is.getItemDamage();
        if(meta == 0) return is;
        else return super.onItemUseFinish(is, world, elb);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer ep, EnumHand eh) {
        ItemStack is = ep.getHeldItem(eh);
        int meta = is.getItemDamage();
        if(meta == 0) {
            ActionResult<ItemStack> ari = new ActionResult<ItemStack>(EnumActionResult.PASS, is);
            return ari;
        } else return super.onItemRightClick(world, ep, eh);
    }
}
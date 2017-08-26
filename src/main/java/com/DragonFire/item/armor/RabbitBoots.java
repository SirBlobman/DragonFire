package com.DragonFire.item.armor;

import com.DragonFire.item.DFItems;
import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RabbitBoots extends QuickArmor {
    public RabbitBoots() {
        super(DFItems.ARMOR_RABBIT, EntityEquipmentSlot.FEET);
    }
    
    @Override
    public void onArmorTick(World w, EntityPlayer ep, ItemStack is) {
        ItemStack boots = ep.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if(boots != null) {
            Item i = boots.getItem();
            if(i == DFItems.RABBIT_BOOTS) {
                PotionEffect pe = new PotionEffect(MobEffects.JUMP_BOOST, 159, 2, true, false);
                ep.addPotionEffect(pe);
            }
        }
    }
    
    @Override
    public void addInformation(ItemStack is, World w, List<String> lore, ITooltipFlag flag) {
        String jump = Util.color("&aJump Boost III");
        lore.add(jump);
    }
}
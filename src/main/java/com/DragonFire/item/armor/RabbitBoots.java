package com.DragonFire.item.armor;

import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RabbitBoots extends ItemArmor {
    public RabbitBoots() {
        super(ArmorMaterial.DIAMOND, ArmorMaterial.DIAMOND.getDamageReductionAmount(EntityEquipmentSlot.FEET), EntityEquipmentSlot.FEET);
        String name = "rabbit_boots";
        setUnlocalizedName(name); setRegistryName(name);
    }
    
    @Override
    public void onArmorTick(World w, EntityPlayer ep, ItemStack is) {
        ItemStack boots = ep.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        if(boots != null) {
            Item i = boots.getItem();
            Class<?> clazz = RabbitBoots.class;
            if(clazz.isInstance(i)) {
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
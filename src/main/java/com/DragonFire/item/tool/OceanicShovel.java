package com.DragonFire.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class OceanicShovel extends QuickShovel {
    public static final ToolMaterial OCEANIC = EnumHelper.addToolMaterial("OCEANIC", 3, 1561, 8.0F, 3.0F, 10);
    public OceanicShovel() {
        super(OCEANIC);
    }
    
    @Override
    public void onUpdate(ItemStack is, World world, Entity en, int itemSlot, boolean isSelected) {
        if(isSelected && (en instanceof EntityPlayer)) {
            EntityPlayer ep = (EntityPlayer) en;
            if(ep.isInWater()) {
                PotionEffect pe = new PotionEffect(MobEffects.HASTE, 30, 255, true, false);
                ep.addPotionEffect(pe);
            }
        }
    }
}
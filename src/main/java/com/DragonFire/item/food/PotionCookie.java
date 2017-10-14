package com.DragonFire.item.food;

import com.DragonFire.creative.DFTabs;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class PotionCookie extends QuickFood {
    public PotionCookie() {
        super("potion_cookie", 2, 0.4F, false);
        setAlwaysEdible();
        setCreativeTab(DFTabs.COOKIES);
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(isInCreativeTab(tab)) {
            for(PotionType pt : PotionType.REGISTRY) {
                List<PotionEffect> effects = pt.getEffects();
                if(!effects.isEmpty()) {
                    ItemStack is = new ItemStack(this);
                    ItemStack pot = PotionUtils.addPotionToItemStack(is, pt);
                    items.add(pot);
                }
            }
        }
    }
    
    @Override
    public void addInformation(ItemStack is, World w, List<String> lore, ITooltipFlag flag) {
        PotionUtils.addPotionTooltip(is, lore, 0.125F);
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack is) {
        PotionType pt = PotionUtils.getPotionFromItem(is);
        String name = pt.getNamePrefixed("potion_cookie.effect.");
        return I18n.format(name);
    }
    
    @Override
    public void onFoodEaten(ItemStack is, World w, EntityPlayer ep) {
        super.onFoodEaten(is, w, ep);
        PotionType pt = PotionUtils.getPotionFromItem(is);
        for(PotionEffect pe : pt.getEffects()) {
            Potion pot = pe.getPotion();
            int duration = Math.max(pe.getDuration() / 8, 1);
            int amplifier = pe.getAmplifier();
            boolean ambient = pe.getIsAmbient();
            boolean particles = pe.doesShowParticles();
            PotionEffect copy = new PotionEffect(pot, duration, amplifier, ambient, particles);
            ep.addPotionEffect(copy);
        }
    }
}
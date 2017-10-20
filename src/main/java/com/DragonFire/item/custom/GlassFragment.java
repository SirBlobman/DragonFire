package com.DragonFire.item.custom;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class GlassFragment extends QuickItem {
    public GlassFragment() {
        super("glass_fragment");
        setHasSubtypes(true);
    } 
    
    @Override
    public String getUnlocalizedName(ItemStack is) {
        int meta = is.getMetadata();
        String add;
        if(meta < 16) {
            EnumDyeColor dye = EnumDyeColor.byDyeDamage(meta);
            add = dye.getUnlocalizedName();
        } else {
            if(meta == 16) add = "obsidian";
            else if(meta == 17) add = "clear";
            else add = "invalid";
        }
        
        String name = super.getUnlocalizedName();
        String nname = (name + "." + add);
        return nname;
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if(isInCreativeTab(tab)) {
            for (int i = 0; i < 18; ++i) {
                ItemStack is = new ItemStack(this, 1, i);
                list.add(is);
            }
        }
    }
}
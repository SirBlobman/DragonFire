package com.DragonFire.creative;

import com.DragonFire.item.DFItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class DFTabs {
    public static final CreativeTabs DRAGONFIRE = new CreativeTabs("DragonFire") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = DFItems.BAT_WINGS;
            ItemStack is = new ItemStack(i);
            return is;
        }
    };
}
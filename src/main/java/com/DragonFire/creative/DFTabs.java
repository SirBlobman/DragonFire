package com.DragonFire.creative;

import com.DragonFire.item.DFItems;
import com.DragonFire.utility.Util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public final class DFTabs {
    public static final CreativeTabs DRAGONFIRE = new CreativeTabs("DragonFire") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = DFItems.BAT_WING;
            ItemStack is = new ItemStack(i);
            return is;
        }
    };
    
    public static final CreativeTabs COOKIES = new CreativeTabs("DragonFire_Cookies") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = Items.COOKIE;
            ItemStack is = new ItemStack(i);
            return is;
        }
        
        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> list) {
            Item i = Items.COOKIE;
            ItemStack is = new ItemStack(i);
            list.add(is);
            super.displayAllRelevantItems(list);
        }
    };
    
    public static final CreativeTabs SPAWN_EGGS = new CreativeTabs("DragonFire_Spawn_Eggs") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = Items.SPAWN_EGG;
            ItemStack is = new ItemStack(i);
            return is;
        }
        
        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> list) {
            super.displayAllRelevantItems(list);
            ItemStack is = Util.getSpawnEgg("dragonfire:mummy");
            list.add(is);
        }
    };
    
    public static final CreativeTabs ARMOR_AND_TOOLS = new CreativeTabs("DragonFire_Armor_and_Tools") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = DFItems.EMERALD_HELMET;
            ItemStack is = new ItemStack(i);
            return is;
        }
    };
}
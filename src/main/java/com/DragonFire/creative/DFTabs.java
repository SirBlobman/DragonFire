package com.DragonFire.creative;

import com.DragonFire.entity.EntityMummy;
import com.DragonFire.item.DFItems;
import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityIllusionIllager;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
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
            ItemStack is1 = ItemUtil.getSpawnEgg(EntityMummy.class);
            ItemStack is2 = ItemUtil.getSpawnEgg(EntityIronGolem.class);
            ItemStack is3 = ItemUtil.getSpawnEgg(EntitySnowman.class);
            ItemStack is4 = ItemUtil.getSpawnEgg(EntityWither.class);
            ItemStack is5 = ItemUtil.getSpawnEgg(EntityIllusionIllager.class);
            List<ItemStack> toAdd = Util.newList(is1, is2, is3, is4, is5);
            list.addAll(toAdd);
        }
    };
    
    public static final CreativeTabs ARMOR_AND_TOOLS = new CreativeTabs("DragonFire_Armor_and_Tools") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = DFItems.EMERALD_HELMET;
            ItemStack is = new ItemStack(i);
            return is;
        }
        
        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> list) {
            super.displayAllRelevantItems(list);
            ItemStack is1 = ItemUtil.getEnchantBook("dragonfire:auto_smelt", 1);
            ItemStack is2 = ItemUtil.getEnchantBook("dragonfire:extinguish", 1);
            ItemStack is3 = ItemUtil.getEnchantBook("dragonfire:extinguish", 2);
            List<ItemStack> toAdd = Util.newList(is1, is2, is3);
            list.addAll(toAdd);
        }
    };
    
    public static final CreativeTabs FOOD_AND_DRINK = new CreativeTabs("DragonFire_Food_and_Drink") {
        @Override
        public ItemStack getTabIconItem() {
            Item i = DFItems.COOKED_BACON;
            ItemStack is = new ItemStack(i);
            return is;
        }
    };
}
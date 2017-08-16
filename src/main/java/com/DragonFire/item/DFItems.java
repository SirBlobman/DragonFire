package com.DragonFire.item;

import com.DragonFire.item.armor.RabbitBoots;
import com.DragonFire.item.tool.EnderArrow;
import com.DragonFire.item.tool.EnderBow;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFItems {
    //Armor
    public static final RabbitBoots RABBIT_BOOTS = new RabbitBoots();
    
    //Tools and Weapons
    public static final EnderBow ENDER_BOW = new EnderBow();
    public static final EnderArrow ENDER_ARROW = new EnderArrow();
    
    //Food
    public static final FoodItem RAW_CALAMARI = new FoodItem("raw_calamari", 1, 0.4F, true);
    public static final FoodItem COOKED_CALAMARI = new FoodItem("cooked_calamari", 5, 6.0F, false);
    public static final FoodItem FRIED_EGG = new FoodItem("fried_egg", 5, 6.0F, true);
    public static final FoodItem PINEAPPLE_SLICE = new FoodItem("pineapple_slice", 4, 2.4F, false);
    public static final FoodItem POTION_COOKIE = new PotionCookie();
    
    //Mob Drops
    public static final Item BAT_WINGS = new QuickItem("bat_wings");
    public static final MummyHead MUMMY_HEAD = new MummyHead();
    public static final Item MUMMY_RAGS = new QuickItem("mummy_rags");
    
    public static final void register(IForgeRegistry<Item> ifr) {
        //Armor
        ifr.registerAll(RABBIT_BOOTS);
        
        //Tools and Weapons
        ifr.registerAll(ENDER_BOW, ENDER_ARROW);
        
        //Food
        ifr.registerAll(RAW_CALAMARI, COOKED_CALAMARI, FRIED_EGG, PINEAPPLE_SLICE, POTION_COOKIE);
        
        //Mob Drops
        ifr.registerAll(BAT_WINGS, MUMMY_HEAD, MUMMY_RAGS);
    }
}
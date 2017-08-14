package com.DragonFire.item;

import com.DragonFire.item.armor.RabbitBoots;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFItems {
    public static final RabbitBoots RABBIT_BOOTS = new RabbitBoots();
    
    public static final FoodItem RAW_CALAMARI = new FoodItem("raw_calamari", 2, 0.4F, true);
    public static final FoodItem COOKED_CALAMARI = new FoodItem("cooked_calamari", 5, 6.0F, false);
    public static final FoodItem FRIED_EGG = new FoodItem("fried_egg", 5, 6.0F, true);
    
    public static final void register(IForgeRegistry<Item> ifr) {
        //Armor
        ifr.register(RABBIT_BOOTS);
        
        //Food
        ifr.registerAll(RAW_CALAMARI, COOKED_CALAMARI, FRIED_EGG);
    }
}
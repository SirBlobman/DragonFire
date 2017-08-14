package com.DragonFire.items;

import com.DragonFire.items.armor.RabbitBoots;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFItems {
    public static final RabbitBoots RABBIT_BOOTS = new RabbitBoots();
    public static final FoodItem RAW_CALAMARI = new FoodItem("raw_calamari", 2, 0.4F, true);
    public static final FoodItem COOKED_CALAMARI = new FoodItem("cooked_calamari", 5, 6.0F, true);
    
    public static final void register(IForgeRegistry<Item> ifr) {
        //Armor
        ifr.register(RABBIT_BOOTS);
        
        //Food
        ifr.registerAll(RAW_CALAMARI, COOKED_CALAMARI);
    }
}
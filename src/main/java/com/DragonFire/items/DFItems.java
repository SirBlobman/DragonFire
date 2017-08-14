package com.DragonFire.items;

import com.DragonFire.items.armor.RabbitBoots;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFItems {
    public static final RabbitBoots RABBIT_BOOTS = new RabbitBoots();
    
    public static final void register(IForgeRegistry<Item> ifr) {
        ifr.registerAll(RABBIT_BOOTS);
    }
}
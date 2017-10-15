package com.DragonFire.listener;

import com.DragonFire.item.DFItems;

import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class ListenCustomCrafting {
    @SubscribeEvent
    public void craft(ItemCraftedEvent e) {
        ItemStack crafted = e.crafting;
        if(crafted != null && !crafted.isEmpty()) {
            Item item = crafted.getItem();
            if(item == DFItems.GLASS_DAGGER) crafted.addEnchantment(Enchantments.SHARPNESS, 1);
        }
    }
}
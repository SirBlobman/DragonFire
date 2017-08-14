package com.DragonFire.recipe;

import com.DragonFire.item.DFItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class DFRecipes {
    public static void furnaceRecipes() {
        /* Normal Egg -> Fried Egg */ 
        GameRegistry.addSmelting(Items.EGG, new ItemStack(DFItems.FRIED_EGG, 1), 0.0F);
        
        /* Raw Calamari -> Cooked Calamari */
        GameRegistry.addSmelting(DFItems.RAW_CALAMARI, new ItemStack(DFItems.COOKED_CALAMARI, 1), 0.0F);
    }
}
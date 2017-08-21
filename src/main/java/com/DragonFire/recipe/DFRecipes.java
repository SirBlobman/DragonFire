package com.DragonFire.recipe;

import com.DragonFire.item.DFItems;
import com.DragonFire.potion.type.DFPotionTypes;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFRecipes {
    public static void craftingRecipes(IForgeRegistry<IRecipe> ifr) {
        ifr.register(new RecipePotionCookie());
    }
    
    public static void furnaceRecipes() {
        /* Normal Egg -> Fried Egg */ 
        GameRegistry.addSmelting(Items.EGG, new ItemStack(DFItems.FRIED_EGG, 1), 0.0F);
        
        /* Raw Calamari -> Cooked Calamari */
        GameRegistry.addSmelting(DFItems.RAW_CALAMARI, new ItemStack(DFItems.COOKED_CALAMARI, 1), 0.0F);
        
        /* Raw Bacon -> Cooked Bacon */
        GameRegistry.addSmelting(DFItems.RAW_BACON, new ItemStack(DFItems.COOKED_BACON, 1), 0.0F);
    }
    
    public static void brewingRecipes() {
        PotionHelper.addMix(PotionTypes.AWKWARD, DFItems.BAT_WING, DFPotionTypes.LEVITATION);
    }
}
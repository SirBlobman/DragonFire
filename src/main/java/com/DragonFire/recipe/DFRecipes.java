package com.DragonFire.recipe;

import com.DragonFire.item.DFItems;
import com.DragonFire.utility.Util;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class DFRecipes {
    public static void furnaceRecipes() {
        /* Normal Egg -> Fried Egg */ 
        GameRegistry.addSmelting(Items.EGG, new ItemStack(DFItems.FRIED_EGG, 1), 0.0F);
        
        /* Raw Calamari -> Cooked Calamari */
        GameRegistry.addSmelting(DFItems.RAW_CALAMARI, new ItemStack(DFItems.COOKED_CALAMARI, 1), 0.0F);
    }
    
    public static void brewingRecipes() {
        ItemStack potion = new ItemStack(Items.POTIONITEM);
        ItemStack awkPotion = PotionUtils.addPotionToItemStack(potion, PotionTypes.AWKWARD);
        ItemStack levitationPotion = PotionUtils.appendEffects(potion, Util.newList(new PotionEffect(MobEffects.LEVITATION, 100, 10)));
        
        /* Bat Wings -> Levitation Potion */
        BrewingRecipeRegistry.addRecipe(awkPotion, new ItemStack(DFItems.BAT_WINGS), levitationPotion);
    }
}
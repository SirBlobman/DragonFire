package com.DragonFire.recipe;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.item.DFItems;
import com.DragonFire.potion.type.DFPotionTypes;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
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
        
        /* Copper Ore -> Copper Ingot */
        GameRegistry.addSmelting(DFBlocks.ITEM_COPPER_ORE, new ItemStack(DFItems.COPPER_INGOT, 1), 0.0F);
        
        /* Copper Armor/Tools -> Copper Nuggets */
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_HELMET, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_CHESTPLATE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_LEGGINGS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_BOOTS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_SWORD, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_AXE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_PICKAXE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_SHOVEL, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        GameRegistry.addSmelting(new ItemStack(DFItems.COPPER_HOE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
    }
    
    public static void brewingRecipes() {
        PotionHelper.addMix(PotionTypes.AWKWARD, DFItems.BAT_WING, DFPotionTypes.LEVITATION);
        PotionHelper.addMix(DFPotionTypes.LEVITATION, Items.REDSTONE, DFPotionTypes.LONG_LEVITATION);
        PotionHelper.addMix(DFPotionTypes.LEVITATION, Items.GLOWSTONE_DUST, DFPotionTypes.STRONG_LEVITATION);
        PotionHelper.addMix(PotionTypes.POISON, Items.GOLDEN_CARROT, DFPotionTypes.BLINDING);
        PotionHelper.addMix(DFPotionTypes.BLINDING, Items.REDSTONE, DFPotionTypes.LONG_BLINDING);
        PotionHelper.addMix(DFPotionTypes.BLINDING, Items.GLOWSTONE_DUST, DFPotionTypes.STRONG_BLINDING);
        PotionHelper.addMix(PotionTypes.AWKWARD, DFItems.WITHERED_BONE, DFPotionTypes.DECAY);
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.MILK_BUCKET), new ItemStack(Items.ROTTEN_FLESH), new ItemStack(DFItems.SPOILED_MILK));
    }
}
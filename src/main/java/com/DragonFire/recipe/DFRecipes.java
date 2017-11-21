package com.DragonFire.recipe;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;
import com.DragonFire.item.DFItems;
import com.DragonFire.potion.type.DFPotionTypes;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static net.minecraftforge.fml.common.registry.GameRegistry.addSmelting;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFRecipes {
    public static void craftingRecipes(IForgeRegistry<IRecipe> ifr) {
        ifr.registerAll(new RecipeDyableBackpack(), new RecipePotionCookie());
        glassRecipes();
    }
    
    public static void furnaceRecipes() {
        /* Normal Egg -> Fried Egg */ 
        addSmelting(Items.EGG, new ItemStack(DFItems.FRIED_EGG, 1), 0.0F);
        
        /* Raw Calamari -> Cooked Calamari */
        addSmelting(DFItems.RAW_CALAMARI, new ItemStack(DFItems.COOKED_CALAMARI, 1), 0.0F);
        
        /* Raw Bacon -> Cooked Bacon */
        addSmelting(DFItems.RAW_BACON, new ItemStack(DFItems.COOKED_BACON, 1), 0.0F);
        
        /* Copper Ore -> Copper Ingot */
        addSmelting(DFBlocks.ITEM_COPPER_ORE, new ItemStack(DFItems.COPPER_INGOT, 1), 0.0F);
        
        /* Copper Armor/Tools -> Copper Nuggets */
        addSmelting(new ItemStack(DFItems.COPPER_HELMET, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_CHESTPLATE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_LEGGINGS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_BOOTS, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_SWORD, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_AXE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_PICKAXE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_SHOVEL, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        addSmelting(new ItemStack(DFItems.COPPER_HOE, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(DFItems.COPPER_NUGGET), 1.0F);
        
        /* Nether Ores -> Vanilla Ores */
        addSmelting(DFBlocks.NETHER_GOLD_ORE, new ItemStack(Items.GOLD_INGOT, 1), 10.0F);
        
        /* Sea Weed -> Dried Sea Weed */
        addSmelting(DFItems.SEA_WEED, new ItemStack(DFItems.DRIED_SEA_WEED, 1), 1.0F);
    }
    
    public static void brewingRecipes() {
        PotionHelper.addMix(PotionTypes.AWKWARD, DFItems.BAT_WING, DFPotionTypes.LEVITATION);
        PotionHelper.addMix(DFPotionTypes.LEVITATION, Items.REDSTONE, DFPotionTypes.LONG_LEVITATION);
        PotionHelper.addMix(DFPotionTypes.LEVITATION, Items.GLOWSTONE_DUST, DFPotionTypes.STRONG_LEVITATION);
        PotionHelper.addMix(PotionTypes.POISON, Items.GOLDEN_CARROT, DFPotionTypes.BLINDING);
        PotionHelper.addMix(DFPotionTypes.BLINDING, Items.REDSTONE, DFPotionTypes.LONG_BLINDING);
        PotionHelper.addMix(DFPotionTypes.BLINDING, Items.GLOWSTONE_DUST, DFPotionTypes.STRONG_BLINDING);
        PotionHelper.addMix(PotionTypes.AWKWARD, DFItems.WITHERED_BONE, DFPotionTypes.DECAY);
        PotionHelper.addMix(PotionTypes.AWKWARD, DFBlocks.ITEM_RADIOACTIVE_MUSHROOM, DFPotionTypes.RADIATION);
        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.MILK_BUCKET), new ItemStack(Items.ROTTEN_FLESH), new ItemStack(DFItems.SPOILED_MILK));
    }
    
    private static void glassRecipes() {
        for(int i = 0; i < 18; i++) regGlassRecipe(i);
    }
    
    private static Ingredient[] getGlassFragments(int meta) {
        ItemStack is = new ItemStack(DFItems.GLASS_FRAGMENT, 1, meta);
        Ingredient i = Ingredient.fromStacks(is);
        
        Ingredient[] ii = new Ingredient[4];
        Arrays.fill(ii, i);
        return ii;
    }
    
    private static void regGlassRecipe(int meta) {
        ItemStack out = ItemStack.EMPTY;
        if(meta < 16) out = new ItemStack(Blocks.STAINED_GLASS, 1, EnumDyeColor.byDyeDamage(meta).getMetadata());
        else if(meta == 16) out = new ItemStack(DFBlocks.OBSIDIAN_GLASS);
        else if(meta == 17) out = new ItemStack(Blocks.GLASS);
        else out = ItemStack.EMPTY;
        
        ResourceLocation name1 = new ResourceLocation(DragonFire.MODID, "glass_fragments_" + meta);
        ResourceLocation group = new ResourceLocation(DragonFire.MODID, "glass");
        GameRegistry.addShapelessRecipe(name1, group, out, getGlassFragments(meta));
    }
}
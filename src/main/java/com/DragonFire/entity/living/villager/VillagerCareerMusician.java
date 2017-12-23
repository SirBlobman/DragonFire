package com.DragonFire.entity.living.villager;

import com.DragonFire.item.DFItems;

import java.util.Random;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillagerCareerMusician extends VillagerCareer {
    private static final ResourceLocation rl = new ResourceLocation("minecraft", "librarian");
    private static final VillagerProfession LIBRARIAN = ForgeRegistries.VILLAGER_PROFESSIONS.getValue(rl);
    
    public static ITradeList BASIC_TRADES = new ITradeList() {
        @Override
        public void addMerchantRecipe(IMerchant im, MerchantRecipeList mrl, Random rand) {
            ItemStack[] toSell = new ItemStack[] {
                new ItemStack(Blocks.NOTEBLOCK, 1),
                new ItemStack(Blocks.JUKEBOX, 1)
            };
            
            for(ItemStack sell : toSell) {
                ItemStack buy = new ItemStack(Items.EMERALD, rand.nextInt(2) + 1);
                MerchantRecipe mr = new MerchantRecipe(buy, sell);
                mrl.add(mr);
            }
        }
    },
    MIDDLE_TRADES = new ITradeList() {
        @Override
        public void addMerchantRecipe(IMerchant im, MerchantRecipeList mrl, Random rand) {
            for(Item item : ForgeRegistries.ITEMS) {
                if(item instanceof ItemRecord && item != DFItems.RECORD_DOG) {
                    ItemStack buy = new ItemStack(Items.EMERALD, rand.nextInt(3) + 1);
                    ItemStack sell = new ItemStack(item, 1);
                    MerchantRecipe mr = new MerchantRecipe(buy, sell);
                    mrl.add(mr);
                }
            }
        }
    },
    SPECIAL_TRADES = new ITradeList() {
        @Override
        public void addMerchantRecipe(IMerchant im, MerchantRecipeList mrl, Random rand) {
            ItemStack buy = new ItemStack(Items.EMERALD, rand.nextInt(5) + 1);
            ItemStack sell = new ItemStack(DFItems.RECORD_DOG, 1);
            MerchantRecipe mr = new MerchantRecipe(buy, sell);
            mrl.add(mr);
        }
    };
    
    public VillagerCareerMusician() {
        super(LIBRARIAN, "musician");
    }
    
    public void register() {
        addTrade(1, BASIC_TRADES);
        addTrade(2, MIDDLE_TRADES);
        addTrade(3, SPECIAL_TRADES);
    }
}
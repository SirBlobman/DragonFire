package com.DragonFire.compat.JustEnoughItems;

import com.DragonFire.item.DFItems;
import com.DragonFire.utility.Util;

import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class PotionCookieRecipeWrapper implements IShapedCraftingRecipeWrapper {
    private final List<ItemStack> inputs;
    private final ItemStack output;
    
    public PotionCookieRecipeWrapper(PotionType pt) {
        ItemStack cookie = new ItemStack(Items.COOKIE);
        ItemStack lingering = new ItemStack(Items.LINGERING_POTION);
        ItemStack potion = PotionUtils.addPotionToItemStack(lingering, pt);
        this.inputs = Util.newList(
            cookie, cookie, cookie,
            cookie, potion, cookie,
            cookie, cookie, cookie
        );
        ItemStack potionCookie = new ItemStack(DFItems.POTION_COOKIE);
        this.output = PotionUtils.addPotionToItemStack(potionCookie, pt);
    }
    
    @Override
    public void getIngredients(IIngredients ii) {
        ii.setInputs(ItemStack.class, inputs);
        ii.setOutput(ItemStack.class, output);
    }
    
    public int getWidth() {return 3;}
    public int getHeight() {return 3;}
}
package com.DragonFire.compat.JustEnoughItems;

import com.DragonFire.item.DFItems;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.plugins.vanilla.brewing.PotionSubtypeInterpreter;

@JEIPlugin
public class DragonFirePlugin implements IModPlugin {
    @Override
    public void registerItemSubtypes(ISubtypeRegistry istr) {
        istr.registerSubtypeInterpreter(DFItems.POTION_COOKIE, PotionSubtypeInterpreter.INSTANCE);
    }
    
    @Override
    public void register(IModRegistry imr) {
        imr.addRecipes(PotionCookieRecipeMaker.getPotionCookieRecipes(), VanillaRecipeCategoryUid.CRAFTING);
    }
}
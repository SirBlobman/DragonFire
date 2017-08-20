package com.DragonFire.compat.JustEnoughItems;

import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;

public final class PotionCookieRecipeMaker {
    public static List<PotionCookieRecipeWrapper> getPotionCookieRecipes() {
        List<PotionCookieRecipeWrapper> list = Util.newList();
        RegistryNamespacedDefaultedByKey<ResourceLocation, PotionType> potionTypes = PotionType.REGISTRY;
        for(ResourceLocation rl : potionTypes.getKeys()) {
            PotionType pt = potionTypes.getObject(rl);
            PotionCookieRecipeWrapper pcrw = new PotionCookieRecipeWrapper(pt);
            list.add(pcrw);
        } return list;
    }
}
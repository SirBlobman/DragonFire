package com.DragonFire.item.food;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemFood;

public class QuickFood extends ItemFood {
    /**
     * Create an instance of a food item
     * @param name Unlocalized name for the food item
     * @param foodPoints Amount of food that this item restores. 1 point = &frac12; food bar
     * @param saturation Amount of saturation that this item restores
     * @param wolfFood Can this be fed to wolves?
     */
    public QuickFood(String name, int foodPoints, float saturation, boolean wolfFood) {
        super(foodPoints, saturation, wolfFood);
        setUnlocalizedName(name); setRegistryName(name);
        setCreativeTab(DFTabs.FOOD_AND_DRINK);
    }
}
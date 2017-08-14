package com.DragonFire.items;

import net.minecraft.item.ItemFood;

public class FoodItem extends ItemFood {
    public FoodItem(String name, int foodPoints, float saturation, boolean wolfFood) {
        super(foodPoints, saturation, wolfFood);
        setUnlocalizedName(name); setRegistryName(name);
    }
}
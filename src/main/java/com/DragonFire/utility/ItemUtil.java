package com.DragonFire.utility;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ItemUtil extends Util {
    public static boolean isAir(ItemStack is) {
        if(is == null) return true;
        if(is == ItemStack.EMPTY) return true;
        Item i = is.getItem();
        if(i == null) return true;
        if(i instanceof ItemBlock) {
            ItemBlock ib = (ItemBlock) i;
            Block b = ib.getBlock();
            return isAir(b);
        }
        
        boolean air = (i == Items.AIR);
        return air;
    }
    
    public static boolean isAir(Block b) {
        if(b == null) return true;
        boolean air = (b == Blocks.AIR);
        return air;
    }
    
    public static ItemStack smelt(ItemStack is) {
        FurnaceRecipes furnace = FurnaceRecipes.instance();
        ItemStack cook = furnace.getSmeltingResult(is);
        return cook;
    }

    public static ItemStack getSpawnEgg(String entityID) {
        try {
            String p1 = "{id:\"minecraft:spawn_egg\", Count:1b, tag:{EntityTag:{id:\"";
            String p2 = "\"}}}";
            String json = p1 + entityID + p2;
            NBTTagCompound nbt = JsonToNBT.getTagFromJson(json);
            ItemStack is = new ItemStack(nbt);
            return is;
        } catch(Throwable ex) {
            Item egg = Items.EGG;
            ItemStack is = new ItemStack(egg);
            return is;
        }
    }
    
    public static ItemStack getSpawnEgg(Class<? extends Entity> clazz) {
        EntityEntry ee = EntityRegistry.getEntry(clazz);
        ResourceLocation rl = ee.getRegistryName();
        ItemStack is = getSpawnEgg(rl.toString());
        return is;
    }

    public static ItemStack getEnchantBook(String enchantID, int level) {
        Item i = Items.ENCHANTED_BOOK;
        ItemStack is = new ItemStack(i);
        
        Enchantment ench = Enchantment.getEnchantmentByLocation(enchantID);
        if(ench != null) is.addEnchantment(ench, level);
        return is;
    }
}
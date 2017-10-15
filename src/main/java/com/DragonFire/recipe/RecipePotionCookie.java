package com.DragonFire.recipe;

import com.DragonFire.item.DFItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class RecipePotionCookie extends Impl<IRecipe> implements IRecipe {
    public RecipePotionCookie() {setRegistryName("potion_cookie");}
    
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        if (inv.getWidth() == 3 && inv.getHeight() == 3) {
            for (int i = 0; i < inv.getWidth(); ++i) {
                for (int j = 0; j < inv.getHeight(); ++j) {
                    ItemStack itemstack = inv.getStackInRowAndColumn(i, j);

                    if (itemstack.isEmpty()) return false;
                    Item item = itemstack.getItem();

                    if (i == 1 && j == 1) {
                        if (item != Items.LINGERING_POTION) return false;
                    } else if (item != Items.COOKIE) return false;
                }
            }

            return true;
        } else return false;
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack itemstack = inv.getStackInRowAndColumn(1, 1);

        if (itemstack.getItem() != Items.LINGERING_POTION) return ItemStack.EMPTY;
        else {
            ItemStack itemstack1 = new ItemStack(DFItems.POTION_COOKIE, 8);
            PotionUtils.addPotionToItemStack(itemstack1, PotionUtils.getPotionFromItem(itemstack));
            PotionUtils.appendEffects(itemstack1, PotionUtils.getFullEffectsFromItem(itemstack));
            return itemstack1;
        }
    }

    public ItemStack getRecipeOutput() {return ItemStack.EMPTY;}
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {return NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);}
    public boolean isHidden() {return true;}
    public boolean canFit(int width, int height) {return width == 3 && height == 3;}
}
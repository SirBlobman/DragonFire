package com.DragonFire.recipe;

import com.DragonFire.item.armor.backpack.DyableBackpack;
import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.oredict.DyeUtils;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class RecipeDyableBackpack extends Impl<IRecipe> implements IRecipe {
    public RecipeDyableBackpack() {setRegistryName("backpack");}
    
    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        ItemStack is = ItemStack.EMPTY;
        List<ItemStack> list = Util.newList();
        for(int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack is1 = inv.getStackInSlot(i);
            if(!is1.isEmpty()) {
                if(is1.getItem() instanceof DyableBackpack) {
                    if(!is.isEmpty()) return false;
                    is = is1;
                } else {
                    if(!DyeUtils.isDye(is1)) return false;
                    list.add(is);
                }
            }
        } return !is.isEmpty() && !list.isEmpty();
    }
    
    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack is = ItemStack.EMPTY;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        DyableBackpack db = null;
        
        for(int k = 0; k < inv.getSizeInventory(); ++k) {
            ItemStack is1 = inv.getStackInSlot(k);
            if(!is1.isEmpty()) {
                if(is1.getItem() instanceof DyableBackpack) {
                    db = (DyableBackpack) is1.getItem();
                    if(!is.isEmpty()) return ItemStack.EMPTY;
                    is = is1.copy();
                    is.setCount(1);
                    if(db.hasColor(is1)) {
                        int l = db.getColor(is);
                        float f0 = (l >> 16 & 255) / 255.0F;
                        float f1 = (l >> 8 & 255) / 255.0F;
                        float f2 = (l & 255) / 255.0F;
                        i = (int) (i + Math.max(f0, Math.max(f1, f2)) * 255.0F);
                        aint[0] = (int) (aint[0] + f0 * 255.0F);
                        aint[1] = (int) (aint[1] + f1 * 255.0F);
                        aint[2] = (int) (aint[2] + f2 * 255.0F);
                        ++j;
                    }
                } else {
                    if(!DyeUtils.isDye(is1)) return ItemStack.EMPTY;
                    float[] afloat = DyeUtils.colorFromStack(is1).get().getColorComponentValues();
                    int l1 = (int) (afloat[0] * 255.0F);
                    int i2 = (int) (afloat[1] * 255.0F);
                    int j2 = (int) (afloat[2] * 255.0F);
                    i += Math.max(l1, Math.max(i2, j2));
                    aint[0] += l1;
                    aint[1] += i2;
                    aint[2] += j2;
                    ++j;
                }
            }
        }
        
        if(db == null) return ItemStack.EMPTY;
        else {
            int i1 = aint[0] / j;
            int j1 = aint[1] / j;
            int k1 = aint[2] / j;
            float f3 = ((float) i / (float) j);
            float f4 = Math.max(i1, Math.max(j1, k1));
            i1 = (int) (i1 * f3 / f4);
            j1 = (int) (j1 * f3 / f4);
            k1 = (int) (k1 * f3 / f4);
            int k2 = (i1 << 8) + j1;
            k2 = (k2 << 8) + k1;
            db.setColor(is, k2);
            return is;
        }
    }
    
    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> nonNullList = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for(int i = 0; i < nonNullList.size(); ++i) {
            ItemStack is = inv.getStackInSlot(i);
            nonNullList.set(i, ForgeHooks.getContainerItem(is));
        } return nonNullList;
    }
    
    public ItemStack getRecipeOutput() {return ItemStack.EMPTY;}
    public boolean isHidden() {return true;}
    public boolean canFit(int width, int height) {return width * height >= 2;}
}
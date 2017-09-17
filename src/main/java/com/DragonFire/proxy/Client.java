package com.DragonFire.proxy;

import com.DragonFire.item.DFItems;
import com.DragonFire.render.DFRendering;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Client extends Common {
    @Override
    public void pre(FMLPreInitializationEvent e) {
        super.pre(e);
        DFRendering.entities();
    }
    
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        //Potion Cookie Effects
        Minecraft mc = Minecraft.getMinecraft();
        ItemColors ic = mc.getItemColors();
        ic.registerItemColorHandler(new IItemColor() {
            @Override
            public int getColorFromItemstack(ItemStack is, int tint) {
                if(tint == 1) {
                    int color = PotionUtils.getColor(is);
                    return color;
                } else return -1;
            }
        }, DFItems.POTION_COOKIE);
    }
    
    @Override
    public void post(FMLPostInitializationEvent e) {
        super.post(e);
    }
    
    @Override
    public void items(IForgeRegistry<Item> ifr) {
        super.items(ifr);
        DFRendering.items();
        DFRendering.blocks();
    }
    
    @Override
    public void blocks(IForgeRegistry<Block> ifr) {
        super.blocks(ifr);
    }
    
    @Override
    public void recipes(IForgeRegistry<IRecipe> ifr) {
        super.recipes(ifr);
    }

    @Override
    public void potions(IForgeRegistry<Potion> ifr) {
        super.potions(ifr);
    }
    
    @Override
    public void potionTypes(IForgeRegistry<PotionType> ifr) {
        super.potionTypes(ifr);
    }
    
    @Override
    public void enchants(IForgeRegistry<Enchantment> ifr) {
        super.enchants(ifr);
    }
}
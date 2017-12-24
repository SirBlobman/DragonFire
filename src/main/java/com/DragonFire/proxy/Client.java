package com.DragonFire.proxy;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.block.item.LeavesItemBlock;
import com.DragonFire.item.DFItems;
import com.DragonFire.item.armor.backpack.DyableBackpack;
import com.DragonFire.item.armor.backpack.KeyBindBackpack;
import com.DragonFire.render.DFRendering;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Client extends Common {
    @Override
    public void pre(FMLPreInitializationEvent e) {
        super.pre(e);
        DFRendering.entities();
        ClientRegistry.registerKeyBinding(KeyBindBackpack.backpack);
    }
    
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        DFRendering.customColors();
    }
    
    @Override
    public void post(FMLPostInitializationEvent e) {
        super.post(e);
    }
    
    @Override
    public void items(IForgeRegistry<Item> ifr) {
        super.items(ifr);
        DFRendering.items();
        DFRendering.special();
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

    @Override
    public void sounds(IForgeRegistry<SoundEvent> ifr) {
        super.sounds(ifr);
    }
    
    @Override
    public void biomes(IForgeRegistry<Biome> ifr) {
        super.biomes(ifr);
    }
}
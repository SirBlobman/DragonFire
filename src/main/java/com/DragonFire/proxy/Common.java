package com.DragonFire.proxy;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;
import com.DragonFire.block.dispenser.BehaviorCustomProjectileDispense;
import com.DragonFire.block.tile.TileEntityNetherBrickFurnace;
import com.DragonFire.enchantment.DFEnchants;
import com.DragonFire.entity.DFEntities;
import com.DragonFire.forge.DFOreDictionary;
import com.DragonFire.item.DFItems;
import com.DragonFire.network.gui.DFGuiHandler;
import com.DragonFire.potion.effect.DFPotions;
import com.DragonFire.potion.type.DFPotionTypes;
import com.DragonFire.recipe.DFRecipes;
import com.DragonFire.sound.DFSoundHandler;
import com.DragonFire.world.DragonFireWorldGen;
import com.DragonFire.world.biome.DFBiomes;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
    public void pre(FMLPreInitializationEvent e) {
        DFEntities.entities();
        GameRegistry.registerWorldGenerator(new DragonFireWorldGen(), 3);
        GameRegistry.registerTileEntity(TileEntityNetherBrickFurnace.class, "nether_brick_furnace");
    }
    
    public void init(FMLInitializationEvent e) {
        DragonFire.PACKET_HANDLER.initialize();
        NetworkRegistry.INSTANCE.registerGuiHandler(DragonFire.INSTANCE, new DFGuiHandler());
        DFRecipes.furnaceRecipes();
        DFRecipes.brewingRecipes();
    }
    
    public void post(FMLPostInitializationEvent e) {
        DFOreDictionary.registerItems();
        DFOreDictionary.registerBlocks();
    }
    
    public void items(IForgeRegistry<Item> ifr) {
        DFItems.register(ifr);
        DFBlocks.register2(ifr);
    }

    public void blocks(IForgeRegistry<Block> ifr) {
        DFBlocks.register1(ifr);
        BehaviorCustomProjectileDispense.register();
    }
    
    public void recipes(IForgeRegistry<IRecipe> ifr) {
        DFRecipes.craftingRecipes(ifr);
    }

    public void potions(IForgeRegistry<Potion> ifr) {
        DFPotions.register(ifr);
    }
    
    public void potionTypes(IForgeRegistry<PotionType> ifr) {
        DFPotionTypes.register(ifr);
    }

    public void enchants(IForgeRegistry<Enchantment> ifr) {
        DFEnchants.register(ifr);
    }

    public void sounds(IForgeRegistry<SoundEvent> ifr) {
        DFSoundHandler.register(ifr);
    }

    public void biomes(IForgeRegistry<Biome> ifr) {
        DFBiomes.register(ifr);
    }
}
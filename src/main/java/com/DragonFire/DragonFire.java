package com.DragonFire;

import com.DragonFire.command.CommandOreDictionary;
import com.DragonFire.config.DFConfig;
import com.DragonFire.item.armor.backpack.KeyBindBackpack;
import com.DragonFire.listener.*;
import com.DragonFire.network.DragonFirePacketHandler;
import com.DragonFire.proxy.Common;
import com.DragonFire.utility.Util;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = DragonFire.MODID, name = DragonFire.NAME, version = DragonFire.VERSION)
public class DragonFire {
    public static final String MODID = "dragonfire";
    public static final String NAME = "Dragon Fire";
    public static final String VERSION = "0.0.1";
    
    public static final String PACKAGE = "com.DragonFire";
    public static final String PROXY = PACKAGE + ".proxy";
    public static final String CLIENT = PROXY + ".Client";
    public static final String SERVER = PROXY + ".Server";
    
    @Instance
    public static DragonFire INSTANCE = new DragonFire();
    
    @SidedProxy(clientSide=CLIENT, serverSide=SERVER)
    public static Common proxy;
    
    public static final DragonFirePacketHandler PACKET_HANDLER = new DragonFirePacketHandler();
    public static File FOLDER;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e) {
        FOLDER = e.getModConfigurationDirectory();
        DFConfig.load();
        Util.regEvents(this, 
            new ListenCustomEvents(),
            new ListenCustomDrops(),
            new ListenCustomEnchants(),
            new ListenCustomLoot(),
            new ListenCustomTarget(),
            new ListenCustomCrafting(),
            new ListenCustomPotions(),
            new ListenCustomItems(),
            new KeyBindBackpack()
        );
        proxy.pre(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
    
    @EventHandler
    public void post(FMLPostInitializationEvent e) {
        proxy.post(e);
    }
    
    @EventHandler
    public void start(FMLServerStartingEvent e) {
        e.registerServerCommand(new CommandOreDictionary());
    }
    
    @SubscribeEvent
    public void items(Register<Item> e) {
        IForgeRegistry<Item> ifr = e.getRegistry();
        proxy.items(ifr);
    }
    
    @SubscribeEvent
    public void blocks(Register<Block> e) {
        IForgeRegistry<Block> ifr = e.getRegistry();
        proxy.blocks(ifr);
    }
    
    @SubscribeEvent
    public void recipes(Register<IRecipe> e) {
        IForgeRegistry<IRecipe> ifr = e.getRegistry();
        proxy.recipes(ifr);
    }
    
    @SubscribeEvent
    public void potions(Register<Potion> e) {
        IForgeRegistry<Potion> ifr = e.getRegistry();
        proxy.potions(ifr);
    }
    
    @SubscribeEvent
    public void potionTypes(Register<PotionType> e) {
        IForgeRegistry<PotionType> ifr = e.getRegistry();
        proxy.potionTypes(ifr);
    }
    
    @SubscribeEvent
    public void enchantments(Register<Enchantment> e) {
        IForgeRegistry<Enchantment> ifr = e.getRegistry();
        proxy.enchants(ifr);
    }
}
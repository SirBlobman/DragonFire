package com.DragonFire;

import com.DragonFire.listener.ListenCustomDrops;
import com.DragonFire.proxy.Common;
import com.DragonFire.utility.Util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e) {
        Util.regEvents(this, 
            new ListenCustomDrops()
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
}
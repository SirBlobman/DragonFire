package com.DragonFire;

import com.DragonFire.proxy.Common;
import com.DragonFire.utility.Util;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = DragonFire.MODID, name = DragonFire.NAME, version = DragonFire.VERSION)
public class DragonFire {
    public static final String MODID = "dragonfire";
    public static final String NAME = "Dragon Fire";
    public static final String VERSION = "0.0.1";
    
    public static final String PACKAGE = "com.DragonFire";
    public static final String PROXY = PACKAGE + ".proxy";
    public static final String CLIENT = PROXY + ".Client";
    public static final String SERVER = PROXY + ".Server";
    
    @SidedProxy(clientSide=CLIENT, serverSide=SERVER)
    public static Common proxy;
    
    @EventHandler
    public void pre(FMLPreInitializationEvent e) {
        proxy.pre(e);
        Util.regEvents(this);
    }
    
    @SubscribeEvent
    public void items(Register<Item> e) {
        //IForgeRegistry<Item> ifr = e.getRegistry();
    }
}
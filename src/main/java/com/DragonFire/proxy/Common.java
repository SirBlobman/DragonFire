package com.DragonFire.proxy;

import com.DragonFire.items.DFItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class Common {
    public void pre(FMLPreInitializationEvent e) {
        
    }
    
    public void init(FMLInitializationEvent e) {
        
    }
    
    public void post(FMLPostInitializationEvent e) {
        
    }
    
    public void items(IForgeRegistry<Item> ifr) {
        DFItems.register(ifr);
    }

    public void blocks(IForgeRegistry<Block> ifr) {
        
    }
}
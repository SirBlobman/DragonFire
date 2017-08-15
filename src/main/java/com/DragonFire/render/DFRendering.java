package com.DragonFire.render;

import com.DragonFire.item.DFItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public final class DFRendering {
    public static void items() {
        //Armor
        reg(DFItems.RABBIT_BOOTS);
        
        //Food
        reg(DFItems.FRIED_EGG, DFItems.RAW_CALAMARI, DFItems.COOKED_CALAMARI);
        
        //Other Items
        reg(DFItems.BAT_WINGS);
    }
    
    /*private static void reg(Block... bb) {
        Item[] ii = new Item[bb.length];
        for(int j = 0; j < bb.length; j++) {
            Block b = bb[j];
            Item i = Item.getItemFromBlock(b);
            ii[j] = i;
        }
        reg(ii);
    }*/
    
    private static void reg(Item... ii) {
        for(Item i : ii) {
            ResourceLocation rl = i.getRegistryName();
            ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
    }
}
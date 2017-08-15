package com.DragonFire.render;

import com.DragonFire.entity.EntityEnderArrow;
import com.DragonFire.item.DFItems;
import com.DragonFire.render.entity.RenderEnderArrow;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public final class DFRendering {
    public static void items() {
        //Armor
        reg(DFItems.RABBIT_BOOTS);
        
        //Tools and Weapons
        reg(DFItems.ENDER_BOW, DFItems.ENDER_ARROW);
        
        //Food
        reg(DFItems.FRIED_EGG, DFItems.RAW_CALAMARI, DFItems.COOKED_CALAMARI);
        
        //Other Items
        reg(DFItems.BAT_WINGS);
    }
    
    public static void entities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderArrow.class, new IRenderFactory<EntityEnderArrow>() {
            @Override
            public Render<EntityEnderArrow> createRenderFor(RenderManager rm) {
                RenderEnderArrow rea = new RenderEnderArrow(rm);
                return rea;
            }
        });
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
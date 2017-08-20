package com.DragonFire.render;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.entity.EntityEnderArrow;
import com.DragonFire.entity.EntityMummy;
import com.DragonFire.item.DFItems;
import com.DragonFire.render.entity.RenderEnderArrow;
import com.DragonFire.render.entity.RenderMummy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public final class DFRendering {
    public static void items() {
        //Armor
        reg(DFItems.RABBIT_BOOTS);
        
        //Tools and Weapons
        reg(DFItems.ENDER_BOW, DFItems.ENDER_ARROW);
        
        //Food
        reg(
            DFItems.RAW_CALAMARI, DFItems.RAW_BACON,
            DFItems.FRIED_EGG, DFItems.COOKED_CALAMARI, DFItems.COOKED_BACON, 
            DFItems.PINEAPPLE_SLICE, DFItems.POTION_COOKIE
        );
        
        //Mob Drops
        reg(DFItems.BAT_WING, DFItems.MUMMY_RAG);
    }
    
    public static void blocks() {
        reg(DFBlocks.MUMMY_HEAD_ITEM);
    }
    
    public static void entities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderArrow.class, RenderEnderArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, RenderMummy::new);
    }
    
    private static void reg(Item... ii) {
        for(Item i : ii) {
            ResourceLocation rl = i.getRegistryName();
            ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
    }
}
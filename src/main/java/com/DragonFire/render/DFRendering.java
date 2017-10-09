package com.DragonFire.render;

import com.DragonFire.block.DFBlocks;
import com.DragonFire.entity.EntityMummy;
import com.DragonFire.entity.projectile.EntityDynamite;
import com.DragonFire.entity.projectile.EntityEnderArrow;
import com.DragonFire.entity.projectile.EntityTikiSpear;
import com.DragonFire.item.DFItems;
import com.DragonFire.render.entity.RenderDynamite;
import com.DragonFire.render.entity.RenderEnderArrow;
import com.DragonFire.render.entity.RenderMummy;
import com.DragonFire.render.entity.RenderTikiSpear;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public final class DFRendering {
    public static void items() {
        //Armor
        reg(
            DFItems.RABBIT_BOOTS,
            DFItems.EMERALD_HELMET, DFItems.EMERALD_CHESTPLATE, DFItems.EMERALD_LEGGINGS, DFItems.EMERALD_BOOTS,
            DFItems.COPPER_HELMET, DFItems.COPPER_CHESTPLATE, DFItems.COPPER_LEGGINGS, DFItems.COPPER_BOOTS,
            DFItems.DYABLE_BACKPACK
        );
        
        //Tools and Weapons
        reg(
            DFItems.ENDER_BOW, DFItems.ENDER_ARROW,
            DFItems.NETHER_ROD, DFItems.VILLAGER_HOE,
            DFItems.EMERALD_SWORD, DFItems.EMERALD_AXE, DFItems.EMERALD_PICKAXE, DFItems.EMERALD_SHOVEL, DFItems.EMERALD_HOE,
            DFItems.COPPER_SWORD, DFItems.COPPER_AXE, DFItems.COPPER_PICKAXE, DFItems.COPPER_SHOVEL, DFItems.COPPER_HOE,
            DFItems.OCEANIC_SHOVEL, DFItems.DYNAMITE, DFItems.NUCLEAR_DYNAMITE
        );
        
        //Food
        reg(
            DFItems.POTION_COOKIE, DFItems.DIAMOND_APPLE,
            DFItems.RAW_CALAMARI, DFItems.RAW_BACON,
            DFItems.FRIED_EGG, DFItems.COOKED_CALAMARI, DFItems.COOKED_BACON, 
            DFItems.PINEAPPLE_SLICE, DFItems.CHOCOLATE_BAR, DFItems.CHEESE, DFItems.SLIME_JELLY, DFItems.SANDWICH
        );
        
        //Drinks
        reg(
            DFItems.BEER, DFItems.SPOILED_MILK
        );
        
        //Mob Drops
        reg(DFItems.BAT_WING, DFItems.MUMMY_RAG, DFItems.WITHERED_BONE);
        
        //Nuggets, Ingots, Gems, etc...
        reg(DFItems.COPPER_NUGGET, DFItems.COPPER_INGOT);
    }
    
    public static void blocks() {
        reg(
            DFBlocks.MUMMY_HEAD, 
            DFBlocks.CHOCOLATE_CAKE, DFBlocks.PIZZA, 
            DFBlocks.PLAYER_PLATE, DFBlocks.NUCLEAR_TNT,
            DFBlocks.COPPER_ORE, DFBlocks.COPPER_BLOCK, DFBlocks.NETHER_GOLD_ORE,
            DFBlocks.ENDER_PEARL_BLOCK, DFBlocks.OBSIDIAN_GLASS
        );
    }
    
    public static void entities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderArrow.class, RenderEnderArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTikiSpear.class, RenderTikiSpear::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, RenderMummy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, RenderDynamite::new);
    }
    
    private static void reg(Block... bb) {
        Item[] ii = new Item[bb.length];
        for(int i = 0; i < bb.length; i++) {
            Block b = bb[i];
            Item ib = Item.getItemFromBlock(b);
            ii[i] = ib;
        } reg(ii);
    }
    
    private static void reg(Item... ii) {
        for(Item i : ii) {
            ResourceLocation rl = i.getRegistryName();
            ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
    }
}
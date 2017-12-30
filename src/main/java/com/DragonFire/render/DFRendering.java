package com.DragonFire.render;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;
import com.DragonFire.block.item.LeavesItemBlock;
import com.DragonFire.block.tree.*;
import com.DragonFire.block.tree.slab.BlockDFWoodSlab;
import com.DragonFire.entity.custom.EntityCustomBoat;
import com.DragonFire.entity.living.EntityDraug;
import com.DragonFire.entity.living.EntityJungleSpider;
import com.DragonFire.entity.living.EntityMummy;
import com.DragonFire.entity.projectile.EntityDynamite;
import com.DragonFire.entity.projectile.EntityEnderArrow;
import com.DragonFire.entity.projectile.EntityExplosiveArrow;
import com.DragonFire.entity.projectile.EntityTikiSpear;
import com.DragonFire.item.DFItems;
import com.DragonFire.item.armor.backpack.DyableBackpack;
import com.DragonFire.item.drink.QuickDrink;
import com.DragonFire.item.tool.Dynamite;
import com.DragonFire.render.entity.*;
import com.DragonFire.utility.BlockUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
            DFItems.OCEANIC_SHOVEL, DFItems.DYNAMITE, DFItems.NUCLEAR_DYNAMITE, DFItems.EXPLOSIVE_ARROW,
            DFItems.MUSHROOM_PICKAXE, DFItems.GLASS_DAGGER, DFItems.PHILOSOPHERS_SWORD, DFItems.TREE_AXE
        );
        
        //Food
        reg(
            DFItems.POTION_COOKIE, DFItems.DIAMOND_APPLE,
            DFItems.RAW_CALAMARI, DFItems.RAW_BACON,
            DFItems.FRIED_EGG, DFItems.COOKED_CALAMARI, DFItems.COOKED_BACON, 
            DFItems.PINEAPPLE_SLICE, DFItems.CHOCOLATE_BAR, DFItems.CHEESE, DFItems.SLIME_JELLY, DFItems.SANDWICH, DFItems.SUGAR_COOKIE,
            DFItems.APPLE_PIE, DFItems.SEA_WEED, DFItems.DRIED_SEA_WEED, DFItems.CHERRY
        );
        
        //Drinks
        reg(DFItems.BEER, DFItems.SPOILED_MILK);
        
        //Mob Drops
        reg(DFItems.BAT_WING, DFItems.MUMMY_RAG, DFItems.WITHERED_BONE);
        
        //Nuggets, Ingots, Gems, etc...
        reg(DFItems.COPPER_NUGGET, DFItems.COPPER_INGOT);
        
        //Other
        reg(DFItems.RECORD_DOG, DFItems.CHERRY_BOAT, DFItems.PALM_BOAT, DFItems.COCONUT);
    }
    
    public static void special() {
        reg(DFItems.GLASS_FRAGMENT, 0, "glass_fragment/black");
        reg(DFItems.GLASS_FRAGMENT, 1, "glass_fragment/red");
        reg(DFItems.GLASS_FRAGMENT, 2, "glass_fragment/green");
        reg(DFItems.GLASS_FRAGMENT, 3, "glass_fragment/brown");
        reg(DFItems.GLASS_FRAGMENT, 4, "glass_fragment/blue");
        reg(DFItems.GLASS_FRAGMENT, 5, "glass_fragment/purple");
        reg(DFItems.GLASS_FRAGMENT, 6, "glass_fragment/cyan");
        reg(DFItems.GLASS_FRAGMENT, 7, "glass_fragment/silver");
        reg(DFItems.GLASS_FRAGMENT, 8, "glass_fragment/gray");
        reg(DFItems.GLASS_FRAGMENT, 9, "glass_fragment/pink");
        reg(DFItems.GLASS_FRAGMENT, 10, "glass_fragment/lime");
        reg(DFItems.GLASS_FRAGMENT, 11, "glass_fragment/yellow");
        reg(DFItems.GLASS_FRAGMENT, 12, "glass_fragment/light_blue");
        reg(DFItems.GLASS_FRAGMENT, 13, "glass_fragment/magenta");
        reg(DFItems.GLASS_FRAGMENT, 14, "glass_fragment/orange");
        reg(DFItems.GLASS_FRAGMENT, 15, "glass_fragment/white");
        reg(DFItems.GLASS_FRAGMENT, 16, "glass_fragment/obsidian");
        reg(DFItems.GLASS_FRAGMENT, 17, "glass_fragment/clear");

        reg(DFBlocks.LOG, 0, "block/tree/cherry_log");
        reg(DFBlocks.LEAVES, 0, "block/tree/cherry_leaves");
        reg(DFBlocks.SAPLING, 0, "block/tree/cherry_sapling");
        reg(DFBlocks.PLANKS, 0, "block/tree/cherry_planks");
        reg(DFBlocks.WOODEN_SLAB, 0, "block/tree/cherry_slab");
        
        reg(DFBlocks.CHERRY_STAIRS, 0, "block/tree/cherry_wooden_stairs");
        reg(DFBlocks.ITEM_CHERRY_DOOR, 0, "block/tree/cherry_wood_door");
        reg(DFBlocks.CHERRY_FENCE, 0, "block/tree/cherry_fence");
        reg(DFBlocks.CHERRY_FENCE_GATE, 0, "block/tree/cherry_fence_gate");
        
        reg(DFBlocks.LOG, 1, "block/tree/palm_log");
        reg(DFBlocks.LEAVES, 1, "block/tree/palm_leaves");
        reg(DFBlocks.SAPLING, 1, "block/tree/palm_sapling");
        reg(DFBlocks.PLANKS, 1, "block/tree/palm_planks");
        reg(DFBlocks.WOODEN_SLAB, 1, "block/tree/palm_slab");
        
        reg(DFBlocks.PALM_STAIRS, 0, "block/tree/palm_wooden_stairs");
        reg(DFBlocks.ITEM_PALM_DOOR, 0, "block/tree/palm_wood_door");
        reg(DFBlocks.PALM_FENCE, 0, "block/tree/palm_fence");
        reg(DFBlocks.PALM_FENCE_GATE, 0, "block/tree/palm_fence_gate");
    }
    
    public static void blocks() {
        reg(
            DFBlocks.MUMMY_HEAD, 
            DFBlocks.CHOCOLATE_CAKE, DFBlocks.PIZZA, 
            DFBlocks.PLAYER_PLATE, DFBlocks.NUCLEAR_TNT,
            DFBlocks.NETHER_BRICK_FURNACE,
            DFBlocks.COPPER_ORE, DFBlocks.COPPER_BLOCK, DFBlocks.NETHER_GOLD_ORE, DFBlocks.CHARCOAL_BLOCK,
            DFBlocks.ENDER_PEARL_BLOCK, DFBlocks.OBSIDIAN_GLASS, DFBlocks.RADIOACTIVE_MUSHROOM
        );
    }
    
    public static void entities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderArrow.class, RenderEnderArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTikiSpear.class, RenderTikiSpear::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, RenderMummy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, RenderDynamite::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveArrow.class, RenderExplosiveArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDraug.class, RenderDraug::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomBoat.class, RenderCustomBoat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityJungleSpider.class, RenderJungleSpider::new);
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
            String path = rl.getResourcePath();
            
            if(i instanceof ItemBlock) path = "block/" + path;
            else if(i instanceof ItemArmor) path = "armor/" + path;
            else if(isTool(i)) path = "tool/" + path;
            else if(isFood(i)) path = "food/" + path;
            
            reg(i, 0, path);
        }
    }
    
    private static void reg(Block b, int meta, String name) {
        Item ib = Item.getItemFromBlock(b);
        reg(ib, meta, name);
    }
    
    private static void reg(Item i, int meta, String name) {
        ResourceLocation rl = new ResourceLocation(DragonFire.MODID, name);
        ModelResourceLocation mrl = new ModelResourceLocation(rl, "inventory");
        ModelLoader.setCustomModelResourceLocation(i, meta, mrl);
    }
    
    private static boolean isTool(Item i) {
        if(i == null || i == Items.AIR) return false;
        else if(i instanceof ItemTool) return true;
        else if(i instanceof ItemHoe) return true;
        else if(i instanceof ItemSword) return true;
        else if(i instanceof ItemBow) return true;
        else if(i instanceof ItemFishingRod) return true;
        else if(i instanceof Dynamite) return true;
        else return false;
    }
    
    private static boolean isFood(Item i) {
        if(i == null || i == Items.AIR) return false;
        else if(i instanceof ItemFood) return true;
        else if(i instanceof QuickDrink) return true;
        else return false;
    }
    
    @SubscribeEvent
    public void customBlockStates(ModelRegistryEvent e) {
        ModelLoader.setCustomStateMapper(DFBlocks.LEAVES, BlockUtil.buildStateMap(BlockDFLeaves.TYPE, "_leaves", BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY));
        ModelLoader.setCustomStateMapper(DFBlocks.LOG, BlockUtil.buildStateMap(BlockDFLog.TYPE, "_log"));
        ModelLoader.setCustomStateMapper(DFBlocks.PLANKS, BlockUtil.buildStateMap(BlockDFPlanks.TYPE, "_planks"));
        ModelLoader.setCustomStateMapper(DFBlocks.SAPLING, BlockUtil.buildStateMap(BlockDFSapling.TYPE, "_sapling"));
        ModelLoader.setCustomStateMapper(DFBlocks.WOODEN_SLAB, BlockUtil.buildStateMap(BlockDFWoodSlab.TYPE, "_slab"));
        ModelLoader.setCustomStateMapper(DFBlocks.DOUBLE_WOODEN_SLAB, BlockUtil.buildStateMap(BlockDFWoodSlab.TYPE, "_double_slab"));
        
        ModelLoader.setCustomStateMapper(DFBlocks.CHERRY_FENCE_GATE, BlockUtil.buildIgnoreMap(BlockDFWoodFenceGate.POWERED));
        ModelLoader.setCustomStateMapper(DFBlocks.CHERRY_DOOR, BlockUtil.buildIgnoreMap(BlockDoor.POWERED));
        
        ModelLoader.setCustomStateMapper(DFBlocks.PALM_FENCE_GATE, BlockUtil.buildIgnoreMap(BlockDFWoodFenceGate.POWERED));
        ModelLoader.setCustomStateMapper(DFBlocks.PALM_DOOR, BlockUtil.buildIgnoreMap(BlockDoor.POWERED));
    }

    public static void customColors() {
        Minecraft mc = Minecraft.getMinecraft();
        ItemColors ic = mc.getItemColors();
        BlockColors bc = mc.getBlockColors();
        
        ic.registerItemColorHandler(new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack is, int tint) {
                if(tint == 1) {
                    int color = PotionUtils.getColor(is);
                    return color;
                } else return -1;
            }
        }, DFItems.POTION_COOKIE);
        
        ic.registerItemColorHandler(new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack is, int tint) {
                if(tint == 0) {
                    Item i = is.getItem();
                    if(i == DFItems.DYABLE_BACKPACK) {
                        DyableBackpack db = (DyableBackpack) i;
                        return db.getColor(is);
                    } else return -1;
                } else return -1;
            }
        }, DFItems.DYABLE_BACKPACK);
        
        ic.registerItemColorHandler(new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack is, int tint) {
                Item item = is.getItem();
                if(item == DFBlocks.ITEM_LEAVES) {
                    LeavesItemBlock leaves = (LeavesItemBlock) item;
                    return leaves.getColor(is);
                } else return -1;
            }
        }, DFBlocks.ITEM_LEAVES);
        
        bc.registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState ibs, IBlockAccess world, BlockPos bp, int tint) {
                int color = BiomeColorHelper.getFoliageColorAtPos(world, bp);
                return color;
            }
        }, DFBlocks.LEAVES);
    }
}
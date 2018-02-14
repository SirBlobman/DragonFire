package com.DragonFire.item;

import com.DragonFire.entity.custom.EntityCustomBoat.Type;
import com.DragonFire.item.armor.QuickArmor;
import com.DragonFire.item.armor.RabbitBoots;
import com.DragonFire.item.armor.backpack.DyableBackpack;
import com.DragonFire.item.custom.*;
import com.DragonFire.item.drink.Beer;
import com.DragonFire.item.drink.SpoiledMilk;
import com.DragonFire.item.food.*;
import com.DragonFire.item.tool.*;
import com.DragonFire.sound.DFSoundHandler;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemRecord;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFItems {
    //Armor Materials
    public static final ArmorMaterial ARMOR_RABBIT = EnumHelper.addArmorMaterial("RABBIT", "dragonfire:rabbit", 33, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0F);
    public static final ArmorMaterial ARMOR_EMERALD = EnumHelper.addArmorMaterial("EMERALD", "dragonfire:emerald", 33, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
    public static final ArmorMaterial ARMOR_COPPER = EnumHelper.addArmorMaterial("COPPER", "dragonfire:copper", 15, new int[] {1, 5, 6, 2}, 11, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    
    //Tool Material
    public static final ToolMaterial TOOL_EMERALD = EnumHelper.addToolMaterial("EMERALD", 3, 1561, 8.0F, 3.0F, 100);
    public static final ToolMaterial TOOL_COPPER = EnumHelper.addToolMaterial("COPPER", 2, 150, 5.0F, 1.5F, 9);
    public static final ToolMaterial TOOL_VILLAGER = EnumHelper.addToolMaterial("VILLAGER", 500, 64, Integer.MAX_VALUE, 0, 0);
    public static final ToolMaterial TOOL_GLASS = EnumHelper.addToolMaterial("GLASS", 0, 50, 1.0F, 0.0F, 1);
    public static final ToolMaterial TOOL_PHILOSOPHY = EnumHelper.addToolMaterial("PHILOSOPHY", 0, 3000, 12.0F, 0.0F, 22);
    
    //Armor
    public static final RabbitBoots RABBIT_BOOTS = new RabbitBoots();
    public static final QuickArmor EMERALD_HELMET = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.HEAD);
    public static final QuickArmor EMERALD_CHESTPLATE = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.CHEST);
    public static final QuickArmor EMERALD_LEGGINGS = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.LEGS);
    public static final QuickArmor EMERALD_BOOTS = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.FEET);
    public static final QuickArmor COPPER_HELMET = new QuickArmor(ARMOR_COPPER, EntityEquipmentSlot.HEAD);
    public static final QuickArmor COPPER_CHESTPLATE = new QuickArmor(ARMOR_COPPER, EntityEquipmentSlot.CHEST);
    public static final QuickArmor COPPER_LEGGINGS = new QuickArmor(ARMOR_COPPER, EntityEquipmentSlot.LEGS);
    public static final QuickArmor COPPER_BOOTS = new QuickArmor(ARMOR_COPPER, EntityEquipmentSlot.FEET);
    public static final DyableBackpack DYABLE_BACKPACK = new DyableBackpack();
    
    //Tools and Weapons
    public static final EnderBow ENDER_BOW = new EnderBow();
    public static final EnderArrow ENDER_ARROW = new EnderArrow();
    public static final NetherRod NETHER_ROD = new NetherRod();
    public static final QuickSword EMERALD_SWORD = new QuickSword(TOOL_EMERALD);
    public static final QuickAxe EMERALD_AXE = new QuickAxe(TOOL_EMERALD);
    public static final QuickPickaxe EMERALD_PICKAXE = new QuickPickaxe(TOOL_EMERALD);
    public static final QuickShovel EMERALD_SHOVEL = new QuickShovel(TOOL_EMERALD);
    public static final QuickHoe EMERALD_HOE = new QuickHoe(TOOL_EMERALD);
    public static final QuickHoe VILLAGER_HOE = new QuickHoe(TOOL_VILLAGER);
    public static final QuickSword COPPER_SWORD = new QuickSword(TOOL_COPPER);
    public static final QuickAxe COPPER_AXE = new QuickAxe(TOOL_COPPER);
    public static final QuickPickaxe COPPER_PICKAXE = new QuickPickaxe(TOOL_COPPER);
    public static final QuickShovel COPPER_SHOVEL = new QuickShovel(TOOL_COPPER);
    public static final QuickHoe COPPER_HOE = new QuickHoe(TOOL_COPPER);
    public static final OceanicShovel OCEANIC_SHOVEL = new OceanicShovel();
    public static final Dynamite DYNAMITE = new Dynamite(false);
    public static final Dynamite NUCLEAR_DYNAMITE = new Dynamite(true);
    public static final ExplosiveArrow EXPLOSIVE_ARROW = new ExplosiveArrow();
    public static final MushroomPickaxe MUSHROOM_PICKAXE = new MushroomPickaxe();
    public static final QuickSword GLASS_DAGGER = new QuickSword(TOOL_GLASS, "glass_dagger");
    public static final QuickSword PHILOSOPHERS_SWORD = new QuickSword(TOOL_PHILOSOPHY, "philosophers_sword");
    public static final QuickAxe TREE_AXE = new QuickAxe(ToolMaterial.DIAMOND, "tree_axe");
    
    //Food
    public static final QuickFood POTION_COOKIE = new PotionCookie();
    public static final DiamondApple DIAMOND_APPLE = new DiamondApple();
    public static final QuickFood RAW_CALAMARI = new QuickFood("raw_calamari", 1, 0.4F, true);
    public static final QuickFood COOKED_CALAMARI = new QuickFood("cooked_calamari", 5, 6.0F, false);
    public static final QuickFood RAW_BACON = new QuickFood("raw_bacon", 1, 1.6F, true);
    public static final QuickFood COOKED_BACON = new QuickFood("cooked_bacon", 2, 3.2F, true);
    public static final QuickFood FRIED_EGG = new QuickFood("fried_egg", 5, 6.0F, true);
    public static final QuickFood PINEAPPLE_SLICE = new QuickFood("pineapple_slice", 4, 2.4F, false);
    public static final QuickFood CHOCOLATE_BAR = new QuickFood("chocolate_bar", 2, 0.4F, false); //Why would you feed a dog chocolate?
    public static final QuickFood CHEESE = new QuickFood("cheese", 6, 0.0F, false);
    public static final QuickFood SLIME_JELLY = new QuickFood("slime_jelly", 3, 1.5F, false);
    public static final QuickFood SANDWICH = new QuickFood("sandwich", 9, 4.5F, true);
    public static final SugarCookie SUGAR_COOKIE = new SugarCookie();
    public static final QuickFood APPLE_PIE = new QuickFood("apple_pie", 9, 4.8F, false);
    public static final QuickFood SEA_WEED = new QuickFood("sea_weed", 1, 0.1F, false);
    public static final QuickFood DRIED_SEA_WEED = new QuickFood("dried_sea_weed", 2, 0.2F, false);
    public static final QuickFood CHERRY = new QuickFood("cherry", 1, 0.1F, false);
    public static final QuickFood MOSS = new ItemMoss();
    
    //Drink
    public static final Beer BEER = new Beer();
    public static final SpoiledMilk SPOILED_MILK = new SpoiledMilk();
    
    //Mob Drops
    public static final Item BAT_WING = new QuickItem("bat_wing");
    public static final Item MUMMY_RAG = new QuickItem("mummy_rag");
    public static final Item WITHERED_BONE = new QuickItem("withered_bone");
    
    //Nuggets, Ingots and Gems
    public static final Item COPPER_INGOT = new QuickItem("copper_ingot");
    public static final Item COPPER_NUGGET = new QuickItem("copper_nugget");
    
    //Other Stuff
    public static final GlassFragment GLASS_FRAGMENT = new GlassFragment();
    public static final ItemRecord RECORD_DOG = new QuickRecord("dog", DFSoundHandler.RECORD_DOG);
    public static final ItemCustomBoat CHERRY_BOAT = new ItemCustomBoat(Type.CHERRY);
    public static final ItemCustomBoat PALM_BOAT = new ItemCustomBoat(Type.PALM);
    public static final Item COCONUT = new ItemCoconut();
    
    public static final void register(IForgeRegistry<Item> ifr) {
        //Armor
        ifr.registerAll(
            RABBIT_BOOTS,
            EMERALD_HELMET, EMERALD_CHESTPLATE, EMERALD_LEGGINGS, EMERALD_BOOTS,
            COPPER_HELMET, COPPER_CHESTPLATE, COPPER_LEGGINGS, COPPER_BOOTS,
            DYABLE_BACKPACK
        );
        
        //Tools and Weapons
        ifr.registerAll(
            ENDER_BOW, ENDER_ARROW,
            NETHER_ROD, VILLAGER_HOE,
            EMERALD_SWORD, EMERALD_AXE, EMERALD_PICKAXE, EMERALD_SHOVEL, EMERALD_HOE,
            COPPER_SWORD, COPPER_AXE, COPPER_PICKAXE, COPPER_SHOVEL, COPPER_HOE,
            OCEANIC_SHOVEL, DYNAMITE, NUCLEAR_DYNAMITE, EXPLOSIVE_ARROW,
            MUSHROOM_PICKAXE, GLASS_DAGGER, PHILOSOPHERS_SWORD, TREE_AXE
        );
        
        //Food
        ifr.registerAll(
            POTION_COOKIE, DIAMOND_APPLE,
            RAW_CALAMARI, RAW_BACON,
            COOKED_CALAMARI, COOKED_BACON, FRIED_EGG, 
            PINEAPPLE_SLICE, CHOCOLATE_BAR, CHEESE, SLIME_JELLY, SANDWICH, SUGAR_COOKIE,
            APPLE_PIE, SEA_WEED, DRIED_SEA_WEED, CHERRY, MOSS
        );
        
        //Drinks
        ifr.registerAll(BEER, SPOILED_MILK);
        
        //Mob Drops
        ifr.registerAll(BAT_WING, MUMMY_RAG, DFItems.WITHERED_BONE);
        
        //Nuggets, Ingots, Gems, etc...
        ifr.registerAll(COPPER_INGOT, COPPER_NUGGET);
        
        //Other Stuff
        ifr.registerAll(
            GLASS_FRAGMENT, RECORD_DOG, COCONUT,
            CHERRY_BOAT, PALM_BOAT
        );
    }
}
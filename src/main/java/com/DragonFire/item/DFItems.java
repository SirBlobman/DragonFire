package com.DragonFire.item;

import com.DragonFire.item.armor.QuickArmor;
import com.DragonFire.item.armor.RabbitBoots;
import com.DragonFire.item.tool.*;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFItems {
    //Armor Materials
    public static final ArmorMaterial ARMOR_RABBIT = EnumHelper.addArmorMaterial("RABBIT", "dragonfire:rabbit", 33, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0F);
    public static final ArmorMaterial ARMOR_EMERALD = EnumHelper.addArmorMaterial("EMERALD", "dragonfire:emerald", 33, new int[] {3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2);
    
    //Tool Material
    public static final ToolMaterial TOOL_EMERALD = EnumHelper.addToolMaterial("EMERALD", 3, 1561, 8.0F, 3.0F, 100);
    
    //Armor
    public static final RabbitBoots RABBIT_BOOTS = new RabbitBoots();
    public static final QuickArmor EMERALD_HELMET = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.HEAD);
    public static final QuickArmor EMERALD_CHESTPLATE = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.CHEST);
    public static final QuickArmor EMERALD_LEGGINGS = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.LEGS);
    public static final QuickArmor EMERALD_BOOTS = new QuickArmor(ARMOR_EMERALD, EntityEquipmentSlot.FEET);
    
    //Tools and Weapons
    public static final EnderBow ENDER_BOW = new EnderBow();
    public static final EnderArrow ENDER_ARROW = new EnderArrow();
    public static final NetherRod NETHER_ROD = new NetherRod();
    public static final QuickSword EMERALD_SWORD = new QuickSword(TOOL_EMERALD);
    public static final QuickAxe EMERALD_AXE = new QuickAxe(TOOL_EMERALD);
    public static final QuickPickaxe EMERALD_PICKAXE = new QuickPickaxe(TOOL_EMERALD);
    public static final QuickShovel EMERALD_SHOVEL = new QuickShovel(TOOL_EMERALD);
    public static final QuickHoe EMERALD_HOE = new QuickHoe(TOOL_EMERALD);
    
    //Food
    public static final FoodItem RAW_CALAMARI = new FoodItem("raw_calamari", 1, 0.4F, true);
    public static final FoodItem COOKED_CALAMARI = new FoodItem("cooked_calamari", 5, 6.0F, false);
    public static final FoodItem RAW_BACON = new FoodItem("raw_bacon", 1, 1.6F, true);
    public static final FoodItem COOKED_BACON = new FoodItem("cooked_bacon", 2, 3.2F, true);
    public static final FoodItem FRIED_EGG = new FoodItem("fried_egg", 5, 6.0F, true);
    public static final FoodItem PINEAPPLE_SLICE = new FoodItem("pineapple_slice", 4, 2.4F, false);
    public static final FoodItem CHOCOLATE_BAR = new FoodItem("chocolate_bar", 2, 0.4F, false); //Why would you feed a dog chocolate?
    public static final FoodItem CHEESE = new FoodItem("cheese", 6, 0.0F, false);
    public static final FoodItem POTION_COOKIE = new PotionCookie();
    
    //Mob Drops
    public static final Item BAT_WING = new QuickItem("bat_wing");
    public static final Item MUMMY_RAG = new QuickItem("mummy_rag");
    
    public static final void register(IForgeRegistry<Item> ifr) {
        //Armor
        ifr.registerAll(
            RABBIT_BOOTS,
            EMERALD_HELMET, EMERALD_CHESTPLATE, EMERALD_LEGGINGS, EMERALD_BOOTS
        );
        
        //Tools and Weapons
        ifr.registerAll(
            ENDER_BOW, ENDER_ARROW,
            NETHER_ROD,
            EMERALD_SWORD, EMERALD_AXE, EMERALD_PICKAXE, EMERALD_SHOVEL, EMERALD_HOE
        );
        
        //Food
        ifr.registerAll(
            RAW_CALAMARI, RAW_BACON,
            COOKED_CALAMARI, COOKED_BACON, FRIED_EGG, 
            PINEAPPLE_SLICE, CHOCOLATE_BAR, CHEESE,
            POTION_COOKIE
       );
        
        //Mob Drops
        ifr.registerAll(BAT_WING, MUMMY_RAG);
    }
}
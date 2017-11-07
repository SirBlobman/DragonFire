package com.DragonFire.enchantment;

import com.google.common.base.Predicate;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentPickup extends Enchantment {
    public static final EnumEnchantmentType ANY_TOOL = EnumHelper.addEnchantmentType("ANY_TOOL", new Predicate<Item>() {
        @Override
        public boolean apply(Item item) {
            boolean tool = (item instanceof ItemTool);
            boolean sword = (item instanceof ItemSword);
            boolean fishing = (item instanceof ItemFishingRod);
            boolean shears = (item instanceof ItemShears);
            boolean hoe = (item instanceof ItemHoe);
            return (tool || sword || fishing || shears || hoe);
        }
    });
    public EnchantmentPickup() {
        super(Rarity.UNCOMMON, ANY_TOOL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setRegistryName("pickup");
        setName("pickup");
    }
    
    @Override
    public int getMinEnchantability(int level) {return 15;}
    public int getMaxEnchantability(int level) {return 65;}
}
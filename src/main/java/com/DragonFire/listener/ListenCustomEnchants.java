package com.DragonFire.listener;

import com.DragonFire.enchantment.DFEnchants;
import com.DragonFire.utility.Util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomEnchants {
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void breakBlock(HarvestDropsEvent e) {
        EntityPlayer ep = e.getHarvester();
        if(ep != null) {
            ItemStack is = ep.getHeldItemMainhand();
            if(is != null) {
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(is);
                Set<Enchantment> enchants = map.keySet();
                boolean smelting = enchants.contains(DFEnchants.AUTO_SMELT);
                if(smelting) {
                    List<ItemStack> drops = e.getDrops();
                    List<ItemStack> copyDrops = Util.newList(drops);
                    for(ItemStack drop : copyDrops) {
                        FurnaceRecipes fr = FurnaceRecipes.instance();
                        ItemStack smelt = fr.getSmeltingResult(drop);
                        if(smelt != null && !smelt.isEmpty()) {
                            drops.remove(drop);
                            drops.add(smelt);
                        }
                    }
                }
            }
        }
    }
}
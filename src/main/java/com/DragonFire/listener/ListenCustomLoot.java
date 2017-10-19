package com.DragonFire.listener;

import com.DragonFire.DragonFire;
import com.DragonFire.item.DFItems;
import com.DragonFire.utility.ItemUtil;
import com.DragonFire.utility.Util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomLoot {
    @SubscribeEvent
    public void loot(LootTableLoadEvent e) {
        ResourceLocation loot = e.getName();
        String name = loot.toString();
        LootTable lt = e.getTable();
        if(name.equals("minecraft:chests/end_city_treasure")) {
            ResourceLocation end_bow = new ResourceLocation(DragonFire.MODID, "chests/end_city");
            addPool(lt, "end_bow", end_bow);
        } else if(name.equals("minecraft:chests/nether_bridge")) {
            ResourceLocation nether_rod = new ResourceLocation(DragonFire.MODID, "chests/nether_bridge");
            addPool(lt, "nether_rod", nether_rod);
        } else if(name.equals("minecraft:chests/desert_pyramid")) {
            ResourceLocation pyramid = new ResourceLocation(DragonFire.MODID, "chests/desert_pyramid");
            addPool(lt, "mummy_rags", pyramid);
        }
    }
    
    @SubscribeEvent
    public void loot(HarvestDropsEvent e) {
        EntityPlayer ep = e.getHarvester();
        if(ep != null) {
            ItemStack hand = ep.getHeldItemMainhand();
            if(!ItemUtil.isAir(hand)) {
                Item i = hand.getItem();
                if(i == DFItems.VILLAGER_HOE) {
                    double chance = (Math.random() * 100);
                    if(chance <= 15) {
                        List<ItemStack> drops = e.getDrops();
                        List<ItemStack> copyDrops = Util.newList(drops);
                        drops.clear();
                        for(ItemStack drop : copyDrops) {
                            drop.setCount(drop.getCount() * 4);
                            drops.add(drop);
                        }
                    }
                }
            }
        }
    }
    
    private void addPool(LootTable lt, String name, ResourceLocation lootPool) {
        LootEntry le = new LootEntryTable(lootPool, 1, 0, new LootCondition[0], name + "_entry");
        LootPool lp = new LootPool(new LootEntry[] {le}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), name + "_pool");
        lt.addPool(lp);
    }
}
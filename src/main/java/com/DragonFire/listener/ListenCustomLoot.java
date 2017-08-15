package com.DragonFire.listener;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenCustomLoot {
    @SubscribeEvent
    public void loot(LootTableLoadEvent e) {
        ResourceLocation loot = e.getName();
        String name = loot.toString();
        if(name.equals("minecraft:chests/end_city_treasure")) {
            LootTable lt = e.getTable();
            ResourceLocation end_bow = new ResourceLocation("dragonfire", "end_city");
            LootEntry le = new LootEntryTable(end_bow, 1, 0, new LootCondition[0], "end_bow_entry");
            LootPool lp = new LootPool(new LootEntry[] {le}, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "end_bow_pool");
            lt.addPool(lp);
        }
    }
}
package com.DragonFire.item.custom;

import com.DragonFire.creative.DFTabs;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class QuickRecord extends ItemRecord {
    public QuickRecord(String name, SoundEvent sound) {
        super("dragonfire." + name, sound);
        setRegistryName("record_" + name);
        setUnlocalizedName("record");
        setCreativeTab(DFTabs.DRAGONFIRE);
    }
}
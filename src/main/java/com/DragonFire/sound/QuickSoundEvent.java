package com.DragonFire.sound;

import com.DragonFire.DragonFire;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class QuickSoundEvent extends SoundEvent {
    public QuickSoundEvent(String name) {
        super(new ResourceLocation(DragonFire.MODID, name));
        setRegistryName(name);
    }
}
package com.DragonFire.sound;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFSoundHandler {
    public static final SoundEvent RECORD_DOG = new QuickSoundEvent("record_dog");
    
    public static void register(IForgeRegistry<SoundEvent> ifr) {
        ifr.registerAll(RECORD_DOG);
    }
}
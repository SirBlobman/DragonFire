package com.DragonFire.entity;

import com.DragonFire.DragonFire;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class DFEntities {
    public static void entities() {
        EntityRegistry.registerModEntity(new ResourceLocation("dragonfire", "ender_arrow"), EntityEnderArrow.class, "ender_arrow", 1, DragonFire.INSTANCE, 64, 1, false);
    }
}
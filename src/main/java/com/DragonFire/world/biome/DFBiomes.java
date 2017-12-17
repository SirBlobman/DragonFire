package com.DragonFire.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.registries.IForgeRegistry;

public final class DFBiomes {
    public static final Biome CHERRY_BLOSSOM = new BiomeCherryBlossom(new BiomeProperties("Cherry Blossom").setTemperature(0.6F).setRainfall(0.6F));
    
    public static void register(IForgeRegistry<Biome> ifr) {
        ifr.registerAll(CHERRY_BLOSSOM);
        
        BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(CHERRY_BLOSSOM, 10));
    }
}
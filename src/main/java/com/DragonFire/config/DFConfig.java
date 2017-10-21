package com.DragonFire.config;

import com.DragonFire.DragonFire;
import com.DragonFire.utility.Util;

import java.io.File;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

public class DFConfig {
    private static final File FOLDER = DragonFire.FOLDER;
    private static final File FILE = new File(FOLDER, "DragonFire.cfg");
    private static Configuration config = new Configuration(FILE, false);
    
    public static List<String> HUMANOIDS = Util.newList();
    public static void load() {
        config.load();
        HUMANOIDS = Util.newList(config.getStringList("humanoids", "enchantment.bane of humanoids", defaultHumanoids(), "Which entities do you want to define as 'human'?"));
        config.save();
    }
    
    private static String[] defaultHumanoids() {
        String[] ss = new String[] {
            "minecraft:zombie", "minecraft:husk", "minecraft:zombie_villager",
            "minecraft:skeleton", "minecraft:stray", "minecraft:wither_skeleton",
            "minecraft:villager", "minecraft:zombie_pigman",
            "minecraft:vindication_illager", "minecraft:evocation_illager", "minecraft:illusion_illager",
            "minecraft:witch", "minecraft:enderman",
            "dragonfire:mummy"
        }; return ss;
    }
}
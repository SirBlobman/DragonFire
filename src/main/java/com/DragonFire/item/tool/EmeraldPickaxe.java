package com.DragonFire.item.tool;

import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

public class EmeraldPickaxe extends ItemPickaxe {
    public static final ToolMaterial EMERALD = EnumHelper.addToolMaterial("EMERALD", 3, 1561, 8.0F, 3.0F, 100);
    public EmeraldPickaxe() {
        super(EMERALD);
        setRegistryName("emerald_pickaxe");
        setUnlocalizedName("emerald_pickaxe");
    }
}
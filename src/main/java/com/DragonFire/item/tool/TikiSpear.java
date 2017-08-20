package com.DragonFire.item.tool;

import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class TikiSpear extends ItemSword {
    public static final ToolMaterial TIKI_SPEAR = EnumHelper.addToolMaterial("TIKI_SPEAR", 0, 250, 0, 3, 30);
    public TikiSpear() {
        super(TIKI_SPEAR);
    }
}
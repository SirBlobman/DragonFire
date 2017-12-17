package com.DragonFire.block.item;

import com.DragonFire.block.tree.DFWoodType;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class TreeItemBlock extends QuickItemBlock {
    private final String nameBase;
    public TreeItemBlock(Block block, String nameBase) {
        super(block);
        this.nameBase = nameBase;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack is) {
        int meta = is.getMetadata();
        DFWoodType type = DFWoodType.byMetadata(meta);
        String wood = type.getName();
        String name = I18n.format("item." + wood + "_" + nameBase + ".name");
        return name;
    }
}
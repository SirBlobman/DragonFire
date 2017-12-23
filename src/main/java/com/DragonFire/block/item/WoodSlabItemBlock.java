package com.DragonFire.block.item;

import com.DragonFire.block.tree.DFWoodType;
import com.DragonFire.creative.DFTabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class WoodSlabItemBlock extends ItemSlab {
    private final String nameBase;
    public WoodSlabItemBlock(BlockSlab halfSlab, BlockSlab doubleSlab) {
        super(halfSlab, halfSlab, doubleSlab);
        this.nameBase = "wooden_slab";
        setRegistryName(halfSlab.getRegistryName());
        setCreativeTab(DFTabs.DRAGONFIRE);
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
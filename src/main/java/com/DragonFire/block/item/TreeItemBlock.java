package com.DragonFire.block.item;

import com.DragonFire.block.plant.DFWoodType;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class TreeItemBlock extends ItemMultiTexture {
    public TreeItemBlock(Block block, String nameBase) {
        super(block, block, new Mapper() {
            @Override
            public String apply(ItemStack is) {
                int meta = is.getMetadata();
                DFWoodType type = DFWoodType.byMetadata(meta);
                String name = type.getName();
                return name;           
            }
        });
        setRegistryName(block.getRegistryName());
        setUnlocalizedName(nameBase);
    }
}
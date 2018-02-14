package com.DragonFire.block.item;

import com.DragonFire.block.plant.BlockDFLeaves;
import com.DragonFire.utility.Util;
import com.DragonFire.world.biome.BiomeCherryBlossom;

import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class LeavesItemBlock extends ItemLeaves {
    private final BlockDFLeaves leaves;
    public LeavesItemBlock(BlockDFLeaves leaves) {
        super(leaves);
        this.leaves = leaves;
        setRegistryName(leaves.getRegistryName());
        setUnlocalizedName(leaves.getUnlocalizedName().substring(5));
    }
    
    @Override
    public String getUnlocalizedName(ItemStack is) {
        String oldName = "tile.leaves";
        int meta = is.getMetadata();
        String newName = oldName + "." + leaves.getDragonFireWoodType(meta).getName();
        return newName;
    }
    
    public int getColor(ItemStack is) {
        int meta = is.getMetadata();
        switch(meta) {
            case 0: return BiomeCherryBlossom.LEAVES_COLOR;
            default: return Util.getRGB(0, 128, 0);
        }
    }
}
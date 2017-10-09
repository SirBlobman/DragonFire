package com.DragonFire.world;

import com.DragonFire.block.DFBlocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class DragonFireWorldGen implements IWorldGenerator {
    @Override
    public void generate(Random rand, int cx, int cz, World world, IChunkGenerator icg, IChunkProvider icp) {
        WorldProvider wp = world.provider;
        int dimension = wp.getDimension();
        if(dimension == 0) generateOres(rand, cx, cz, world, icg, icp);
        else if(dimension == -1) generateNetherOres(rand, cx, cz, world, icg, icp);
    }
    
    private void generateOres(Random rand, int cx, int cz, World world, IChunkGenerator icg, IChunkProvider icp) {
        generateOre(DFBlocks.COPPER_ORE, world, rand, cx * 16, cz * 16, 16, 64, 4 + rand.nextInt(4), 6, Blocks.STONE);
    }
    
    private void generateNetherOres(Random rand, int cx, int cz, World world, IChunkGenerator icg, IChunkProvider icp) {
        generateOre(DFBlocks.NETHER_GOLD_ORE, world, rand, cx * 16, cz * 16, 16, 64, 4 + rand.nextInt(4), 6, Blocks.NETHERRACK);
    }
    
    private void generateOre(Block b, World world, Random rand, int x, int z, int miny, int maxy, int size, int chances, Block replace) {
        int deltaY = (maxy - miny);
        IBlockState ibs = b.getDefaultState();
        for(int i = 0; i < chances; i++) {
            BlockPos bp = new BlockPos(x + rand.nextInt(16), miny + rand.nextInt(deltaY), z + rand.nextInt(16));
            WorldGenMinable gen = new WorldGenMinable(ibs, size, BlockMatcher.forBlock(replace));
            gen.generate(world, rand, bp);
        }
    }
}
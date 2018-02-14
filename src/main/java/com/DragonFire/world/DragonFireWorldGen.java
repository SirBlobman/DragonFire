package com.DragonFire.world;

import com.DragonFire.DragonFire;
import com.DragonFire.block.DFBlocks;
import com.DragonFire.utility.Util;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class DragonFireWorldGen implements IWorldGenerator {
    @Override
    public void generate(Random rand, int cx, int cz, World world, IChunkGenerator icg, IChunkProvider icp) {
        WorldProvider wp = world.provider;
        int dimension = wp.getDimension();
        if(dimension == 0) generateOverworldOres(rand, cx, cz, world, icg, icp);
        else if(dimension == -1) generateNetherOres(rand, cx, cz, world, icg, icp);
    }

    private void generateOverworldOres(Random rand, int cx, int cz, World world, IChunkGenerator icg, IChunkProvider icp) {
        generateOre(DFBlocks.COPPER_ORE, world, rand, cx * 16, cz * 16, 16, 64, 4 + rand.nextInt(4), 23, Blocks.STONE);
        generateSunkenTreasure(world, rand, cx * 16, cz * 16, 30, 50, 1);
    }

    private void generateNetherOres(Random rand, int cx, int cz, World world, IChunkGenerator icg, IChunkProvider icp) {
        generateOre(DFBlocks.NETHER_GOLD_ORE, world, rand, cx * 16, cz * 16, 16, 64, 4 + rand.nextInt(4), 23, Blocks.NETHERRACK);
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
    
    private void generateSunkenTreasure(World world, Random rand, int x, int z, int miny, int maxy, int chances) {
        int deltaY = (maxy - miny);
        for(int i = 0; i < chances; i++) {
            int randX = (x + rand.nextInt(16));
            int randY = (miny + rand.nextInt(deltaY));
            int randZ = (z + rand.nextInt(16));
            
            BlockPos bp = new BlockPos(randX, randY, randZ);
            Biome biome = world.getBiome(bp);
            List<Biome> oceans = Util.newList(Util.getBiomes("ocean"));
            if(oceans.contains(biome) && world.getBlockState(bp).getBlock()== Blocks.GRAVEL) {
                WorldServer ws = (WorldServer) world;
                MinecraftServer ms = world.getMinecraftServer();
                TemplateManager tm = ws.getStructureTemplateManager();
                ResourceLocation treasure = new ResourceLocation(DragonFire.MODID, "sunken_treasure");
                Template template = tm.getTemplate(ms, treasure);
                if(template != null) {
                    IBlockState ibs = world.getBlockState(bp);
                    world.notifyBlockUpdate(bp, ibs, ibs, 3);
                    PlacementSettings ps = new PlacementSettings().setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk(null).setReplacedBlock(null).setIgnoreStructureBlock(false);
                    template.addBlocksToWorldChunk(world, bp, ps);
                }
            }
        }
    }
}
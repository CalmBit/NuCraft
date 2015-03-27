package calmbit.nucraft.core;

import java.util.Random;

import calmbit.nucraft.world.WorldGenCherryTrees;
import calmbit.nucraft.world.WorldGenElmTrees;
import calmbit.nucraft.world.WorldGenHellTrees;
import calmbit.nucraft.world.WorldGenRubberTrees;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.IWorldGenerator;

public class NuCraftWorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) 
	{
		switch(world.provider.dimensionId)
		{
		case 0:
			generateOverworld(random, chunkX, chunkZ, world);
			break;
		/*case 1:
			break;*/	
		case 2:
			generateRift(random, chunkX, chunkZ, world);
			break;
		case -1:
			generateNether(random, chunkX, chunkZ, world);
			break;
		}
		
	}
	
	private void generateOverworld(Random random, int x, int z, World world) 
	{
		
		addOreSpawn(NuCraft.copperOre, world, random, x*16, z*16, 0, 8, 20, 0, 64, Blocks.stone);
		addOreSpawn(NuCraft.leadOre, world, random, x*16, z*16, 0, 6, 20, 0, 50, Blocks.stone);
		//addOreSpawn(NuCraft.heavyCrudeOilBlock, world, random, x*16, z*16, 10, 20, 1, 10, 40);
		addTreeSpawn(world, random, x*16, z*16, 2, 1);
		addTreeSpawn(world, random, x*16, z*16, 2, 3);
		addTreeSpawn(world, random, x*16, z*16, 5, 4);
		//System.err.println(world.getWorldChunkManager().getBiomeGenAt(x*16, z*16).toString());
			
				
	}
	
	private void generateNether(Random random, int x, int z, World world)
	{
		addTreeSpawn(world, random, x*16, z*16, 5, 2);
	}
	
	private void generateRift(Random random, int x, int z, World world)
	{
		addOreSpawn(NuCraft.amethiteOre, world, random, x*16, z*16, 0, 4, 20, 0, 40, NuCraft.riftStone);
		addOreSpawn(NuCraft.leptrusOre, world, random, x*16, z*16, 0, 10, 20, 0, 60, NuCraft.riftStone);
	}
	
    /**
    *
    * This method adds our block to the world.
    * It randomizes the coordinates, and does that as many times, as defined in spawnChance.
    * Then it gives all the params to WorldGenMinable, which handles the replacing of the ores for us.
    *
    * @param block The block you want to spawn
    * @param world The world
    * @param random The Random
    * @param blockXPos the blockXpos of a chunk
    * @param blockZPos the blockZpos of a chunk
    * @param minVeinSize min vein
    * @param maxVeinSize max vein
    * @param chancesToSpawn the chance to spawn. Usually around 2
    * @param minY lowest point to spawn
    * @param maxY highest point to spawn
    */

   public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY, Block replacementBlock)
   {
       WorldGenMinable minable = new WorldGenMinable(block, maxVeinSize, replacementBlock);
       for(int i = 0; i < chancesToSpawn; i++)
       {
           int posX = blockXPos + random.nextInt(16);
           int posY = minY + random.nextInt(maxY - minY);
           int posZ = blockZPos + random.nextInt(16);
           minable.generate(world, random, posX, posY, posZ);
       }
   }
   
   public void addTreeSpawn(World world, Random random, int blockXPos, int blockZPos, int chancesToSpawn, int type)
   {
	   switch(type)
	   {
	   		case 1:
	   			WorldGenElmTrees genElm = new WorldGenElmTrees();
	   			for(int i = 0;i < chancesToSpawn; i++)
	   			{
	   				int X = blockXPos + random.nextInt(16);
	   				int Z = blockZPos + random.nextInt(16);
	   				int Y = world.getHeightValue(X, Z);
	   				if(BiomeDictionary.isBiomeOfType(world.getWorldChunkManager().getBiomeGenAt(X, Z), Type.PLAINS))genElm.generate(world, random, X, Y, Z);	
	   			}
	   			break;
	   		case 3:
	   			WorldGenCherryTrees genCherry = new WorldGenCherryTrees();
	   			for(int i = 0;i < chancesToSpawn; i++)
	   			{
	   				int X = blockXPos + random.nextInt(16);
	   				int Z = blockZPos + random.nextInt(16);
	   				int Y = world.getHeightValue(X, Z);
	   				if(BiomeDictionary.isBiomeOfType(world.getWorldChunkManager().getBiomeGenAt(X, Z), Type.PLAINS))genCherry.generate(world, random, X, Y, Z);	
	   			}
	   			break;
	  		case 4:
	   			WorldGenRubberTrees genRubber = new WorldGenRubberTrees();
	   			for(int i = 0;i < chancesToSpawn; i++)
	   			{
	   				int X = blockXPos + random.nextInt(16);
	   				int Z = blockZPos + random.nextInt(16);
	   				int Y = world.getHeightValue(X, Z);
	   				if(BiomeDictionary.isBiomeOfType(world.getWorldChunkManager().getBiomeGenAt(X, Z), Type.SWAMP))genRubber.generate(world, random, X, Y, Z);	
	   			}
	   			break;
	   		case 2:
	   			int X;
	   			int Z;
	   			int Y;
	   			WorldGenHellTrees genHell = new WorldGenHellTrees();
	   			for(int y = 1; y < 128;y++)
	   			{
	   				X = blockXPos + random.nextInt(16);
	   				Z = blockZPos + random.nextInt(16);
	   				if(world.getBlock(X, y-1, Z).isAssociatedBlock(Blocks.netherrack) && world.getBlock(X, y, Z).isAssociatedBlock(Blocks.air))
	   				{
	   					boolean blockValid = true;
	   					for(int ya = y + 1;ya < y + WorldGenHellTrees.MIN_TREE_HEIGHT + WorldGenHellTrees.TREE_HEIGHT_DEV && blockValid && ya < 128;ya++)
	   					{
	   						if(!world.getBlock(X, ya, Z).isAssociatedBlock(Blocks.air)) blockValid = false;
	   					}
	   					if(blockValid)
	   					{
	   						genHell.generate(world, random, X, y, Z);
	   						y=256;
	   					}
	   				}
	   			}
	   }
   }
	
	

}

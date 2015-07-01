package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenPurisSpikes extends WorldGenerator
{
	/**
	 * Mininum tree height...
	 */
	private final static int MIN_SPIKE_HEIGHT = 5;
	private final static int SPIKE_HEIGHT_DEV = 1;
	private final static int SPIKE_WIDTH = 1;
	private final static int SPIKE_DEPTH = 1;
	private final static Block SPIKE_BLOCK = NuCraft.purisBlock;
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{	            
	                    int spikeHeight = random.nextInt(SPIKE_HEIGHT_DEV) + MIN_SPIKE_HEIGHT;
	                    
	                    boolean flag = true;
	                    
	                    //If the spike is within the requisite bounds;
	                    if(y >= 1 && y + spikeHeight + 1 <= 256)
	                    {
	                    	byte b0;
	                    	int i0;
	                    	Block block;
	                    	//Scanning through from bottom to top
	                    	for(int tempY = y;tempY <= y + spikeHeight + 1;++tempY)
	                    	{
	                    		b0 = 1;
	                    		if(tempY == y) b0 = 0;
	                    		if(tempY >= y + spikeHeight - 1) b0 = 2;
	                    		
	                    		for(int tempX = x - b0;tempX <= x + b0 && flag;++tempX)
	                    		{
	                    			for(int tempZ = z - b0;tempZ <= z + b0 && flag;++tempZ)
	                    			{
	                    				if(tempY >= 0 && tempY <= 256)
	                    				{
	                    					block = world.getBlock(tempX, tempY, tempZ);
	                    					//Is it a replacable block?
	                    					if(!isReplaceable(world, tempX, tempY, tempZ)) flag = false;
	                    				}
	                    				else flag = false;
	                    			}
	                    		}	
	                    	}
	                    	if(!flag) return false;
	                    	else
	                    	{
	                    		if(y < 256 - spikeHeight - 1)
	                    		{
	                    		   	for (int tempY = y; tempY <= y + spikeHeight; ++tempY)
	                    		   	{
	                                   	for (int tempX = x - SPIKE_WIDTH; tempX <= x + SPIKE_WIDTH; ++tempX)
	                                   	{
	                                       	int j2 = tempX - x;

	                                       	for (int tempZ = z - SPIKE_DEPTH; tempZ <= z + SPIKE_DEPTH; ++tempZ)
	                                       	{
	                                           	int l2 = tempZ - z;

	                                           	if ((random.nextInt(2) != 0 && tempY != y + spikeHeight) || (tempY == y + spikeHeight && tempX == x && tempZ == z))
	                                           	{
	                                               	Block block1 = world.getBlock(tempX, tempY, tempZ);

	                                               	if (block1.isAir(world, tempX, tempY, tempZ))
	                                               	{
	                                               		world.setBlock(tempX, tempY, tempZ, SPIKE_BLOCK, 0, 3);
	                                               	}
	                                           	}
	                                       	}
	                                   	}
	                                }
	                    		}
	                    		else return false;
	                    	}
	                    }
	                    return true;
					}
	
	 protected boolean isReplaceable(World world, int x, int y, int z)
	 {
	        Block block = world.getBlock(x, y, z);
	        return block.getMaterial() == Material.air ;
	 }
}

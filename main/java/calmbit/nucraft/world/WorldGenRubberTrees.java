package calmbit.nucraft.world;

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

public class WorldGenRubberTrees extends WorldGenerator
{
	/**
	 * Minimum tree height.
	 */
	private final static int MIN_TREE_HEIGHT = 5;
	/**
	 * Deviation in tree height.
	 */
	private final static int TREE_HEIGHT_DEV = 5;
	/**
	 * Block to use for leaf generation.
	 */
	private final static Block LEAF_BLOCK = NuCraft.leavesRubberTree;
	/**
	 * Block to use for trunk generation.
	 */
	private final static Block TRUNK_BLOCK = NuCraft.logRubberTree;
	/**
	 * Height of leaves below top.
	 */
	private final static int LEAF_HEIGHT = 4;
	/**
	 * Width/Height Scaling for leaves (needs research)
	 */
	private final static int LEAF_INVERSE_SCALE = 3;
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{	            
	                    int treeHeight = random.nextInt(TREE_HEIGHT_DEV) + MIN_TREE_HEIGHT;
	                    
	                    boolean flag = true;
	                    
	                    //If the tree is within the requisite bounds;
	                    if(y >= 1 && y + treeHeight + 1 <= 256)
	                    {
	                    	byte b0;
	                    	int i0;
	                    	Block block;
	                    	//Scanning through from bottom to top
	                    	for(int tempY = y;tempY <= y + treeHeight + 1;++tempY)
	                    	{
	                    		b0 = 1;
	                    		if(tempY == y) b0 = 0;
	                    		if(tempY >= y + treeHeight - 1) b0 = 2;
	                    		
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
	                    		//Now let's grab the ground under the tree
	                    		Block block2 = world.getBlock(x, y - 1, z);
	                    		//Just use a vanilla sapling figuring we're assuming our sapling ~= Vanilla sapling
	                    		boolean isSoil = block2.canSustainPlant(world, x, y-1, z, ForgeDirection.UP, (IPlantable) Blocks.sapling);
	                    		if(isSoil && y < 256 - treeHeight - 1)
	                    		{
	                    			block2.onPlantGrow(world, x, y - 1, z, x, y, z);
	                    		   	b0 = LEAF_HEIGHT;
	                    		   	byte b1 = 0;
	                    		   	for (int tempY = y - b0 + treeHeight; tempY <= y + treeHeight; ++tempY)
	                               	{
	                                   	int i3 = tempY - (y + treeHeight);
	                                   	int l1 = b1 + 1 - i3 / LEAF_INVERSE_SCALE;

	                                   	for (int tempX = x - l1; tempX <= x + l1; ++tempX)
	                                   	{
	                                       	int j2 = tempX - x;

	                                       	for (int tempZ = z - l1; tempZ <= z + l1; ++tempZ)
	                                       	{
	                                           	int l2 = tempZ - z;

	                                           	if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0)
	                                           	{
	                                               	Block block1 = world.getBlock(tempX, tempY, tempZ);

	                                               	if (block1.isAir(world, tempX, tempY, tempZ) || block1.isLeaves(world, tempX, tempY, tempZ))
	                                               	{
	                                               		world.setBlock(tempX, tempY, tempZ, LEAF_BLOCK, 0, 3);
	                                               	}
	                                           	}
	                                       	}
	                                   	}
	                                }
	                    			for (int tempY = y; tempY < y + treeHeight; ++tempY)
	                                {
	                                    block = world.getBlock(x, tempY + y, z);

	                                    if (block.isAir(world, x, tempY, z) || block.isAssociatedBlock(LEAF_BLOCK));
	                                    {
	                                        world.setBlock(x, tempY, z, TRUNK_BLOCK, 0, 3);
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
	        boolean flag1 = block.getMaterial() == Material.air || block.getMaterial() == Material.leaves || block == Blocks.grass || block == Blocks.dirt || block == Blocks.log || block == Blocks.log2 || block == Blocks.sapling || block == Blocks.vine || block == Blocks.double_plant;
	        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || flag1;
	 }
}

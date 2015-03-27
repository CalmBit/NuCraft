package calmbit.nucraft.world;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockHopsCrop extends BlockCrops {

	public BlockHopsCrop()
	{
		super();
		setBlockName("hopsCrop");
		setBlockTextureName("nucraftworld:hopsCrop");
	}
	
	@Override
	protected Item func_149866_i()
    {
        return NuCraft.hopsCropSeeds;
    }

	@Override
    protected Item func_149865_P()
    {
        return NuCraft.hopsCropItem;
    }
	
	@Override
	public boolean canBlockStay(World world, int blockX, int blockY, int blockZ)
    {
		Block block = world.getBlock(blockX, blockY-1, blockZ);
		if(block instanceof BlockFarmland)
		{
			return true;
		}
		return false;
    }
}

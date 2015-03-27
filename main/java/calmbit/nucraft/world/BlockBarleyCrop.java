package calmbit.nucraft.world;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockBarleyCrop extends BlockCrops {

	public BlockBarleyCrop()
	{
		super();
		setBlockName("barleyCrop");
		setBlockTextureName("nucraftworld:barleyCrop");
	}
	
	@Override
	protected Item func_149866_i()
    {
        return NuCraft.barleyCropSeeds;
    }

	@Override
    protected Item func_149865_P()
    {
        return NuCraft.barleyCropItem;
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

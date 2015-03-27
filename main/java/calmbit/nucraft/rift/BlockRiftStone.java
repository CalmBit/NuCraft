package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockRiftStone extends Block {

	public BlockRiftStone() {
		super(Material.rock);
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5f);
		this.setBlockName("riftStone");
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("nucraftrift:riftStone");
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Item.getItemFromBlock(NuCraft.riftCobblestone);
	}
	
	@Override
	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target)
    {
        return true;
    }

	

}

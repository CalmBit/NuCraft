package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockAmethiteOre extends Block {

	public BlockAmethiteOre() {
		super(Material.rock);
		this.setBlockName("amethiteOre");
		this.setBlockTextureName("nucraftrift:amethiteOre");
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return NuCraft.amethite;
	}
	
	

}

package calmbit.nucraft.rift;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockLeptrusOre extends Block{
	public BlockLeptrusOre()
	{
		super(Material.rock);
		setHarvestLevel("pickaxe", 1);
		setHardness(4.0f);
		setStepSound(Block.soundTypeStone);
		setBlockName("leptrusOre");
		setBlockTextureName("nucraftrift:leptrusOre");
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
}

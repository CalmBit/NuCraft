package calmbit.nucraft.rift;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockRiftCobblestone extends Block {

	public BlockRiftCobblestone() {
		super(Material.rock);
		this.setStepSound(soundTypeStone);
		this.setHardness(2f);
		this.setBlockName("riftCobblestone");
		this.setHarvestLevel("pickaxe", 0);
		this.setBlockTextureName("nucraftrift:riftCobblestone");
	}	

}

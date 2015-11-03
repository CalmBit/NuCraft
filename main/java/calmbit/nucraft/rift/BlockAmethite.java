package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;

public class BlockAmethite extends Block 
{

	public BlockAmethite()
	{
		super(Material.iron);
		setHarvestLevel("pickaxe", 2);
		setHardness(5.0f);
		setStepSound(Block.soundTypeMetal);
		setBlockName("amethiteBlock");
		setBlockTextureName("nucraftrift:amethiteBlock");
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
}

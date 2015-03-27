package calmbit.nucraft.power;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGlassBottle;

public class BlockLeadOre extends Block 
{

	public BlockLeadOre()
	{
		super(Material.rock);
		setHarvestLevel("pickaxe", 1);
		setHardness(6.0f);
		setStepSound(Block.soundTypeStone);
		setBlockName("leadOre");
		setBlockTextureName("nucraftpower:leadOre");
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
}

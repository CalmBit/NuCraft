package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockPlasmaticTreeDoor extends BlockDoor {

	public BlockPlasmaticTreeDoor() {
		super(Material.wood);
		setBlockName("doorPlasmaticTree");
		setBlockTextureName("nucraftrift:doorPlasmaticTree");
		setHardness(4.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return (p_149650_1_ & 8) != 0 ? null : NuCraft.doorPlasmaticTreeItem;
    }

}

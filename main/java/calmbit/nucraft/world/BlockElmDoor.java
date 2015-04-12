package calmbit.nucraft.world;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class BlockElmDoor extends BlockDoor {

	public BlockElmDoor() {
		super(Material.wood);
		setBlockName("doorElm");
		setBlockTextureName("nucraftworld:doorElm");
		setHardness(3.0F);
		setStepSound(soundTypeWood);
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		return (p_149650_1_ & 8) != 0 ? null : NuCraft.doorElmItem;
    }

}

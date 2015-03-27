package calmbit.nucraft.world;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockRubberTreeDoor extends BlockDoor {

	public BlockRubberTreeDoor() {
		super(Material.wood);
		setBlockName("doorRubberTree");
		setBlockTextureName("nucraftworld:doorRubberTree");
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return NuCraft.doorRubberTreeItem;
    }

}

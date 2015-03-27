package calmbit.nucraft.world;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockHellTreeDoor extends BlockDoor {

	public BlockHellTreeDoor() {
		super(Material.wood);
		setBlockName("doorHellTree");
		setBlockTextureName("nucraftworld:doorHellTree");
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return NuCraft.doorHellTreeItem;
    }

}

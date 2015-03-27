package calmbit.nucraft.world;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemHopsSeeds extends ItemSeeds {

	public ItemHopsSeeds() {
		super(NuCraft.hopsCrop, Blocks.farmland);
		setUnlocalizedName("hopsCropSeeds");
		setTextureName("nucraftworld:hopsCropSeeds");
	}

}

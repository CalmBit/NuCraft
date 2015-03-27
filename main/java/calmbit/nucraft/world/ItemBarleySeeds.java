package calmbit.nucraft.world;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemBarleySeeds extends ItemSeeds {

	public ItemBarleySeeds() {
		super(NuCraft.barleyCrop, Blocks.farmland);
		setUnlocalizedName("barleyCropSeeds");
		setTextureName("nucraftworld:barleyCropSeeds");
	}

}

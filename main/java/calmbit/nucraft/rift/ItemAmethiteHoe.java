package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemHoe;

public class ItemAmethiteHoe extends ItemHoe {

	public ItemAmethiteHoe() {
		super(NuCraft.amethiteMaterial);
		this.setTextureName("nucraftrift:amethite_hoe");
		this.setUnlocalizedName("amethiteHoe");
	}

}

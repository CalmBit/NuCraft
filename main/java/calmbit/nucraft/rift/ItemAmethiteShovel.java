package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemSpade;

public class ItemAmethiteShovel extends ItemSpade {

	public ItemAmethiteShovel() {
		super(NuCraft.amethiteMaterial);
		this.setTextureName("nucraftrift:amethite_shovel");
		this.setUnlocalizedName("amethiteShovel");
	}

}

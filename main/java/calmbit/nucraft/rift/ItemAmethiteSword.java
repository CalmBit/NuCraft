package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemSword;

public class ItemAmethiteSword extends ItemSword {

	public ItemAmethiteSword() {
		super(NuCraft.amethiteToolMaterial);
		this.setTextureName("nucraftrift:amethite_sword");
		this.setUnlocalizedName("amethiteSword");
	}

}

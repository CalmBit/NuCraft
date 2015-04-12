package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemPickaxe;

public class ItemAmethitePickaxe extends ItemPickaxe {

	public ItemAmethitePickaxe() {
		super(NuCraft.amethiteToolMaterial);
		this.setTextureName("nucraftrift:amethite_pickaxe");
		this.setUnlocalizedName("amethitePickaxe");
	}

}

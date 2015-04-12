package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemSword;

public class ItemLeptrusSword extends ItemSword {

	public ItemLeptrusSword() {
		super(NuCraft.leptrusToolMaterial);
		this.setTextureName("nucraftrift:leptrus_sword");
		this.setUnlocalizedName("leptrusSword");
	}

}

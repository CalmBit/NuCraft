package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemSpade;

public class ItemLeptrusShovel extends ItemSpade {

	public ItemLeptrusShovel() {
		super(NuCraft.leptrusToolMaterial);
		this.setTextureName("nucraftrift:leptrus_shovel");
		this.setUnlocalizedName("leptrusShovel");
	}

}

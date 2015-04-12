package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.item.ItemPickaxe;

public class ItemLeptrusPickaxe extends ItemPickaxe {

	public ItemLeptrusPickaxe() {
		super(NuCraft.leptrusToolMaterial);
		this.setTextureName("nucraftrift:leptrus_pickaxe");
		this.setUnlocalizedName("leptrusPickaxe");
	}

}

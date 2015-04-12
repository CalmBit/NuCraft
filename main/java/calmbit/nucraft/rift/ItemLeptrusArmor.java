package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemLeptrusArmor extends ItemArmor  {

	public String textureName;
	public ItemLeptrusArmor(String unlocalizedName, String textureName, int type) {
		super(NuCraft.leptrusArmorMaterial, 0, type);
		this.textureName = textureName;
		this.setUnlocalizedName(unlocalizedName);
		this.setTextureName("nucraftrift:" + unlocalizedName);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
	    return "nucraftrift:textures/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}

}

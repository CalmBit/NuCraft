package calmbit.nucraft.power;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class ItemCopperIngot extends Item 
{
	public ItemCopperIngot()
	{
		setMaxStackSize(64);
		setUnlocalizedName("copperIngot");
		setTextureName("nucraftpower:copperIngot");
	}
	
}

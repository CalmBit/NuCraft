package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class ItemLeptrusIngot extends Item 
{
	public ItemLeptrusIngot()
	{
		setMaxStackSize(64);
		setUnlocalizedName("leptrusIngot");
		setTextureName("nucraftrift:leptrusIngot");
	}
	
}

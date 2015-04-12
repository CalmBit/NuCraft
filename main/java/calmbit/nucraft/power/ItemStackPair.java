package calmbit.nucraft.power;

import net.minecraft.item.ItemStack;

//Merciful god. this is an ugly hack.
public class ItemStackPair {

	public ItemStack one;
	public ItemStack two;
	
	public ItemStackPair(ItemStack one, ItemStack two)
	{
		this.one = one;
		this.two = two;
	}
	
	public boolean equals(ItemStackPair pair)
	{
		return compareStacks(this.one, pair.one) && compareStacks(this.two, pair.two);
	}
	
	private boolean compareStacks(ItemStack one, ItemStack two)
	{
	   return two.getItem() == one.getItem() && (two.getItemDamage() == 32767 || two.getItemDamage() == one.getItemDamage());
	}
}

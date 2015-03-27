package calmbit.nucraft.core;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class NuCraftFuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel != null)
		{
			if(fuel.getItem() == NuCraft.carbonWafer) return 1600;
			else if(fuel.getItem() == NuCraft.heavyCrudeOilBucket) return 13320;
			else return 0; 
		}
		else return 0;
	}

}

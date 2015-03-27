package calmbit.nucraft.power;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBatteryBase extends Item {

	public static enum BatteryComposition
	{
		ALKALINE,
		CARBON_ZINC,
		NICD,
		NIMH
	}
	private int mAh;
	private BatteryComposition composition;
	public ItemBatteryBase(int mAh, BatteryComposition composition)
	{
		this.mAh = mAh;
		this.composition = composition;
		this.setUnlocalizedName("battery_mAh" + mAh + "_comp_" + composition.toString());
		this.setMaxDamage(mAh);
		this.setTextureName("nucraftpower:battery" + composition.toString());
	}
	
	public static float getVoltage(BatteryComposition composition)
	{
		switch(composition)
		{
			case ALKALINE:
			case CARBON_ZINC:
				return 1.5f;
				
			case NICD:
			case NIMH:
				return 1.2f;
			
			default:
				return 0.0f;
		}
	}
	
	public int getmAh()
	{
		return mAh;
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player,
            List par3List, boolean par4) {
        par3List.add(StatCollector.translateToLocal("battery_mAh" + mAh + "_comp_" + composition.toString() + "_description"));
    }
		
	
}

package calmbit.nucraft.world;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHellFruit extends ItemFood {

	public ItemHellFruit() {
		super(4, 0.5f, false);
		setUnlocalizedName("hellFruit");
		setTextureName("nucraftworld:hellFruit");
	}
	
	@Override
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
		if(!world.isRemote)
		{
			player.setFire(2);
			super.onFoodEaten(itemStack, world, player);
		}
    }
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player,
            List par3List, boolean par4) {
        par3List.add("§cEat it...if you dare.§r");
    }
	

}

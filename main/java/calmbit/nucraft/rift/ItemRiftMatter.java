package calmbit.nucraft.rift;

import java.util.List;

import calmbit.nucraft.power.EntityNitroPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRiftMatter extends Item {
	
	public ItemRiftMatter()
	{
		this.setUnlocalizedName("riftMatter");
		this.setTextureName("nucraftrift:riftMatter");
		this.setMaxStackSize(8);
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player,
            List par3List, boolean par4) {
        par3List.add("§c§oIt buzzes with energy...§r");
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
		if(!world.isRemote)
		{
			EntityRiftDaemon maerc = new EntityRiftDaemon(world);
			maerc.setLocationAndAngles(posX, posY+3.8, posZ, 0, 0);
			
			world.spawnEntityInWorld(maerc);
		}
        return true;
    }
	

}

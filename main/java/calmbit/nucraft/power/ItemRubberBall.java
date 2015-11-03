package calmbit.nucraft.power;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityRubberBall;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRubberBall extends Item {

	public ItemRubberBall()
	{
		setMaxStackSize(64);
		setUnlocalizedName("rubberBall");
		setTextureName("nucraftpower:rubberBall");
	}
	
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer player)
    {
		player.addStat(NuCraft.boingBoing, 1);
		
        if (!player.capabilities.isCreativeMode)
        {
            --p_77659_1_.stackSize;
        }

        p_77659_2_.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!p_77659_2_.isRemote)
        {
            p_77659_2_.spawnEntityInWorld(new EntityRubberBall(p_77659_2_, player));
        }

        return p_77659_1_;
    }
}

package calmbit.nucraft.world;

import java.util.List;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemAcidBucket extends ItemBucket {

	public ItemAcidBucket(Block p_i45331_1_) {
		super(p_i45331_1_);
		setMaxStackSize(1);
		setUnlocalizedName("acidBucket");
		setTextureName("nucraftworld:acidBucket");
	}
	

}

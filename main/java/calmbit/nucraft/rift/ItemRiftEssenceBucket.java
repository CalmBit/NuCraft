package calmbit.nucraft.rift;

import java.util.List;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemRiftEssenceBucket extends ItemBucket {

	public ItemRiftEssenceBucket(Block p_i45331_1_) {
		super(p_i45331_1_);
		setMaxStackSize(1);
		setUnlocalizedName("riftEssenceBucket");
		setTextureName("nucraftrift:riftEssenceBucket");
	}
}

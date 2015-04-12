package calmbit.nucraft.power;

import java.util.List;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;

public class ItemHeavyCrudeOilBucket extends ItemBucket {

	public ItemHeavyCrudeOilBucket(Block p_i45331_1_) {
		super(p_i45331_1_);
		setMaxStackSize(1);
		setUnlocalizedName("heavyCrudeOilBucket");
		setTextureName("nucraftpower:oilBucket");
		setContainerItem(Items.bucket);
	}
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer player,
            List par3List, boolean par4) {
        par3List.add("§e23 API§r");
    }
	

}

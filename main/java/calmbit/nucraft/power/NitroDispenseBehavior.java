package calmbit.nucraft.power;

import net.minecraft.client.Minecraft;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class NitroDispenseBehavior implements IBehaviorDispenseItem{

	@Override
	public ItemStack dispense(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
    {
        EnumFacing var3 = EnumFacing.getFront(metadataToFacing(par1IBlockSource.getBlockMetadata()));
        double var4 = par1IBlockSource.getX() + (double)var3.getFrontOffsetX();
        double var6 = par1IBlockSource.getY() + (double)var3.getFrontOffsetY();
        double var8 = par1IBlockSource.getZ() + (double)var3.getFrontOffsetZ();
        par1IBlockSource.getWorld().spawnEntityInWorld(new EntityNitroPrimed(par1IBlockSource.getWorld(), var4, var6, var8, null));
        par1IBlockSource.getWorld().playAuxSFX(1000, (int)var4, (int)var6, (int)var8, 0);
        par2ItemStack.splitStack(1);
        return par2ItemStack;
    }
	
	public int metadataToFacing(int meta)
	{
		if(meta <= 5) return meta;
		if(meta == 8) return 0;
		if(meta == 9) return 1;
		if(meta == 10) return 2;
		if(meta == 11) return 3;	
		if(meta == 12) return 4;
		if(meta == 13) return 5;
		return 0;
	}

}

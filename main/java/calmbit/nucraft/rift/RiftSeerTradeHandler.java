package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class RiftSeerTradeHandler implements IVillageTradeHandler 
{

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		recipeList.add(new MerchantRecipe(new ItemStack(NuCraft.steelIngot, 3 + random.nextInt(3)), null, new ItemStack(Items.emerald, 1 + random.nextInt(2))));
		recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 6 + random.nextInt(4)), null, new ItemStack(NuCraft.copperBlock, 1 + random.nextInt(3))));
		recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 20 + random.nextInt(10)), null, new ItemStack(NuCraft.machineCasing, 1)));
	}

}

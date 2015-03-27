package calmbit.nucraft.power;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CompressorRecipes {

	private static final CompressorRecipes INSTANCE = new CompressorRecipes();
	private HashMap<ItemStack, ItemStack> compressionList = new HashMap<ItemStack, ItemStack>();
	
	public static CompressorRecipes compressor()
	{
		return INSTANCE;
	}
	
	public CompressorRecipes()
	{
		AddRecipe(new ItemStack(Items.coal), new ItemStack(NuCraft.carbonWafer));
		AddRecipe(new ItemStack(Blocks.sand), new ItemStack(NuCraft.siliconFragment));
		AddRecipe(new ItemStack(Items.sugar), new ItemStack(NuCraft.yeastDust));
	}
	
	public void AddRecipe(ItemStack input, ItemStack output)
	{
		compressionList.put(input, output);
	}
	
	public ItemStack GetCompressionResult(ItemStack input)
	{
		Iterator iterator = this.compressionList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.compareStacks(input, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
	}
	
	 private boolean compareStacks(ItemStack one, ItemStack two)
	 {
        return two.getItem() == one.getItem() && (two.getItemDamage() == 32767 || two.getItemDamage() == one.getItemDamage());
	 }
	
}

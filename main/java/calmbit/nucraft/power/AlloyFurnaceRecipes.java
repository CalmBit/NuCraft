package calmbit.nucraft.power;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.Pair;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AlloyFurnaceRecipes {

	private static final AlloyFurnaceRecipes INSTANCE = new AlloyFurnaceRecipes();
	private HashMap<ItemStackPair, ItemStack> alloyList = new HashMap<ItemStackPair, ItemStack>();
	
	public static AlloyFurnaceRecipes alloyFurnace()
	{
		return INSTANCE;
	}
	
	public AlloyFurnaceRecipes()
	{
		AddRecipe(new ItemStack(Items.iron_ingot), new ItemStack(NuCraft.carbonWafer), new ItemStack(NuCraft.steelIngot));
		AddRecipe(new ItemStack(NuCraft.carbonWafer), new ItemStack(Items.iron_ingot), new ItemStack(NuCraft.steelIngot));
		AddRecipe(new ItemStack(NuCraft.copperIngot), new ItemStack(NuCraft.tinIngot), new ItemStack(NuCraft.bronzeIngot));
		AddRecipe(new ItemStack(NuCraft.tinIngot), new ItemStack(NuCraft.copperIngot), new ItemStack(NuCraft.bronzeIngot));
	}
	
	public void AddRecipe(ItemStack inputOne, ItemStack inputTwo, ItemStack output)
	{
		alloyList.put(new ItemStackPair(inputOne, inputTwo), output);
	}
	
	public ItemStack GetAlloyResult(ItemStack inputOne, ItemStack inputTwo)
	{
		Iterator iterator = this.alloyList.entrySet().iterator();
        Entry entry;
        HashMap hash = new HashMap<ItemStack,ItemStack>();
        do
        {
        	hash.clear();
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
            hash.put(inputOne, inputTwo);
        }
        while (!((ItemStackPair)entry.getKey()).equals(new ItemStackPair(inputOne, inputTwo)));

        return (ItemStack)entry.getValue();
	}
}

package calmbit.nucraft.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FermentationTankRecipes {

	private static final FermentationTankRecipes INSTANCE = new FermentationTankRecipes();
	private HashMap<ArrayList<ItemStack>, Integer> compressionList = new HashMap<ArrayList<ItemStack>, Integer>();
	
	public static FermentationTankRecipes fermentationTank()
	{
		return INSTANCE;
	}
	
	public FermentationTankRecipes()
	{
		
	}
	
	public void AddRecipe(ItemStack hops, ItemStack grain, ItemStack yeast, int result)
	{
		ArrayList<ItemStack> input = new ArrayList<ItemStack>();
		input.add(hops);
		input.add(grain);
		input.add(yeast);
		compressionList.put(input, result);
	}
	
	public int GetFermentationResult(ItemStack hops, ItemStack grain, ItemStack yeast)
	{
		Iterator iterator = this.compressionList.entrySet().iterator();
        Entry entry;
        ArrayList<ItemStack> input = new ArrayList<ItemStack>();
		input.add(hops);
		input.add(grain);
		input.add(yeast);
        do
        {
            if (!iterator.hasNext())
            {
                return 0;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.compareLists(input, (ArrayList<ItemStack>)entry.getKey()));

        return (Integer)entry.getValue();
	}
	
	 private boolean compareStacks(ItemStack one, ItemStack two)
	 {
        return two.getItem() == one.getItem() && (two.getItemDamage() == 32767 || two.getItemDamage() == one.getItemDamage());
	 }
	 
	 private boolean compareLists(ArrayList<ItemStack> one, ArrayList<ItemStack> two)
	 {
		 if(one.size() == two.size())
		 {
			 boolean areSame = true;
			 for(int i = 0; i < one.size();i++)
			 {
				 if(!compareStacks(one.get(i), two.get(i))) areSame = false;
			 }
			 return areSame;
		 }
		 else
		 {
			 return false;
		 }
	 }
	
}

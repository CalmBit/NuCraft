package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class BlockPuris extends Block {

	public BlockPuris() {
		super(Material.rock);
		this.setStepSound(soundTypeGlass);
		this.setHardness(1f);
		this.setBlockName("purisBlock");
		this.setHarvestLevel("pickaxe", 0); 
		this.setBlockTextureName("nucraftrift:purisBlock");
		this.setLightLevel(0.65f);
	}	
	
	/**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
	@Override
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return MathHelper.clamp_int(this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1), 1, 6);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random p_149745_1_)
    {
        return 1 + p_149745_1_.nextInt(2);
    }

	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return NuCraft.purisShard;
    }

}

package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;

public class BlockPurisGlass extends Block {
	 
	private boolean field_149996_a = false;
	
		public BlockPurisGlass() {
			super(Material.glass);
			this.setBlockName("purisGlass");
			this.setBlockTextureName("nucraftrift:purisGlass");
			setStepSound(Block.soundTypeGlass);
			this.setHardness(0.3f);
		}
		
		@SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	    {
	        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);

	        if (this == NuCraft.purisGlass)
	        {
	            if (p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_]))
	            {
	                return true;
	            }

	            if (block == this)
	            {
	                return false;
	            }
	        }

	        return !this.field_149996_a && block == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	    }

		
		public boolean isOpaqueCube()
	    {
	        return false;
	    }
		
		/**
	     * Returns the quantity of items to drop on block destruction.
	     */
	    public int quantityDropped(Random p_149745_1_)
	    {
	        return 0;
	    }
	
	    /**
	     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	     */
	    @SideOnly(Side.CLIENT)
	    public int getRenderBlockPass()
	    {
	        return 1;
	    }
	
	    /**
	     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	     */
	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
	
	    /**
	     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
	     */
	    protected boolean canSilkHarvest()
	    {
	        return true;
	    }
}


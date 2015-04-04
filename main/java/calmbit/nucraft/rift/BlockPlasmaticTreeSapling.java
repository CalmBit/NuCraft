package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPlasmaticTreeSapling extends BlockBush implements IGrowable {

	protected IIcon saplingTexture;
	
	public BlockPlasmaticTreeSapling()
	{
		super(Material.grass);
		setStepSound(soundTypeGrass);
		float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setBlockName("saplingPlasmaticTree");
	}
	
	/***
	 * Still Growing?
	 */
	@Override
	public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_,
			boolean p_149851_5_) {
		return true;
	}

	/**
	 * Bone meal speedup?
	 */
	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_,
			int p_149852_5_) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return saplingTexture;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		saplingTexture = register.registerIcon("nucraftrift:saplingPlasmaticTree");
	}
	
	public void generate(World world, int x, int y, int z, Random random)
	{
		if(world.getBlock(x, y-1, z) != NuCraft.riftDirt && world.getBlock(x, y-1, z) != NuCraft.riftGrass) return;
		WorldGenPlasmaticTrees gen = new WorldGenPlasmaticTrees();
		world.setBlock(x, y, z, Blocks.air, 0, 4);
		if(!gen.generate(world, random, x, y, z))
		{
			world.setBlock(x, y, z, this, 0, 4);
		}
	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
    {
		Block block  = p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
        return  block == NuCraft.riftDirt || block == NuCraft.riftGrass;
    }
	
	/***
	 * Increment growth stage.
	 */
	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) 
	{
		generate(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
	}

}

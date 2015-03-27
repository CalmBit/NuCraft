package calmbit.nucraft.world;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHellTreeLog extends BlockLog {

	@SideOnly(Side.CLIENT)
	protected IIcon logHellTreeSide;
	
	@SideOnly(Side.CLIENT)
	protected IIcon logHellTreeTop;
	
	public BlockHellTreeLog() {
		 super();
	     this.setHardness(4.0F);
	     this.setStepSound(soundTypeWood);
	     this.setBlockName("logHellTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		logHellTreeSide = register.registerIcon("nucraftworld:logHellTree");
		logHellTreeTop = register.registerIcon("nucraftworld:logHellTreeTop");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int meta) {
		return logHellTreeSide;
	}
	
	@Override
	public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side)
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getTopIcon(int meta)
	{
		return logHellTreeTop;
	}
	
}

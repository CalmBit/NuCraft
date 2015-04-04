package calmbit.nucraft.rift;

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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockPlasmaticTreeLog extends BlockLog {

	@SideOnly(Side.CLIENT)
	protected IIcon logPlasmaticTreeSide;
	
	@SideOnly(Side.CLIENT)
	protected IIcon logPlasmaticTreeTop;
	
	public BlockPlasmaticTreeLog() {
		 super();
	     this.setHardness(4.0F);
	     this.setStepSound(soundTypeWood);
	     this.setBlockName("logPlasmaticTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		logPlasmaticTreeSide = register.registerIcon("nucraftrift:logPlasmaticTree");
		logPlasmaticTreeTop = register.registerIcon("nucraftrift:logPlasmaticTreeTop");
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int meta) {
		return logPlasmaticTreeSide;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getTopIcon(int meta)
	{
		return logPlasmaticTreeTop;
	}
	
}

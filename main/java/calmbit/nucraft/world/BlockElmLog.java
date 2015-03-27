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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockElmLog extends BlockLog {

	@SideOnly(Side.CLIENT)
	protected IIcon logElmSide;
	
	@SideOnly(Side.CLIENT)
	protected IIcon logElmTop;
	
	public BlockElmLog() {
		 super();
	     this.setHardness(2.0F);
	     this.setStepSound(soundTypeWood);
	     this.setBlockName("logElm");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		logElmSide = register.registerIcon("nucraftworld:logElm");
		logElmTop = register.registerIcon("nucraftworld:logElmTop");
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int meta) {
		return logElmSide;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getTopIcon(int meta)
	{
		return logElmTop;
	}
	
}

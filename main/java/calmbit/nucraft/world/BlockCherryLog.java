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

public class BlockCherryLog extends BlockLog {

	@SideOnly(Side.CLIENT)
	protected IIcon logCherrySide;
	
	@SideOnly(Side.CLIENT)
	protected IIcon logCherryTop;
	
	public BlockCherryLog() {
		 super();
	     this.setHardness(2.0F);
	     this.setStepSound(soundTypeWood);
	     this.setBlockName("logCherry");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		logCherrySide = register.registerIcon("nucraftworld:logCherry");
		logCherryTop = register.registerIcon("nucraftworld:logCherryTop");
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int meta) {
		return logCherrySide;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getTopIcon(int meta)
	{
		return logCherryTop;
	}
	
}

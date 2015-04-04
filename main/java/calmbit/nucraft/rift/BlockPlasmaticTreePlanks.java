package calmbit.nucraft.rift;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

public class BlockPlasmaticTreePlanks extends Block {

	@SideOnly(Side.CLIENT)
	protected IIcon plankSide;
	
	public BlockPlasmaticTreePlanks() {
		super(Material.wood);
		setHardness(4.0F);
		setResistance(10.0F);
		setStepSound(soundTypeWood);
		setBlockName("planksPlasmaticTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		plankSide = register.registerIcon("nucraftrift:planksPlasmaticTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return plankSide;
	}
	

}

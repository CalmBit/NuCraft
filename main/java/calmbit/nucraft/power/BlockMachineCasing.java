package calmbit.nucraft.power;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockMachineCasing extends Block {

	@SideOnly(Side.CLIENT)
	public IIcon casingTexture;
	
	public BlockMachineCasing() {
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setHardness(5.0f);
		setHarvestLevel("pickaxe", 1);
		setBlockName("machineCasing");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		casingTexture = register.registerIcon("nucraftpower:machineCasing");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return casingTexture;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

}

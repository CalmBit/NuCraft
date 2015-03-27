package calmbit.nucraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockBarleyBale extends BlockRotatedPillar {

	public BlockBarleyBale()
	{
		super(Material.grass);
		this.setBlockName("barleyBale");
		this.setBlockTextureName("nucraftworld:barleyBale");
		this.setStepSound(soundTypeGrass);
	}

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int meta)
    {
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.field_150164_N = register.registerIcon(this.getTextureName() + "_top");
        this.blockIcon = register.registerIcon(this.getTextureName() + "_side");
    }
}

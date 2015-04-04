package calmbit.nucraft.rift;

import java.util.List;
import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;

public class BlockPlasmaticTreeLeaves extends BlockLeaves 
{

	protected IIcon leavesPlasmaticTree;
	protected IIcon leavesPlasmaticTree_opaque;
	
	public BlockPlasmaticTreeLeaves()
	{
		super();
		setBlockName("leavesPlasmaticTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		leavesPlasmaticTree = register.registerIcon("nucraftrift:leavesPlasmaticTree");
		leavesPlasmaticTree_opaque = register.registerIcon("nucraftrift:leavesPlasmaticTree_opaque");
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(NuCraft.saplingPlasmaticTree);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		this.setGraphicsLevel((Minecraft.getMinecraft()).gameSettings.fancyGraphics);
		return !this.field_150121_P ? leavesPlasmaticTree_opaque :  leavesPlasmaticTree;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getBlockColor()
    {
        double d0 = 0.2D;
        double d1 = 0.0D;
        return 0xFFFFFF;
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int p_149741_1_)
    {
        return 0xFFFFFF;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
		//Has no sub blocks
    }
	
	@Override
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		return 0xFFFFFF;
	}

	@Override
	public String[] func_150125_e() 
	{
		return new String[]{"plasmatic"};
	}

}

package calmbit.nucraft.world;

import java.util.ArrayList;
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
import net.minecraft.world.World;

public class BlockRubberTreeLeaves extends BlockLeaves 
{

	protected IIcon leavesRubberTree;
	protected IIcon leavesRubberTree_opaque;
	
	public BlockRubberTreeLeaves()
	{
		super();
		setBlockName("leavesRubberTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		leavesRubberTree = register.registerIcon("nucraftworld:leavesRubberTree");
		leavesRubberTree_opaque = register.registerIcon("nucraftworld:leavesRubberTree_opaque");
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(NuCraft.saplingRubberTree);
    }

	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		this.setGraphicsLevel((Minecraft.getMinecraft()).gameSettings.fancyGraphics);
		return !this.field_150121_P ? leavesRubberTree_opaque :  leavesRubberTree;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerFoliage.getFoliageColor(d0, d1);
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int p_149741_1_)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
		//Has no sub blocks
    }
	
	

	@Override
	public String[] func_150125_e() 
	{
		return new String[]{"rubberTree"};
	}

}

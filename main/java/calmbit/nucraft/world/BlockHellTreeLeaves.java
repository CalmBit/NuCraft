package calmbit.nucraft.world;

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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.World;

public class BlockHellTreeLeaves extends BlockLeaves 
{

	protected IIcon leavesHellTree;
	protected IIcon leavesHellTree_opaque;
	
	public BlockHellTreeLeaves()
	{
		super();
		setBlockName("leavesHellTree");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		leavesHellTree = register.registerIcon("nucraftworld:leavesHellTree");
		leavesHellTree_opaque = register.registerIcon("nucraftworld:leavesHellTree_opaque");
	}
	
	@SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xFFFF0000;
    }

	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(NuCraft.saplingElm);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		this.setGraphicsLevel((Minecraft.getMinecraft()).gameSettings.fancyGraphics);
		return !this.field_150121_P ? leavesHellTree_opaque :  leavesHellTree;
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
		return new String[]{"hellTree"};
	}
	
	protected void func_150124_c(World world, int x, int y, int z, int meta, int chance)
    {
        if (world.rand.nextInt(chance) == 0)
        {
            this.dropBlockAsItem(world, x, y, z, new ItemStack(NuCraft.hellFruit, 1, 0));
        }
    }
	
	/***
	 * Sapling Chance (check out getDrops in BlockLeaves)
	 * @param meta metadata
	 */
	protected int func_150123_b(int meta)
    {
        return 20;
    }

}

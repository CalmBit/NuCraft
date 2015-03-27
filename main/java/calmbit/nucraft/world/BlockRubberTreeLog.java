package calmbit.nucraft.world;

import java.util.ArrayList;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRubberTreeLog extends BlockLog {

	@SideOnly(Side.CLIENT)
	protected IIcon logRubberTreeSide;
	
	@SideOnly(Side.CLIENT)
	protected IIcon logRubberTreeTop;
	
	public BlockRubberTreeLog() {
		 super();
	     this.setHardness(2.0F);
	     this.setStepSound(soundTypeWood);
	     this.setBlockName("logRubber");
	}
	
	
	@Override
    public ArrayList <ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(NuCraft.logRubberTree, 1));
        drops.add(new ItemStack(NuCraft.latexDroplet, world.rand.nextInt(2) + 1));
        return drops;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		logRubberTreeSide = register.registerIcon("nucraftworld:logRubberTree");
		logRubberTreeTop = register.registerIcon("nucraftworld:logRubberTreeTop");
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int meta) {
		return logRubberTreeSide;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getTopIcon(int meta)
	{
		return logRubberTreeTop;
	}
	
}

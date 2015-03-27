package calmbit.nucraft.rift;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockTempPortal extends Block {

	protected BlockTempPortal() {
		super(Material.rock);
		setBlockName("tempPortal");
	}
	
	 public boolean onBlockActivated(World world, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	 {
		 player.travelToDimension(2);
		 return true;
	 }

}

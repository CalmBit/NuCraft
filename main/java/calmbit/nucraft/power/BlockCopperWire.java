package calmbit.nucraft.power;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCopperWire extends Block implements ITileEntityProvider {

	public BlockCopperWire() {
		super(Material.cloth);
		setHarvestLevel("pickaxe", 0);
		setHardness(2.0f);
		setStepSound(Block.soundTypeCloth);
		setBlockName("copperWire");
		setBlockTextureName("nucraftpower:copperWire");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return null;
	}

}

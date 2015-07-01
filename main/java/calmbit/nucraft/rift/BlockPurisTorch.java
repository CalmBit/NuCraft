package calmbit.nucraft.rift;

import net.minecraft.block.BlockTorch;

public class BlockPurisTorch extends BlockTorch {

	public BlockPurisTorch()
	{
		super();
		this.setBlockTextureName("nucraftrift:purisTorch");
		this.setHardness(0.0F);
		this.setLightLevel(0.50F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("purisTorch");
	}
}

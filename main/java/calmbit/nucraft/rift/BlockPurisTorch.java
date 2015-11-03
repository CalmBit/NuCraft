package calmbit.nucraft.rift;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class BlockPurisTorch extends BlockTorch {

	public BlockPurisTorch()
	{
		super();
		this.setBlockTextureName("nucraftrift:purisTorch");
		this.setHardness(0.0F);
		this.setLightLevel(1.0F);
		this.setStepSound(soundTypeWood);
		this.setBlockName("purisTorch");
	}
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random random)
    {
        /*int l = world.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
        double d0 = (double)((float)p_149734_2_ + 0.5F);
        double d1 = (double)((float)p_149734_3_ + 0.7F);
        double d2 = (double)((float)p_149734_4_ + 0.5F);
        double d3 = 0.2199999988079071D;
        double d4 = 0.27000001072883606D;

        if (l == 1)
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPurisFlameFX(world, d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D));
        }
        else if (l == 2)
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPurisFlameFX(world, d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D));
        }
        else if (l == 3)
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPurisFlameFX(world, d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D));
        }
        else if (l == 4)
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPurisFlameFX(world, d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D));
        }
        else
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityPurisFlameFX(world, d0, d1, d2, 0.025D * random.nextDouble(), 0.025D * random.nextDouble(), 0.025D * random.nextDouble()));
        }*/
    }
}

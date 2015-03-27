package calmbit.nucraft.rift;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockRiftEssence extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	private IIcon riftEssenceStill;
	@SideOnly(Side.CLIENT)
	private IIcon riftEssenceFlowing;
	
	public BlockRiftEssence(Fluid fluid) {
		super(fluid, Material.water);
		setBlockName("riftEssence");
		setLightOpacity(1);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? riftEssenceStill : riftEssenceFlowing;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		riftEssenceStill = register.registerIcon("nucraftrift:riftEssenceStill");
		riftEssenceFlowing = register.registerIcon("nucraftrift:riftEssenceFlowing");
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity == null) return;

		if(entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP entityPlayer = (EntityPlayerMP)entity;
			if(entityPlayer.timeUntilPortal == 0) entityPlayer.mcServer.getConfigurationManager().transferPlayerToDimension(entityPlayer, entityPlayer.dimension == 2 ? 0 : 2, new TeleporterRift(entityPlayer.mcServer.worldServerForDimension(entityPlayer.dimension == 2 ? 0 : 2)));
		}
	}
	
	@Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
     
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, int bx, int by, int bz, Random rand)
    {
		 double fX = bx + (rand.nextDouble());
		 double fY = by;
		 double fZ = bz + (rand.nextDouble());
		 worldIn.spawnParticle("portal", fX, fY, fZ, 0, 1, 0);
    }

}

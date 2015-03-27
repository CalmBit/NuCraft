package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockRiftPlasma extends BlockFluidClassic  {

	@SideOnly(Side.CLIENT)
	protected IIcon riftPlasmaStill;
	@SideOnly(Side.CLIENT)
	protected IIcon riftPlasmaFlowing;
	public BlockRiftPlasma(Fluid fluid) {
		super(fluid, Material.lava);
		this.setBlockName("riftPlasma");
		this.setLightOpacity(1);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? riftPlasmaStill : riftPlasmaFlowing;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		riftPlasmaStill = register.registerIcon("nucraftrift:riftPlasmaStill");
		riftPlasmaFlowing = register.registerIcon("nucraftrift:riftPlasmaFlowing");
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity == null) return;

		//entity.motionY = Math.min(1.0, entity.motionY);

		/*if (entity.motionY < -0.05) {
			entity.motionY *= 0.05;
		}*/

		entity.motionX = Math.max(-0.05, Math.min(0.05, entity.motionX * 0.05));
		entity.motionY = Math.max(-0.05, Math.min(0.05, entity.motionY * 0.05));
		entity.motionZ = Math.max(-0.05, Math.min(0.05, entity.motionZ * 0.05));
		entity.setFire(10);
		entity.attackEntityFrom(new DamageSource("plasma"), (float) 8.0);
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


}

package calmbit.nucraft.power;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockHeavyCrudeOil extends BlockFluidFinite  {

	@SideOnly(Side.CLIENT)
	protected IIcon heavyCrudeStill;
	@SideOnly(Side.CLIENT)
	protected IIcon heavyCrudeFlowing;
	public BlockHeavyCrudeOil(Fluid fluid) {
		super(fluid, Material.water);
		this.setBlockName("heavyCrudeOil");
		this.setLightOpacity(1);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side == 0 || side == 1) ? heavyCrudeStill : heavyCrudeFlowing;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		heavyCrudeStill = register.registerIcon("nucraftpower:heavyCrudeOilStill");
		heavyCrudeFlowing = register.registerIcon("nucraftpower:heavyCrudeOilFlowing");
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
	}
	
	@Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
     
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid() || world.getBlock(x, y, z).getMaterial() == Material.fire) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
    
    @Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 300;
    }

    @Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 100;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return true;
    }

    @Override
    public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
        return false;
    }
	
	

}

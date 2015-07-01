package calmbit.nucraft.power;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRailLeptrus extends BlockRailPowered	 
{

	@SideOnly(Side.CLIENT)
    private IIcon field_150056_b;
    private static final String __OBFID = "CL_00000293";
    
	public BlockRailLeptrus()
    {
        setBlockName("railLeptrus");
        setBlockTextureName("nucraftpower:railLeptrus");
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_2_ >= 6 ? this.field_150056_b : this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        super.registerBlockIcons(p_149651_1_);
        this.field_150056_b = p_149651_1_.registerIcon(this.getTextureName() + "Powered");
    }
    
    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, int x, int y, int z)
    {
    	boolean power = (world.getBlockMetadata(x,y,z) & 8) != 0;
        return power ? 0.8f : 0.1f;
    }
    
    @Override
    public void onMinecartPass(World worldObj, EntityMinecart cart, int x, int y, int z) 
    {
    	boolean power = (worldObj.getBlockMetadata(x,y,z) & 8) != 0;
    	int l = this.getBasicRailMetadata(worldObj, null, x,y,z);
    	if(power)
    	{
	    	if(l == 1)
	    	{
		        if (worldObj.getBlock(x - 1, y, z).isNormalCube())
		        {
		            cart.motionX += 0.02D;
		        }
		        else if (worldObj.getBlock(x + 1, y, z).isNormalCube())
		        {
		            cart.motionX += -0.02D;
		        }
	    	}
	    	else if(l == 0)
	    	{
	        	if (worldObj.getBlock(x, y, z - 1).isNormalCube())
	            {
	                cart.motionZ += 0.02D;
	            }
	            else if (worldObj.getBlock(x, y, z + 1).isNormalCube())
	            {
	                cart.motionZ += -0.02D;
	            }
	    	}
    		cart.motionX *= 1.5;
        	cart.motionY *= 1.5;
        	cart.motionZ *= 1.5;
	    }	
    	
        else
    	{
    		cart.motionX *= 0.25;
        	cart.motionY *= 0.25;
        	cart.motionZ *= 0.25;
    	}
    };
}

package calmbit.nucraft.power;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAlloyFurnace extends BlockContainer {
	private final boolean isActive;
	
	@SideOnly(Side.CLIENT)
	public IIcon alloyFurnaceFront;
	@SideOnly(Side.CLIENT)
	public IIcon alloyFurnaceSide;
	
	public static boolean isChanging;
	
	public BlockAlloyFurnace(boolean isActive) {
		super(Material.iron);
		setHarvestLevel("pickaxe", 2);
		setHardness(5.0f);
		setStepSound(Block.soundTypeMetal);
		setBlockName("alloyFurnace");
		this.isActive = isActive;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		alloyFurnaceSide = register.registerIcon("nucraftpower:alloyFurnaceSide");
		alloyFurnaceFront = this.isActive ? register.registerIcon("nucraftpower:active_alloyFurnaceFront") : register.registerIcon("nucraftpower:alloyFurnaceFront");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata == 0 && side == 3) return alloyFurnaceFront;
		return (side == metadata) ? alloyFurnaceFront : alloyFurnaceSide;
	}
	
	public static void updateAlloyFurnaceState(boolean burning, World worldIn, int bx, int by, int bz)
	{
		int l = worldIn.getBlockMetadata(bx, by, bz);
        TileEntity tileentity = worldIn.getTileEntity(bx, by, bz);
        isChanging = true;

        if (burning)
        {
            worldIn.setBlock(bx, by, bz, NuCraft.active_alloyFurnace);
        }
        else
        {
            worldIn.setBlock(bx, by, bz, NuCraft.alloyFurnace);
        }

        isChanging = false;
        worldIn.setBlockMetadataWithNotify(bx, by, bz, l, 2);
        
        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(bx, by, bz, tileentity);
        }
	}
	
	@Override
	public boolean onBlockActivated(World world, int bX, int bY, int bZ, EntityPlayer player, int side, float tX, float tY, float tZ) 
	{
		if(world.isRemote) return true;
		player.openGui(NuCraft.instance, GuiHandlerAlloyFurnace.getGuiId(), world, bX, bY, bZ);
		return true;
	};
	
	@Override
	public void breakBlock(World world, int bX, int bY, int bZ, Block block, int meta) 
	{
		if(!isChanging)
		{
			TileEntity tileEntity = world.getTileEntity(bX, bY, bZ);
			if(tileEntity != null && tileEntity instanceof TileEntityAlloyFurnace)
			{
				IInventory inventory = (IInventory)tileEntity;
				for(int i = 0; i < inventory.getSizeInventory();i++)
				{
					if(inventory.getStackInSlot(i) != null)
					{
						EntityItem item = new EntityItem(world, bX, bY + 1.0, bZ, inventory.getStackInSlot(i));
						float motionX = world.rand.nextFloat() - 0.5f;
						float motionY = world.rand.nextFloat() - 0.5f;
						float motionZ = world.rand.nextFloat() - 0.5f;
						item.motionX = motionX * 0.2;
						item.motionY = motionY * 0.2;
						item.motionZ = motionZ * 0.2;
						world.spawnEntityInWorld(item);
						inventory.setInventorySlotContents(i, null);
					}
				}
			}
		}
	};
	
	@Override
	public void onBlockPlacedBy(World world, int bX, int bY, int bZ, EntityLivingBase placer, ItemStack stack) 
	{
		int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(bX, bY, bZ, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(bX, bY, bZ, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(bX, bY, bZ, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(bX, bY, bZ, 4, 2);
        }
        
		if(stack.hasDisplayName())
		{
			TileEntity tile = world.getTileEntity(bX, bY, bZ);
			if(tile instanceof TileEntityAlloyFurnace)
			{
				((TileEntityAlloyFurnace)tile).setCustomInventoryName(stack.getDisplayName());
			}
		}
	};
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, int bx, int by, int bz, Random rand)
    {
		 if (this.isActive)
	        {
	        	int l = worldIn.getBlockMetadata(bx, by, bz);
	            float f = (float)bx + 0.5F;
	            float f1 = (float)by + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
	            float f2 = (float)bz + 0.5F;
	            float f3 = 0.52F;
	            float f4 = rand.nextFloat() * 0.6F - 0.3F;

	            if (l == 4)
	            {
	                worldIn.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	                worldIn.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 5)
	            {
	                worldIn.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	                worldIn.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 2)
	            {
	                worldIn.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
	                worldIn.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
	            }
	            else if (l == 3)
	            {
	                worldIn.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
	                worldIn.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
	            }
	        }
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityAlloyFurnace();
	}

}

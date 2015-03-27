package calmbit.nucraft.power;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCarbonGenerator extends BlockContainer {
	private final boolean isActive;
	
	@SideOnly(Side.CLIENT)
	private IIcon carbonGeneratorFront;
	@SideOnly(Side.CLIENT)
	private IIcon carbonGeneratorSide;
	private static boolean isChanging;
	
	public BlockCarbonGenerator(boolean isActive) {
		super(Material.iron);
		setHarvestLevel("pickaxe", 2);
		setHardness(5.0f);
		setStepSound(Block.soundTypeMetal);
		setBlockName("carbonGenerator");
		this.isActive = isActive;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		carbonGeneratorSide = register.registerIcon("nucraftpower:carbonGeneratorSide");
		carbonGeneratorFront = this.isActive ? register.registerIcon("nucraftpower:active_carbonGeneratorFront") : register.registerIcon("nucraftpower:carbonGeneratorFront");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata == 0 && side == 3) return carbonGeneratorFront;
		return (side == metadata) ? carbonGeneratorFront : carbonGeneratorSide;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCarbonGenerator();
	}
	
	public static void updateCarbonGeneratorState(boolean burning, World worldIn, int bx, int by, int bz)
    {
        int l = worldIn.getBlockMetadata(bx, by, bz);
        TileEntity tileentity = worldIn.getTileEntity(bx, by, bz);
        isChanging = true;

        if (burning)
        {
            worldIn.setBlock(bx, by, bz, NuCraft.active_carbonGenerator);
        }
        else
        {
            worldIn.setBlock(bx, by, bz, NuCraft.carbonGenerator);
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
	public boolean onBlockActivated(World worldIn, int bx, int by, int bz, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote) return true;
		playerIn.openGui(NuCraft.instance, GuiHandlerCarbonGenerator.getGuiId(), worldIn, bx, by, bz);
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, int bx, int by, int bz, Block block, int meta)
	{
		if(!isChanging)
		{
			TileEntity tileEntity = worldIn.getTileEntity(bx, by, bz);
			if (tileEntity != null && tileEntity instanceof IInventory)
			{
				IInventory inventory = (IInventory)tileEntity;
				// For each slot in the inventory
				for (int i = 0; i < inventory.getSizeInventory(); i++)
				{
					// If the slot is not empty
					if (inventory.getStackInSlot(i) != null)
					{
						// Create a new entity item with the item stack in the slot
						EntityItem item = new EntityItem(worldIn, bx + 0.5, by + 0.5, bz + 0.5, inventory.getStackInSlot(i));
		
						// Apply some random motion to the item
						float multiplier = 0.1f;
						float motionX = worldIn.rand.nextFloat() - 0.5f;
						float motionY = worldIn.rand.nextFloat() - 0.5f;
						float motionZ = worldIn.rand.nextFloat() - 0.5f;
		
						item.motionX = motionX * multiplier;
						item.motionY = motionY * multiplier;
						item.motionZ = motionZ * multiplier;
		
						// Spawn the item in the world
						worldIn.spawnEntityInWorld(item);
						inventory.setInventorySlotContents(i, null);
					}
				}
			}
		}
		super.breakBlock(worldIn, bx, by, bz , block, meta);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, int bx, int by, int bz, EntityLivingBase placer, ItemStack stack)
    {
		int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            worldIn.setBlockMetadataWithNotify(bx, by, bz, 2, 2);
        }

        if (l == 1)
        {
            worldIn.setBlockMetadataWithNotify(bx, by, bz, 5, 2);
        }

        if (l == 2)
        {
            worldIn.setBlockMetadataWithNotify(bx, by, bz, 3, 2);
        }

        if (l == 3)
        {
            worldIn.setBlockMetadataWithNotify(bx, by, bz, 4, 2);
        }
        
		if(stack.hasDisplayName())
		{
			TileEntity tile = worldIn.getTileEntity(bx, by, bz);
			if(tile instanceof TileEntityCarbonGenerator)
			{
				((TileEntityCarbonGenerator)tile).setCustomInventoryName(stack.getDisplayName());
			}
		}
    }
	
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
}

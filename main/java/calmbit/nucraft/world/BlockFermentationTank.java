package calmbit.nucraft.world;

import calmbit.nucraft.core.NuCraft;
import calmbit.nucraft.power.GuiHandlerCompressor;
import calmbit.nucraft.power.TileEntityCompressor;
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

public class BlockFermentationTank extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon caskFront;
	@SideOnly(Side.CLIENT)
	private IIcon caskSide;
	
	public BlockFermentationTank() {
		super(Material.wood);
		this.setBlockName("fermentationTank");
		this.setStepSound(soundTypeMetal);
		this.setHardness(5.0f);
		this.setHarvestLevel("pickaxe", 2);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		caskFront = register.registerIcon("nucraftworld:fermentationTankFront");
		caskSide = register.registerIcon("nucraftworld:fermentationTankSide");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		if(metadata == 0 && side == 3) return caskFront;
		return (side == metadata) ? caskFront : caskSide;
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
			if(tile instanceof TileEntityFermentationTank)
			{
				((TileEntityFermentationTank)tile).setCustomInventoryName(stack.getDisplayName());
			}
		}
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityFermentationTank();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, int bx, int by, int bz, EntityPlayer playerIn, int side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote) return true;
		playerIn.openGui(NuCraft.instance, GuiHandlerFermentationTank.getGuiId(), worldIn, bx, by, bz);
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, int bx, int by, int bz, Block block, int meta)
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
		super.breakBlock(worldIn, bx, by, bz , block, meta);
	}

}

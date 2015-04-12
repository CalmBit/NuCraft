package calmbit.nucraft.world;

import java.awt.Color;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityFermentationTank extends TileEntity implements IInventory {

	private ItemStack[] tankInventory = new ItemStack[5];
	private String customTankName;
	public int fermentationTime;
	public int fermentationTimeTotal;
	/*
	 * WARNING: NBT cast to byte - 128+ at own risk.
	 */
	public int brewType;
	/*
	 * Number of bucket/glass units left.
	 */
	public int unitsLeft;
	
	public final static int BREWTYPE_NONE = 0;
	public final static int BREWTYPE_ALE = 1;
	public final static int BREWTYPE_LAGER = 2;
	public final static int BREWTYPE_CIDER = 3;
	public final static int BREWTYPE_HELL_CIDER = 4;
	
	@Override
	public int getSizeInventory() 
	{
		return 5;
	}
	
	public String getBrewTypeString()
	{
		switch(this.brewType)
		{
			default:
				return "None";
			case 1:
				return "Ale";
			case 2:
				return "Lager";
			case 3:
				return "Cider";
			case 4:
				return "Hell Cider";
		}
	}
	
	public int getBrewColor()
	{
		switch(this.brewType)
		{
			default:
				return Color.darkGray.getRGB();
			case 1:
				return 0xF79400;
			case 2:
				return 0x301800;
			case 3:
				return 0xDC8F32;
			case 4:
				return 0xF82C00;
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		if(slot < this.getSizeInventory()) return this.tankInventory[slot];
		else return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		ItemStack itemStack;
		if(this.tankInventory[index] != null)
		{
			if(this.tankInventory[index].stackSize <= count)
			{
				itemStack = this.tankInventory[index];
				this.tankInventory[index] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.tankInventory[index].splitStack(count);
				if(this.tankInventory[index].stackSize == 0)
				{
					this.tankInventory[index] = null;
				}
				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) 
	{
		if(this.tankInventory[index] != null)
		{
			ItemStack itemStack = this.tankInventory[index];
			this.tankInventory[index] = null;
			return itemStack;
		}
		else
		{
			return null;
		}
	}
	
	public boolean isFermenting()
	{
		return this.fermentationTime > 0;
	}
	
	public boolean hasBeer()
	{
		return this.brewType != 0 && this.unitsLeft == 0;
	}
	
	
	public double fractionofFermentationRemaining()
	{
		if(this.fermentationTimeTotal <= 0) return 0.0;
		else return MathHelper.clamp_double(((double)this.fermentationTime/(double)this.fermentationTimeTotal), 0.0, 1.0);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		this.tankInventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }	
		
	}

	@Override
	public String getInventoryName() 
	{
		return this.hasCustomInventoryName() ? this.customTankName : "container.fermentationTank";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return this.customTankName != null && !this.customTankName.isEmpty();
	}
	
	public void setCustomInventoryName(String name)
	{
		this.customTankName = name;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	public boolean getItemValidForFerment(ItemStack stack)
	{
		return stack.getItem() == NuCraft.hopsCropItem; 
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return getItemValidForFerment(stack);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("FermentTime", this.fermentationTime);
		compound.setInteger("FermentTimeTotal", this.fermentationTimeTotal);
		compound.setByte("BrewType", (byte)this.brewType);
		compound.setInteger("UnitsLeft", this.unitsLeft);
		NBTTagList items = new NBTTagList();
		for(int i = 0; i < this.tankInventory.length;i++)
		{
			if(this.tankInventory[i] != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				this.tankInventory[i].writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("Items", items);
		if(this.hasCustomInventoryName())
		{
			compound.setString("CustomName", this.customTankName);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.fermentationTime = compound.getInteger("FermentTime");
		this.fermentationTimeTotal = compound.getInteger("FermentTimeTotal");
		this.brewType = compound.getByte("BrewType");
		this.unitsLeft = compound.getInteger("UnitsLeft");
		NBTTagList items = compound.getTagList("Items", 10);
		for(int i = 0; i < items.tagCount();i++)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if(slot >= 0 && slot < this.getSizeInventory())
			{
				this.tankInventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
		if(compound.hasKey("CustomName", 8))
		{
			this.customTankName = compound.getString("CustomName");
		}
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}
	
	public boolean canFerment()
	{
		//Three Ingredient Beer
		if(this.tankInventory[0] != null && this.tankInventory[1] != null && this.tankInventory[2] != null)
		{
			boolean hops =  this.tankInventory[0].getItem() == NuCraft.hopsCropItem;
			int grain = this.tankInventory[1].getItem() == Items.wheat ? BREWTYPE_ALE : (this.tankInventory[1].getItem() == NuCraft.barleyCropItem ? BREWTYPE_LAGER : BREWTYPE_NONE);
			boolean yeast = this.tankInventory[2].getItem()  == NuCraft.yeastDust;
			boolean liquid = this.tankInventory[3].getItem() == Items.water_bucket;
			if(hops && grain != 0 && yeast && liquid)
			{
				if(this.brewType == BREWTYPE_NONE || this.brewType == grain)
				{	
					this.brewType = grain;
					this.markDirty();
					return true;
				}			
			}
		}
		//Two Ingredient Cider
		else if(this.tankInventory[0] != null && this.tankInventory[2] != null)
		{
			boolean apple = this.tankInventory[0].getItem() == Items.apple;
			boolean hellApple = this.tankInventory[0].getItem() == NuCraft.hellFruit;
			boolean sulfur = this.tankInventory[2].getItem() == Items.gunpowder;
			if(apple && sulfur)
			{
				if(this.brewType == BREWTYPE_NONE || this.brewType == BREWTYPE_CIDER)
				{
					this.brewType = BREWTYPE_CIDER;
					this.markDirty();
					return true;
				}
			}
			else if(hellApple && sulfur)
			{
				if(this.brewType == BREWTYPE_NONE || this.brewType == BREWTYPE_HELL_CIDER)
				{
					this.brewType = BREWTYPE_HELL_CIDER;
					this.markDirty();
					return true;
				}
			}
		}
		return false;
	}
	
	public void ferment()
	{
		this.unitsLeft++;
		/*if(this.unitsLeft != 0 && this.brewType != BREWTYPE_NONE && this.tankInventory[4] != null && this.tankInventory[4].getItem() == Items.glass_bottle && this.tankInventory[4].stackSize == 1)
		{
			this.tankInventory[4] = new ItemStack(NuCraft.bottleAlcohol, 1, this.brewType);
			this.unitsLeft--;
			if(this.unitsLeft == 0 && this.brewType != BREWTYPE_NONE && this.fermentationTime == 0) 
			{
				this.brewType = BREWTYPE_NONE;
			}
			this.markDirty();
		}*/
	}
	
	@Override
	public void updateEntity() 
	{
		boolean flag = false;
		if(this.fermentationTime > 0)
		{
			this.fermentationTime--;
			if(this.fermentationTime == 0 && !this.worldObj.isRemote)
			{
				this.ferment();
				flag = true;
			}
		}
		if(!this.worldObj.isRemote)
		{
			if(this.fermentationTime != 0 || this.canFerment()) 
			{
				if(this.fermentationTime == 0 && this.canFerment())
				{
					this.fermentationTimeTotal = this.fermentationTime = 1600;
					for(int i = 0; i < 4;i++)
					{
						if(this.tankInventory[i] != null) 
						{
							this.tankInventory[i].stackSize--;
							if(this.tankInventory[i].stackSize == 0) this.tankInventory[i] = this.tankInventory[i].getItem().getContainerItem(this.tankInventory[i]);
						}
					}
				}
			}
			if(this.unitsLeft != 0 && this.brewType != BREWTYPE_NONE && this.tankInventory[4] != null && this.tankInventory[4].getItem() == Items.glass_bottle && this.tankInventory[4].stackSize == 1)
			{
				this.tankInventory[4] = new ItemStack(NuCraft.bottleAlcohol, 1, this.brewType);
				this.unitsLeft--;
				if(this.unitsLeft == 0 && this.brewType != BREWTYPE_NONE && this.fermentationTime == 0) 
				{
					this.brewType = BREWTYPE_NONE;
				}
				this.markDirty();
			}
		}
		if(flag)
		{
			this.markDirty();
		}
	}

}

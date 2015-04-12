package calmbit.nucraft.power;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityCompressor extends TileEntity implements IInventory
{

	private ItemStack compressorInventory[] = new ItemStack[3];
	public int itemFuelTime;
	public int itemFuelTimeTotal;
	public int compressionTime;
	public int compressionTimeTotal;
	private String customGeneratorName;
	private boolean batteryOperated = false;
	
	
	
	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index < this.getSizeInventory() && index >= 0 ? this.compressorInventory[index] : null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemStack;
		if(this.compressorInventory[index] != null)
		{
			if(this.compressorInventory[index].stackSize <= count)
			{
				itemStack = this.compressorInventory[index];
				this.compressorInventory[index] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.compressorInventory[index].splitStack(count);
				if(this.compressorInventory[index].stackSize == 0)
				{
					this.compressorInventory[index] = null;
				}
				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}
	
	public boolean isCompressing()
	{
		return this.itemFuelTime > 0;
	}
	
	public double fractionOfFuelRemaining()
	{
		if(this.itemFuelTimeTotal <= 0) return 0.0;
		double fraction = (double)this.itemFuelTime / (double)this.itemFuelTimeTotal;
		//System.err.print("Fuel Time: " + itemFuelTime + "\nTotal Time: " + itemFuelTimeTotal + "\nDivision: " + fraction + "\n");
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}
	
	public double fractionOfCompressionRemaining()
	{
		double fraction = (double)this.compressionTime / (double)200;
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if(this.compressorInventory[index] != null)
		{
			ItemStack itemStack = this.compressorInventory[index];
			this.compressorInventory[index] = null;
			return itemStack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		//boolean flag = stack != null && stack.isItemEqual(this.generatorInventory) && ItemStack.areItemStackTagsEqual(stack, this.generatorInventory);
		this.compressorInventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }	
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
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

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		//TODO: Fix this.
		switch(index)
		{
			case 0:
				return false;
			case 1:
				return isItemFuel(stack);
			case 2:
				return false;
			default:
				return false;
				
		}
	}
	
	public void setCustomInventoryName(String customName)
	{
		customGeneratorName = customName;
	}
	
	 public static int getItemBurnTime(ItemStack itemStack_)
	    {
	        if (itemStack_ == null)
	        {
	            return 0;
	        }
	        else
	        {
	            Item item = itemStack_.getItem();

	            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
	            {
	                Block block = Block.getBlockFromItem(item);

	                if (block == Blocks.wooden_slab)
	                {
	                    return 150;
	                }

	                if (block.getMaterial() == Material.wood)
	                {
	                    return 300;
	                }

	                if (block == Blocks.coal_block)
	                {
	                    return 16000;
	                }
	            }

	            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
	            if (item == Items.stick) return 100;
	            if (item == Items.coal) return 1600;
	            if (item == Items.lava_bucket) return 20000;
	            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
	            if (item == Items.blaze_rod) return 2400;
	            if (item instanceof ItemBatteryBase)
	            {
	            	int damage = ((ItemBatteryBase)item).getmAh() - ((ItemBatteryBase)item).getDamage(itemStack_);
	            	return damage;
	            }
	            return GameRegistry.getFuelValue(itemStack_);
	        }
	    }

	    public static boolean isItemFuel(ItemStack itemStack)
	    {
	        return getItemBurnTime(itemStack) > 0;
	    }
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList itemList = compound.getTagList("Items", 10);
		this.compressorInventory = new ItemStack[this.getSizeInventory()];
		for(int i = 0; i < itemList.tagCount();i++)
		{
			NBTTagCompound nbtCompound = itemList.getCompoundTagAt(i);
			byte slot = nbtCompound.getByte("Slot");
			if(slot >= 0 && slot < this.getSizeInventory())
			{
				this.compressorInventory[slot] = ItemStack.loadItemStackFromNBT(nbtCompound);
			}
		}
		
		this.itemFuelTime = compound.getShort("BurnTime");
		this.itemFuelTimeTotal = compound.getShort("BurnTimeTotal");
		this.compressionTime = compound.getShort("CompressionTime");
		this.compressionTimeTotal = compound.getShort("CompressionTimeTotal");
		this.batteryOperated = compound.getBoolean("BatteryOperated");
		if(compound.hasKey("CustomName", 8))
		{
			this.customGeneratorName = compound.getString("CustomName");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short) itemFuelTime);
		compound.setShort("BurnTimeTotal", (short) itemFuelTimeTotal);
		compound.setShort("CompressionTime", (short)compressionTime);
		compound.setShort("CompressionTimeTotal", (short)compressionTimeTotal);
		compound.setBoolean("BatteryOperated", batteryOperated);
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < this.compressorInventory.length;i++)
		{
			if(compressorInventory[i] != null)
			{
				NBTTagCompound itemStack = new NBTTagCompound();
				itemStack.setByte("Slot", (byte)i);
				compressorInventory[i].writeToNBT(itemStack);
				itemList.appendTag(itemStack);
			}
		}
		
		
		compound.setTag("Items", itemList);
		
		if(this.hasCustomInventoryName())
		{
			compound.setString("CustomName", customGeneratorName);
		}
	}
	
	public boolean canCompress()
	{
		if (this.compressorInventory[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = CompressorRecipes.compressor().GetCompressionResult(this.compressorInventory[0]);
            if (itemstack == null) return false;
            if (this.compressorInventory[2] == null) return true;
            if (!this.compressorInventory[2].isItemEqual(itemstack)) return false;
            int result = compressorInventory[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.compressorInventory[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
	}

	@Override
	public void updateEntity() 
	{
		boolean flag = this.itemFuelTime > 0;
        boolean flag1 = false;

        if (this.itemFuelTime > 0)
        {
            --this.itemFuelTime;
            if(this.compressorInventory[1] != null && this.compressorInventory[1].getItem() instanceof ItemBatteryBase && this.compressorInventory[1].getItemDamage() < ((ItemBatteryBase)this.compressorInventory[1].getItem()).getmAh())
            {
            	this.compressorInventory[1].setItemDamage(this.compressorInventory[1].getItemDamage() + 1);
            }
            else if(this.batteryOperated)
            {
            	this.batteryOperated = false;
            	this.itemFuelTime = 0;
            	this.itemFuelTimeTotal = 0;
            }
        }

        if (!this.worldObj.isRemote)
        {
            if (this.itemFuelTime != 0 || this.compressorInventory[1] != null && this.compressorInventory[0] != null)
            {
                if (this.itemFuelTime == 0 && this.canCompress())
                {
                    this.itemFuelTimeTotal = this.itemFuelTime = getItemBurnTime(this.compressorInventory[1]);

                    if (this.itemFuelTime > 0)
                    {
                        flag1 = true;

                        if (this.compressorInventory[1] != null)
                        {
                        	if(this.compressorInventory[1].getItem() instanceof ItemBatteryBase)
                        	{
                        		this.batteryOperated = true;
                        	}
                        	else
                        	{
	                            --this.compressorInventory[1].stackSize;
	
	                            if (this.compressorInventory[1].stackSize == 0)
	                            {
	                                this.compressorInventory[1] = compressorInventory[1].getItem().getContainerItem(compressorInventory[1]);
	                            }
                        	}
                        }
                    }
                }

                if (this.isCompressing() && this.canCompress())
                {
                    ++this.compressionTime;

                    if (this.compressionTime == 200)
                    {
                        this.compressionTime = 0;
                        this.compressItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.compressionTime = 0;
                }
            }

            if (flag != this.itemFuelTime > 0)
            {
                flag1 = true;
                BlockCompressor.updateCompressorState(this.itemFuelTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? this.customGeneratorName : "container.compressor";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customGeneratorName != null && !this.customGeneratorName.isEmpty();
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
	
	public void compressItem()
    {
        if (this.canCompress())
        {
        	if(this.compressorInventory[0].getItem() == NuCraft.riftMatter)
        	{
        		this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 3.0f, true);
        	}
        	else
        	{
	            ItemStack itemstack = CompressorRecipes.compressor().GetCompressionResult(this.compressorInventory[0]);
	
	            if (this.compressorInventory[2] == null)
	            {
	                this.compressorInventory[2] = itemstack.copy();
	            }
	            else if (this.compressorInventory[2].getItem() == itemstack.getItem())
	            {
	                this.compressorInventory[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
	            }
	
	            --this.compressorInventory[0].stackSize;
	
	            if (this.compressorInventory[0].stackSize <= 0)
	            {
	                this.compressorInventory[0] = null;
	            }
	        }
        }
    }

}

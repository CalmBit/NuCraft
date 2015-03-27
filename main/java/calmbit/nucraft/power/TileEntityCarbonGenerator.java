package calmbit.nucraft.power;

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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public class TileEntityCarbonGenerator extends TileEntity implements IInventory 
{

	private ItemStack generatorInventory;
	public int itemFuelTime;
	public int itemFuelTimeTotal;
	private String customGeneratorName;
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.generatorInventory;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemStack;
		if(this.generatorInventory != null)
		{
			if(this.generatorInventory.stackSize <= count)
			{
				itemStack = this.generatorInventory;
				this.generatorInventory = null;
				return itemStack;
			}
			else
			{
				itemStack = this.generatorInventory.splitStack(count);
				if(this.generatorInventory.stackSize == 0)
				{
					this.generatorInventory = null;
				}
				return itemStack;
			}
		}
		else
		{
			return null;
		}
	}
	
	public double fractionOfFuelRemaining()
	{
		if(this.itemFuelTimeTotal <= 0) return 0.0;
		double fraction = (double)this.itemFuelTime / (double)this.itemFuelTimeTotal;
		//System.err.print("Fuel Time: " + itemFuelTime + "\nTotal Time: " + itemFuelTimeTotal + "\nDivision: " + fraction + "\n");
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if(this.generatorInventory != null)
		{
			ItemStack itemStack = this.generatorInventory;
			this.generatorInventory = null;
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
		this.generatorInventory = stack;

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
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return isItemFuel(stack);
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
		if(itemList.tagCount() >= 1) 
		{
			NBTTagCompound itemStack = itemList.getCompoundTagAt(0);
			this.generatorInventory = ItemStack.loadItemStackFromNBT(itemStack);
		}
		
		this.itemFuelTime = compound.getShort("BurnTime");
		this.itemFuelTimeTotal = compound.getShort("BurnTimeTotal");
		//System.err.print("This generator should have a stack of " + ((generatorInventory != null) ? generatorInventory.toString() : "nothing") + " read\n");
		if(compound.hasKey("CustomName"))
		{
			this.customGeneratorName = compound.getString("CustomName");
		}
		//System.err.print("Hey, i just read from NBT!");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short) itemFuelTime);
		compound.setShort("BurnTimeTotal", (short) itemFuelTimeTotal);
		NBTTagList itemList = new NBTTagList();
		NBTTagCompound itemStack = new NBTTagCompound();
		if(generatorInventory != null) generatorInventory.writeToNBT(itemStack);
		itemList.appendTag(itemStack);
		compound.setTag("Items", itemList);
		//System.err.print("This generator should have a stack of " + ((generatorInventory != null) ? generatorInventory.toString() : "nothing") + " written\n");
		if(this.hasCustomInventoryName())
		{
			compound.setString("CustomName", customGeneratorName);
		}
		//System.err.print("Hey, i just wrote to NBT!");
	}

	@Override
	public void updateEntity() 
	{
		boolean flag = this.itemFuelTime > 0;
		boolean flag1 = false;
		if(this.itemFuelTime > 0) --this.itemFuelTime;
		if(this.itemFuelTime == 0)
		{
			if(generatorInventory != null && getItemBurnTime(generatorInventory) > 0)
			{
				itemFuelTime = itemFuelTimeTotal = getItemBurnTime(generatorInventory);
				--generatorInventory.stackSize;
				flag1 = true;
				if(generatorInventory.stackSize == 0) generatorInventory = generatorInventory.getItem().getContainerItem(generatorInventory);
			}
		}
		if (flag != this.itemFuelTime > 0)
        {
            flag1 = true;
            BlockCarbonGenerator.updateCarbonGeneratorState(this.itemFuelTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
		if(flag1) markDirty();
	}

	@Override
	public String getInventoryName() {
		return hasCustomInventoryName() ? this.customGeneratorName : "container.carbonGenerator";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customGeneratorName != null && !this.customGeneratorName.isEmpty();
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

}

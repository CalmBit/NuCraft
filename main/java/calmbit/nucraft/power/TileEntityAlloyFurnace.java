package calmbit.nucraft.power;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityAlloyFurnace extends TileEntity implements IInventory 
{
	public ItemStack[] alloyFurnaceInventory = new ItemStack[4];
	public int itemFuelTime;
	public int itemFuelTimeTotal;
	public int alloyTime;
	public int alloyTimeTotal;
	public String customAlloyFurnaceName;
	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot < getSizeInventory() && slot >= 0 ? alloyFurnaceInventory[slot] : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack itemStack;
		if(this.alloyFurnaceInventory[slot] != null)
		{
			if(this.alloyFurnaceInventory[slot].stackSize <= count)
			{
				itemStack = this.alloyFurnaceInventory[slot];
				this.alloyFurnaceInventory[slot] = null;
			}
			else
			{
				itemStack = this.alloyFurnaceInventory[slot].splitStack(count);
				if(this.alloyFurnaceInventory[slot].stackSize == 0) this.alloyFurnaceInventory[slot] = null;
			}
			return itemStack;
		}
		else return null;
	}
	
	public boolean isBurning()
	{
		return this.itemFuelTime > 0;
	}
	
	public double fractionOfFuelRemaining()
	{
		if(this.itemFuelTimeTotal <= 0) return 0.0;
		double fraction = (double)this.itemFuelTime / (double)this.itemFuelTimeTotal;
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}
	
	public double fractionOfAlloyRemaining()
	{
		double fraction = (double)this.alloyTime / (double)400;
		return MathHelper.clamp_double(fraction, 0.0, 1.0);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		if(this.alloyFurnaceInventory[slot] != null)
		{
			ItemStack itemStack = this.alloyFurnaceInventory[slot];
			this.alloyFurnaceInventory[slot] = null;
			return itemStack;
		}
		else return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		this.alloyFurnaceInventory[slot] = stack;
		if(stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? customAlloyFurnaceName : "container.alloyFurnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customAlloyFurnaceName != null && !this.customAlloyFurnaceName.isEmpty();
	}

	@Override
	public int getInventoryStackLimit() {
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

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch(slot)
		{
		case 0:
			return this.isItemFuel(stack);
		case 1:
			return true;
		case 2:
			return true;
		case 3:
			return false;
		default:
			return false;
		}
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
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		NBTTagList itemList = compound.getTagList("Items", 10);
		this.alloyFurnaceInventory = new ItemStack[this.getSizeInventory()];
		for(int i = 0; i < itemList.tagCount();i++)
		{
			NBTTagCompound nbtCompound = itemList.getCompoundTagAt(i);
			byte slot = nbtCompound.getByte("Slot");
			if(slot >= 0 && slot < this.getSizeInventory())
			{
				this.alloyFurnaceInventory[slot] = ItemStack.loadItemStackFromNBT(nbtCompound);
			}
		}
		
		this.itemFuelTime = compound.getShort("BurnTime");
		this.itemFuelTimeTotal = compound.getShort("BurnTimeTotal");
		this.alloyTime = compound.getShort("AlloyTime");
		this.alloyTimeTotal = compound.getShort("AlloyTimeTotal");
		if(compound.hasKey("CustomName", 8))
		{
			this.customAlloyFurnaceName = compound.getString("CustomName");
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setShort("BurnTime", (short) itemFuelTime);
		compound.setShort("BurnTimeTotal", (short) itemFuelTimeTotal);
		compound.setShort("AlloyTime", (short)alloyTime);
		compound.setShort("AlloyTimeTotal", (short)alloyTimeTotal);
		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < this.alloyFurnaceInventory.length;i++)
		{
			if(alloyFurnaceInventory[i] != null)
			{
				NBTTagCompound itemStack = new NBTTagCompound();
				itemStack.setByte("Slot", (byte)i);
				alloyFurnaceInventory[i].writeToNBT(itemStack);
				itemList.appendTag(itemStack);
			}
		}
		
		
		compound.setTag("Items", itemList);
		
		if(this.hasCustomInventoryName())
		{
			compound.setString("CustomName", customAlloyFurnaceName);
		}
	}
	
	public boolean canAlloy()
	{
		if (this.alloyFurnaceInventory[1] == null || this.alloyFurnaceInventory[2] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = AlloyFurnaceRecipes.alloyFurnace().GetAlloyResult(this.alloyFurnaceInventory[1], this.alloyFurnaceInventory[2]);
            if (itemstack == null) return false;
            if (this.alloyFurnaceInventory[3] == null) return true;
            if (!this.alloyFurnaceInventory[3].isItemEqual(itemstack)) return false;
            int result = alloyFurnaceInventory[3].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.alloyFurnaceInventory[3].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
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
        }

        if (!this.worldObj.isRemote)
        {
            if (this.itemFuelTime != 0 || (this.alloyFurnaceInventory[2] != null && this.alloyFurnaceInventory[1] != null && this.alloyFurnaceInventory[0] != null))
            {
                if (this.itemFuelTime == 0 && this.canAlloy())
                {
                    this.itemFuelTimeTotal = this.itemFuelTime = getItemBurnTime(this.alloyFurnaceInventory[0]);

                    if (this.itemFuelTime > 0)
                    {
                        flag1 = true;

                        if (this.alloyFurnaceInventory[0] != null)
                        {
                            --this.alloyFurnaceInventory[0].stackSize;

                            if (this.alloyFurnaceInventory[0].stackSize == 0)
                            {
                                this.alloyFurnaceInventory[0] = alloyFurnaceInventory[0].getItem().getContainerItem(alloyFurnaceInventory[0]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canAlloy())
                {
                    ++this.alloyTime;
                    if (this.alloyTime == 400)
                    {
                        this.alloyTime = 0;
                        this.alloyItems();
                        flag1 = true;
                    }
                }
                else
                {
                    this.alloyTime = 0;
                }
            }

            if (flag != this.itemFuelTime > 0)
            {
                flag1 = true;
                BlockAlloyFurnace.updateAlloyFurnaceState(this.itemFuelTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
	}
	
	public void alloyItems()
    {
        if (this.canAlloy())
        {
            ItemStack itemstack = AlloyFurnaceRecipes.alloyFurnace().GetAlloyResult(this.alloyFurnaceInventory[1], this.alloyFurnaceInventory[2]);

            if (this.alloyFurnaceInventory[3] == null)
            {
                this.alloyFurnaceInventory[3] = itemstack.copy();
            }
            else if (this.alloyFurnaceInventory[3].getItem() == itemstack.getItem())
            {
                this.alloyFurnaceInventory[3].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.alloyFurnaceInventory[1].stackSize;

            if (this.alloyFurnaceInventory[1].stackSize <= 0)
            {
                this.alloyFurnaceInventory[1] = null;
            }
            
            --this.alloyFurnaceInventory[2].stackSize;

            if (this.alloyFurnaceInventory[2].stackSize <= 0)
            {
                this.alloyFurnaceInventory[2] = null;
            }
        }
    }

	public void setCustomInventoryName(String displayName) 
	{
		this.customAlloyFurnaceName = displayName;
	}
}

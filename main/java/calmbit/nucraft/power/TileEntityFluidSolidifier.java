package calmbit.nucraft.power;

import com.sun.imageio.plugins.common.I18N;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.TileFluidHandler;

public class TileEntityFluidSolidifier extends TileFluidHandler implements IInventory {

	private ItemStack[] fluidSolidifierInventory = new ItemStack[2];
	private String customFluidSolidifierName;
	public int solidifyTime;
	
    public TileEntityFluidSolidifier() {
		super();
		this.tank.setCapacity(16000);
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot < this.getSizeInventory() ? fluidSolidifierInventory[slot] : null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int num) {
		ItemStack itemStack;
		if(this.fluidSolidifierInventory[slot] != null)
		{
			if(this.fluidSolidifierInventory[slot].stackSize <= num)
			{
				itemStack = this.fluidSolidifierInventory[slot];
				this.fluidSolidifierInventory[slot] = null;
				return itemStack;
			}
			else
			{
				itemStack = this.fluidSolidifierInventory[slot].splitStack(num);
				if(this.fluidSolidifierInventory[slot].stackSize == 0)
				{
					this.fluidSolidifierInventory[slot] = null;
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
	public ItemStack getStackInSlotOnClosing(int slot) {
		if(slot < this.getSizeInventory() && this.fluidSolidifierInventory[slot] != null)
		{
			ItemStack itemStack;
			itemStack = this.fluidSolidifierInventory[slot];
			this.fluidSolidifierInventory[slot] = null;
			return itemStack;
		}
		else return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if(slot < this.getSizeInventory())
		{
			this.fluidSolidifierInventory[slot] = itemStack;
			if(itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
			{
				itemStack.stackSize = this.getInventoryStackLimit();
			}
		}
		
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.customFluidSolidifierName : "container.fluidSolidifier";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customFluidSolidifierName != null && !this.customFluidSolidifierName.isEmpty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public FluidStack getTankFluidStack()
	{
		return this.getTankInfo(null)[0].fluid;
	}
	
	public double getFractionOfTank()
	{
		double capacity = this.getTankInfo(null)[0].capacity;
		double current = this.getTankFluidStack().amount;
		
		return capacity == 0 ? 0 : current/capacity;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
		switch(slot)
		{
			default:
				return false;
			case 1:
				return itemStack.getItem().getContainerItem() != null;
		}
	}

	public void setCustomInventoryName(String displayName) 
	{
		this.customFluidSolidifierName = displayName;
	}
	
	@Override
	public void updateEntity() 
	{
		if(fluidSolidifierInventory[1] != null && fluidSolidifierInventory[1].getItem().getContainerItem() != null)
		{
			FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(fluidSolidifierInventory[1]);
			if(fluidStack == null) return;
			if(!fluidStack.equals(tank.getFluid()) && tank.getFluid() != null) return;
			if(this.fill(null, fluidStack, true) != 0) fluidSolidifierInventory[1] = fluidSolidifierInventory[1].getItem().getContainerItem(fluidSolidifierInventory[1]);
		}
	}

}

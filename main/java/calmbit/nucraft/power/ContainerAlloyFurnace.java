package calmbit.nucraft.power;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import calmbit.nucraft.power.ContainerCompressor.SlotFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerAlloyFurnace extends Container {

	private TileEntityAlloyFurnace tileEntityAlloyFurnace;
	private int lastFuelTime;
	private int lastFuelTotalTime;
	private int lastAlloyTime;
	
	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_ROW_COUNT = 3;
	private final int PLAYER_COLUMN_COUNT = 9;
	private final int PLAYER_INVENTORY_COUNT = PLAYER_ROW_COUNT * PLAYER_COLUMN_COUNT;
	private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_COUNT;
	private final int FUEL_SLOT_POS = VANILLA_SLOT_COUNT;
	private final int MATERIAL_SLOT_ONE = FUEL_SLOT_POS + 1;
	private final int MATERIAL_SLOT_TWO = FUEL_SLOT_POS + 2;
	private final int RESULT_SLOT_POS = MATERIAL_SLOT_TWO + 1;
	
	public ContainerAlloyFurnace(InventoryPlayer player, TileEntityAlloyFurnace tileEntityAlloyFurnace) 
	{
		this.tileEntityAlloyFurnace = tileEntityAlloyFurnace;
		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 8;
		final int HOTBAR_YPOS = 142;
		for(int i = 0; i < HOTBAR_SLOT_COUNT;i++)
		{
			addSlotToContainer(new Slot(player, i, HOTBAR_XPOS + SLOT_X_SPACING * i, HOTBAR_YPOS));
		}
		
		final int PLAYER_INVENTORY_XPOS = 8;
		final int PLAYER_INVENTORY_YPOS = 84;
		for(int i = 0; i < PLAYER_ROW_COUNT;i++)
		{
			for(int j = 0; j < PLAYER_COLUMN_COUNT;j++)
			{
				int slotNumber = HOTBAR_SLOT_COUNT + i * PLAYER_COLUMN_COUNT + j;
				int xpos = PLAYER_INVENTORY_XPOS + j * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + i * SLOT_Y_SPACING;
				addSlotToContainer(new Slot(player, slotNumber, xpos, ypos));
			}
		}
		
		addSlotToContainer(new Slot(this.tileEntityAlloyFurnace, 0, 42, 34));
		addSlotToContainer(new Slot(this.tileEntityAlloyFurnace, 1, 78, 7 ));
		addSlotToContainer(new Slot(this.tileEntityAlloyFurnace, 2, 113, 7 ));
		addSlotToContainer(new SlotFurnace(player.player, this.tileEntityAlloyFurnace, 3, 97, 57));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntityAlloyFurnace.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int sourceSlotIndex)
	{
		//TODO Stack overflow? No time or effort avalible to debug why this isn't working - probably that mergeItemStack stuff.
		/*Slot sourceSlot = (Slot)inventorySlots.get(sourceSlotIndex);
		if(sourceSlot == null || !sourceSlot.getHasStack()) return null;
		ItemStack sourceStack = sourceSlot.getStack();
		ItemStack copyOfSourceStack = sourceStack.copy();
		if(sourceSlotIndex >= 0 && sourceSlotIndex < VANILLA_SLOT_COUNT)
		{
			if(TileEntityCarbonGenerator.getItemBurnTime(sourceStack) > 0) 
				if(!mergeItemStack(sourceStack, VANILLA_SLOT_COUNT, VANILLA_SLOT_COUNT, true)) 
					return null;
			
			else return null;
		}
		else if(sourceSlotIndex == VANILLA_SLOT_COUNT)
		{
			if(!mergeItemStack(sourceStack,0,VANILLA_SLOT_COUNT,false))
				return null;
		}
		else
		{
			System.err.print("Invalid slotindex: " + sourceSlotIndex);
			return null;
		}
		
		if(sourceStack.stackSize == 0) sourceSlot.putStack(null);
		else sourceSlot.onSlotChanged();
		sourceSlot.onPickupFromSlot(player, sourceStack);
		return copyOfSourceStack;*/
		return null;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		if(lastAlloyTime != tileEntityAlloyFurnace.alloyTime)
		{
			lastAlloyTime = tileEntityAlloyFurnace.alloyTime;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 0, lastAlloyTime);
			}
		}
		
		if(lastFuelTime != tileEntityAlloyFurnace.itemFuelTime)
		{
			lastFuelTime = tileEntityAlloyFurnace.itemFuelTime;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 1, lastFuelTime);
			}
		}
		
		if(lastFuelTotalTime != tileEntityAlloyFurnace.itemFuelTimeTotal)
		{
			lastFuelTotalTime = tileEntityAlloyFurnace.itemFuelTimeTotal;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 2, lastFuelTotalTime);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data)
	{
		switch(id)
		{
			case 0:
				tileEntityAlloyFurnace.alloyTime = data;
				break;
			case 1:
				tileEntityAlloyFurnace.itemFuelTime = data;
				break;
			case 2:
				tileEntityAlloyFurnace.itemFuelTimeTotal = data;
				break;
		}
	}
	
	public class SlotFuel extends Slot
	{

		public SlotFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}
		
		@Override
		public boolean isItemValid(ItemStack stack)
		{
			return TileEntityAlloyFurnace.isItemFuel(stack);
		}
		
	}

}

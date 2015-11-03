package calmbit.nucraft.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import calmbit.nucraft.core.NuCraft;
import calmbit.nucraft.power.TileEntityCompressor;
import calmbit.nucraft.power.ContainerCompressor.SlotFuel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerFermentationTank extends Container
{

	private TileEntityFermentationTank tileEntityFermentationTank;
	private int lastFermentationTime;
	private int lastFermentationTimeTotal;
	private int lastBrewType;
	private int lastUnitCount;
	
	private final int HOTBAR_SLOT_COUNT = 9;
	private final int PLAYER_ROW_COUNT = 3;
	private final int PLAYER_COLUMN_COUNT = 9;
	private final int PLAYER_INVENTORY_COUNT = PLAYER_ROW_COUNT * PLAYER_COLUMN_COUNT;
	private final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_COUNT;
	
	public ContainerFermentationTank(InventoryPlayer player, TileEntityFermentationTank fermentationTank)
	{
		this.tileEntityFermentationTank = fermentationTank;
		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		final int HOTBAR_XPOS = 8;
		final int HOTBAR_YPOS = 142;
		
		addSlotToContainer(new Slot(this.tileEntityFermentationTank, 0, 58, 29 ));
		addSlotToContainer(new Slot(this.tileEntityFermentationTank, 1, 86, 29));
		addSlotToContainer(new Slot(this.tileEntityFermentationTank, 2, 114, 29));
		addSlotToContainer(new Slot(this.tileEntityFermentationTank, 3, 20, 55));
		addSlotToContainer(new Slot(this.tileEntityFermentationTank, 4, 140, 55));
		
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
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntityFermentationTank.isUseableByPlayer(playerIn);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(p_82846_2_);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (p_82846_2_ == 4)
            {
                if (!this.mergeItemStack(itemstack1, 5, 41, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (p_82846_2_ != 3 && p_82846_2_ != 2 && p_82846_2_ != 1 && p_82846_2_ != 0)
            {
                if (itemstack1.getItem() == NuCraft.hopsCropItem)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() == NuCraft.barleyCropItem || itemstack1.getItem() == Items.wheat)
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() == NuCraft.yeastDust)
                {
                    if (!this.mergeItemStack(itemstack1, 2, 3, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() == Items.water_bucket)
                {
                    if (!this.mergeItemStack(itemstack1, 3, 4, false))
                    {
                        return null;
                    }
                }
                else if (itemstack1.getItem() == NuCraft.glassStein && ((Slot)this.inventorySlots.get(4)).getStack() == null)
                {
                    if (!this.mergeItemStack(itemstack1, 4, 5, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 5 && p_82846_2_ < 32)
                {
                    if (!this.mergeItemStack(itemstack1, 32, 41, false))
                    {
                        return null;
                    }
                }
                else if (p_82846_2_ >= 32 && p_82846_2_ < 41 && !this.mergeItemStack(itemstack1, 5, 32, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 5, 41, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(p_82846_1_, itemstack1);
        }

        return itemstack;
    }
	
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		if(lastFermentationTime != tileEntityFermentationTank.fermentationTime)
		{
			lastFermentationTime  = tileEntityFermentationTank.fermentationTime;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 0, lastFermentationTime);
			}
		}
		
		if(lastFermentationTimeTotal != tileEntityFermentationTank.fermentationTimeTotal)
		{
			lastFermentationTimeTotal  = tileEntityFermentationTank.fermentationTimeTotal;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 1, lastFermentationTimeTotal);
			}
		}
		
		if(lastBrewType != tileEntityFermentationTank.brewType)
		{
			lastBrewType  = tileEntityFermentationTank.brewType;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 2, lastBrewType);
			}
		}
		if(lastUnitCount != tileEntityFermentationTank.unitsLeft)
		{
			lastUnitCount  = tileEntityFermentationTank.unitsLeft;
			for(int i = 0; i < this.crafters.size();i++)
			{
				ICrafting icrafting = (ICrafting)this.crafters.get(i);
				icrafting.sendProgressBarUpdate(this, 3, lastUnitCount);
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
				tileEntityFermentationTank.fermentationTime = data;
				break;
			case 1:
				tileEntityFermentationTank.fermentationTimeTotal = data;
				break;
			case 2:
				tileEntityFermentationTank.brewType = data;
				break;
			case 3:
				tileEntityFermentationTank.unitsLeft = data;
				break;
		}
	}

}

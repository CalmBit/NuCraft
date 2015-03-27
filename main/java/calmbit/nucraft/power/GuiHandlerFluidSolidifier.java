package calmbit.nucraft.power;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerFluidSolidifier implements IGuiHandler {
	private static final int GUIID = 53;
	public static int getGuiId() {return GUIID;}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityFluidSolidifier)
		{
			TileEntityFluidSolidifier fluidSolidifier = (TileEntityFluidSolidifier)tileEntity;
			return new ContainerFluidSolidifier(player.inventory, fluidSolidifier);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityFluidSolidifier)
		{
			TileEntityFluidSolidifier fluidSolidifier = (TileEntityFluidSolidifier)tileEntity;
			return new GuiFluidSolidifier(player.inventory, fluidSolidifier);
		}
		return null;
	}

}

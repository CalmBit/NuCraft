package calmbit.nucraft.world;

import calmbit.nucraft.power.ContainerCompressor;
import calmbit.nucraft.power.GuiCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandlerFermentationTank implements IGuiHandler {

	private static final int GUIID = 52;
	public static int getGuiId() {return GUIID;}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityFermentationTank)
		{
			TileEntityFermentationTank fermentationTank = (TileEntityFermentationTank)tileEntity;
			return new ContainerFermentationTank(player.inventory, fermentationTank);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityFermentationTank)
		{
			TileEntityFermentationTank fermentationTank = (TileEntityFermentationTank)tileEntity;
			return new GuiFermentationTank(player.inventory, fermentationTank);
		}
		return null;
	}

}

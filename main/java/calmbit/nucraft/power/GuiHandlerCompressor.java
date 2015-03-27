package calmbit.nucraft.power;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlerCompressor implements IGuiHandler {

	private static final int GUIID = 51;
	public static int getGuiId() {return GUIID;}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityCompressor)
		{
			TileEntityCompressor compressor = (TileEntityCompressor)tileEntity;
			return new ContainerCompressor(player.inventory, compressor);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityCompressor)
		{
			TileEntityCompressor compressor = (TileEntityCompressor)tileEntity;
			return new GuiCompressor(player.inventory, compressor);
		}
		return null;
	}

}

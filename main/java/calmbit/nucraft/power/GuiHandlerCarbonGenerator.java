package calmbit.nucraft.power;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlerCarbonGenerator implements IGuiHandler {

	private static final int GUIID = 50;
	public static int getGuiId() {return GUIID;}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityCarbonGenerator)
		{
			TileEntityCarbonGenerator carbonGenerator = (TileEntityCarbonGenerator)tileEntity;
			return new ContainerCarbonGenerator(player.inventory, carbonGenerator);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID != getGuiId()) System.err.print("Invaid ID "+ ID + " - expected " + getGuiId());
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityCarbonGenerator)
		{
			TileEntityCarbonGenerator carbonGenerator = (TileEntityCarbonGenerator)tileEntity;
			return new GuiCarbonGenerator(player.inventory, carbonGenerator);
		}
		return null;
	}

}

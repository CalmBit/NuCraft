package calmbit.nucraft.power;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiAlloyFurnace extends GuiContainer 
{

	private static final ResourceLocation guiTexture = new ResourceLocation("nucraftpower" , "textures/gui/container/alloyFurnace.png");
	private TileEntityAlloyFurnace tileEntity;
	
	final int FLAME_XPOS = 69;
	final int FLAME_YPOS = 36;
	final int FLAME_UVX = 176;
	final int FLAME_UVY = 0;
	final int FLAME_HEIGHT = 14;
	final int FLAME_WIDTH = 14;
	
	final int ARROW_XPOS = 84;
	final int ARROW_YPOS = 25;
	final int ARROW_UVX = 176;
	final int ARROW_UVY = 14;
	final int ARROW_HEIGHT = 25;
	final int ARROW_WIDTH = 41;
	
	public GuiAlloyFurnace(InventoryPlayer player, TileEntityAlloyFurnace tileEntityAlloyFurnace) 
	{
		super(new ContainerAlloyFurnace(player, tileEntityAlloyFurnace));
		xSize = 176;
		ySize = 167;
		this.tileEntity = tileEntityAlloyFurnace;
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		double burnRemaining = tileEntity.fractionOfFuelRemaining();
		int yOffsetFlame = (int)((1.0 - burnRemaining) * FLAME_HEIGHT);
		drawTexturedModalRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS + yOffsetFlame, FLAME_UVX, FLAME_UVY + yOffsetFlame, FLAME_WIDTH, FLAME_HEIGHT - yOffsetFlame);
		double compressRemaining = tileEntity.fractionOfAlloyRemaining();
		int yOffsetArrow = (int)((compressRemaining) * ARROW_HEIGHT);
		drawTexturedModalRect(guiLeft + ARROW_XPOS, guiTop + ARROW_YPOS, ARROW_UVX, ARROW_UVY, ARROW_WIDTH, yOffsetArrow);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		
		fontRendererObj.drawString(tileEntity.hasCustomInventoryName() ? tileEntity.getInventoryName() : I18n.format(tileEntity.getInventoryName(), new Object[0]), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
	}

}

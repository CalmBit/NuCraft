package calmbit.nucraft.world;

import java.awt.Color;

import calmbit.nucraft.power.TileEntityCompressor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiFermentationTank extends GuiContainer 
{

	private static final ResourceLocation guiTexture = new ResourceLocation("nucraftworld" , "textures/gui/container/fermentationTank.png");
	private TileEntityFermentationTank tileEntity;
	
	final int BUBBLES_XPOS = 87;
	final int BUBBLES_YPOS = 48;
	final int BUBBLES_UVX = 176;
	final int BUBBLES_UVY = 0;
	final int BUBBLES_HEIGHT = 29;
	final int BUBBLES_WIDTH = 12;
	
	public GuiFermentationTank(InventoryPlayer player, TileEntityFermentationTank fermentationTank) {
		super(new ContainerFermentationTank(player, fermentationTank));

		xSize = 176;
		ySize = 167;
		
		this.tileEntity = fermentationTank;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		double burnRemaining = tileEntity.fractionofFermentationRemaining();
		int yOffsetFlame = (int)((1.0 - burnRemaining) * BUBBLES_HEIGHT);
		drawTexturedModalRect(guiLeft + BUBBLES_XPOS, guiTop + BUBBLES_YPOS + yOffsetFlame, BUBBLES_UVX, BUBBLES_UVY + yOffsetFlame, BUBBLES_WIDTH, BUBBLES_HEIGHT - yOffsetFlame);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		
		final int BREW_LABEL_XPOS = 5;
		final int BREW_LABEL_YPOS = 24;
		
		final int BREW_XPOS = 5;
		final int BREW_YPOS = 32;
		
		final int NUM_XPOS = 5;
		final int NUM_YPOS = 44;
		
		fontRendererObj.drawString(tileEntity.hasCustomInventoryName() ? tileEntity.getInventoryName() : I18n.format(tileEntity.getInventoryName(), new Object[0]), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
		fontRendererObj.drawString("Brew:", BREW_LABEL_XPOS, BREW_LABEL_YPOS, Color.darkGray.getRGB());
		fontRendererObj.drawString(tileEntity.getBrewTypeString(), BREW_XPOS, BREW_YPOS, tileEntity.getBrewColor());
		fontRendererObj.drawString(Integer.toString(tileEntity.unitsLeft) + " units", NUM_XPOS, NUM_YPOS, Color.darkGray.getRGB());
	}

}

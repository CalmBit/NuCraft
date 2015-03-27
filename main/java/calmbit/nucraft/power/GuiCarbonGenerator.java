package calmbit.nucraft.power;

import java.awt.Color;

import com.sun.imageio.plugins.common.I18N;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCarbonGenerator extends GuiContainer {

	
	private static final ResourceLocation guiTexture = new ResourceLocation("nucraftpower" , "textures/gui/container/carbonGenerator.png");
	private TileEntityCarbonGenerator tileEntity;
	
	final int FLAME_XPOS = 81;
	final int FLAME_YPOS = 37;
	final int FLAME_UVX = 176;
	final int FLAME_UVY = 0;
	final int FLAME_HEIGHT = 14;
	final int FLAME_WIDTH = 14;
	
	public GuiCarbonGenerator(InventoryPlayer invPlayer, TileEntityCarbonGenerator tileEntityCarbonGenerator) {
		super(new ContainerCarbonGenerator(invPlayer, tileEntityCarbonGenerator));
		
		xSize = 176;
		ySize = 167;
		
		this.tileEntity = tileEntityCarbonGenerator;
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		double burnRemaining = tileEntity.fractionOfFuelRemaining();
		int yOffset = (int)((1.0 - burnRemaining) * FLAME_HEIGHT);
		drawTexturedModalRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS + yOffset, FLAME_UVX, FLAME_UVY + yOffset, FLAME_WIDTH, FLAME_HEIGHT - yOffset);
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

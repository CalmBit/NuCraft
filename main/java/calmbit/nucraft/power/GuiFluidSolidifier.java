package calmbit.nucraft.power;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiFluidSolidifier extends GuiContainer {

	private static final ResourceLocation guiTexture = new ResourceLocation("nucraftpower" , "textures/gui/container/fluidSolidifier.png");
	private static ResourceLocation liquidTexture;
	private TileEntityFluidSolidifier tileEntity;
	
	final int ARROW_XPOS = 66;
	final int ARROW_YPOS = 33;
	final int ARROW_UVX = 185;
	final int ARROW_UVY = 0;
	final int ARROW_HEIGHT = 15;
	final int ARROW_WIDTH = 37;
	
	final int FLUID_OVERLAY_XPOS = 118;
	final int FLUID_OVERLAY_YPOS = 12;
	final int FLUID_OVERLAY_UVX = 176;
	final int FLUID_OVERLAY_UVY = 0;
	final int FLUID_OVERLAY_HEIGHT = 62;
	final int FLUID_OVERLAY_WIDTH = 9;
	
	public GuiFluidSolidifier(InventoryPlayer player, TileEntityFluidSolidifier fluidSolidifier) {
		super(new ContainerFluidSolidifier(player, fluidSolidifier));
		xSize = 176;
		ySize = 167;
		this.tileEntity = fluidSolidifier;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		double solidifyRemaining = tileEntity.solidifyTime == 0 ? 0 : 200/tileEntity.solidifyTime;
		int xOffsetArrow = (int)((1.0 - solidifyRemaining) * ARROW_WIDTH);
		drawTexturedModalRect(guiLeft + ARROW_XPOS + xOffsetArrow, guiTop + ARROW_YPOS, ARROW_UVX + xOffsetArrow, ARROW_UVY, ARROW_WIDTH - xOffsetArrow, ARROW_HEIGHT);
		drawTexturedModalRect(guiLeft + FLUID_OVERLAY_XPOS, guiTop + FLUID_OVERLAY_YPOS, FLUID_OVERLAY_UVX, FLUID_OVERLAY_UVY, FLUID_OVERLAY_WIDTH, FLUID_OVERLAY_HEIGHT);
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

package calmbit.nucraft.power;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;

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
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		double solidifyRemaining = tileEntity.solidifyTime == 0 ? 0 : 200/tileEntity.solidifyTime;
		int xOffsetArrow = (int)((1.0 - solidifyRemaining) * ARROW_WIDTH);
		drawTexturedModalRect(guiLeft + ARROW_XPOS + xOffsetArrow, guiTop + ARROW_YPOS, ARROW_UVX + xOffsetArrow, ARROW_UVY, ARROW_WIDTH - xOffsetArrow, ARROW_HEIGHT);	
		Minecraft.getMinecraft().getTextureManager().bindTexture(guiTexture);
		drawTexturedModalRect(guiLeft + FLUID_OVERLAY_XPOS, guiTop + FLUID_OVERLAY_YPOS, FLUID_OVERLAY_UVX, FLUID_OVERLAY_UVY, FLUID_OVERLAY_WIDTH, FLUID_OVERLAY_HEIGHT);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		if(this.tileEntity.getTankInfo(null)[0].fluid != null) 
		{
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
			for(int i = 0; i < (this.tileEntity.getFractionOfTank() * 64 / 16);i++)
			{
				drawTexturedModelRectFromIcon(FLUID_OVERLAY_XPOS, (64 - (i == (int)((this.tileEntity.getFractionOfTank() * 64 / 16)) ? ((i-1)*16) + (int)(this.tileEntity.getFractionOfTank() * 64 % 16) : i*16) - 6), this.tileEntity.getTankInfo(null)[0].fluid.getFluid().getBlock().getIcon(0, 0), 16, i == (int)((this.tileEntity.getFractionOfTank() * 64 / 16)) ? (int)(this.tileEntity.getFractionOfTank() * 64 % 16) : 16);

			}
            if (this.func_146978_c(FLUID_OVERLAY_XPOS, 10, 16, 64, mouseX, mouseY))
            {
               this.renderLiquidTip(this.tileEntity.getTankInfo(null)[0], mouseX, mouseY);
            }
		}
		fontRendererObj.drawString(tileEntity.hasCustomInventoryName() ? tileEntity.getInventoryName() : I18n.format(tileEntity.getInventoryName(), new Object[0]), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
	}
	
	protected void renderLiquidTip(FluidTankInfo tankInfo, int p_146285_2_, int p_146285_3_)
    {
		ArrayList list = new ArrayList();
		list.add(tankInfo.fluid.getFluid().getBlock().getLocalizedName());
		list.add(tankInfo.fluid.amount + "mb/" + tankInfo.capacity + "mb");

        for (int k = 0; k < list.size(); ++k)
        {
        	list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
        }

        FontRenderer font = this.fontRendererObj;
        drawHoveringText(list, p_146285_2_ - guiLeft, p_146285_3_ - guiTop, (font == null ? fontRendererObj : font));
    }
}

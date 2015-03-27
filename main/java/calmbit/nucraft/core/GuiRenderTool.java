package calmbit.nucraft.core;

import net.minecraft.client.renderer.Tessellator;

public class GuiRenderTool {

	 	public static void drawTexturedModalRect(int x, int y, int u, int v, int sizeX, int sizeY, int zLevel)
	    {
	        float f = 0.00390625F;
	        float f1 = 0.00390625F;
	        Tessellator tessellator = Tessellator.instance;
	        tessellator.startDrawingQuads();
	        tessellator.addVertexWithUV((double)(x + 0), (double)(y + sizeY), (double)zLevel, (double)((float)(u + 0) * f), (double)((float)(v + sizeY) * f1));
	        tessellator.addVertexWithUV((double)(x + sizeX), (double)(y + sizeY), (double)zLevel, (double)((float)(u + sizeX) * f), (double)((float)(v + sizeY) * f1));
	        tessellator.addVertexWithUV((double)(x + sizeX), (double)(y + 0), (double)zLevel, (double)((float)(u + sizeX) * f), (double)((float)(v + 0) * f1));
	        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)zLevel, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
	        tessellator.draw();
	    }
}

package calmbit.nucraft.world;

import calmbit.nucraft.core.GuiRenderTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionAlcohol extends Potion {

	public static final int ALCOHOL_ID = 21;
	public static final ResourceLocation texture = new ResourceLocation("nucraftworld","textures/gui/potion_effects.png");
	public PotionAlcohol(int modifier) {
		super(ALCOHOL_ID + modifier, false, 0xFF0000);
		this.setPotionName("potion.alcohol");
	}
	
	
	@Override
	public boolean hasStatusIcon()
	{
		return false;
	}
	
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) 
	{ 
		mc.getTextureManager().bindTexture(this.texture);
		GuiRenderTool.drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18, 300);
	}

}

package calmbit.nucraft.rift;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;

public class RenderMaercs extends RenderBiped {

	private final static ResourceLocation maercMouthOpen = new ResourceLocation("nucraftrift", "textures/entity/maercs/mouth_open.png");
	private final static ResourceLocation maercMouthClosed = new ResourceLocation("nucraftrift", "textures/entity/maercs/mouth_closed.png");
	public RenderMaercs() {
		super(new ModelMaercs(), 1.0f);
	}
	
	protected ResourceLocation getEntityTexture(Entity entity)
    {
		EntityMaercs entityMaercs = (EntityMaercs)entity;
        return entityMaercs.getHealth() <= entityMaercs.getMaxHealth() / 2 ? maercMouthOpen : maercMouthClosed;
    }
	
	

}

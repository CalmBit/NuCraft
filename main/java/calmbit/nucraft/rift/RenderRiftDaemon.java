package calmbit.nucraft.rift;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderRiftDaemon extends RenderLiving{

	private final static ResourceLocation riftDaemon = new ResourceLocation("nucraftrift", "textures/entity/daemon/daemon_purple.png");

	public RenderRiftDaemon() {
		super(new ModelRiftDaemon(), 1.0f);
	}
	
	protected ResourceLocation getEntityTexture(Entity entity)
    {
		return riftDaemon;
    }
}

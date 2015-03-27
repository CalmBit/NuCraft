package calmbit.nucraft.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;
import calmbit.nucraft.core.NuCraft;
import calmbit.nucraft.power.CommonProxy;
import calmbit.nucraft.power.EntityNitroPrimed;
import calmbit.nucraft.power.RenderNitroPrimed;
import calmbit.nucraft.rift.EntityMaercs;
import calmbit.nucraft.rift.EntityRiftBombPrimed;
import calmbit.nucraft.rift.EntityRiftCrawler;
import calmbit.nucraft.rift.EntityRiftDaemon;
import calmbit.nucraft.rift.RenderMaercs;
import calmbit.nucraft.rift.RenderRiftBombPrimed;
import calmbit.nucraft.rift.RenderRiftCrawler;
import calmbit.nucraft.rift.RenderRiftDaemon;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers()
	{
		//1.8
		/*ModelResourceLocation copperIngotLoc = new ModelResourceLocation("nucraftpower:copperIngot","inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(NuCraftPower.copperIngot, 0, copperIngotLoc);
		ModelResourceLocation copperOreLoc = new ModelResourceLocation("nucraftpower:copperOre", "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GameRegistry.findItem("nucraftpower", "copperOre"), 0, copperOreLoc);
		ModelResourceLocation copperCableItemLoc = new ModelResourceLocation("nucraftpower:copperCable","inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GameRegistry.findItem("nucraftpower", "copperCable"), 0, copperCableItemLoc);
		ModelResourceLocation carbonGeneratorItemLoc = new ModelResourceLocation("nucraftpower:carbonGenerator","inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GameRegistry.findItem("nucraftpower", "carbonGenerator"), 0, carbonGeneratorItemLoc);
		ModelResourceLocation active_carbonGeneratorItemLoc = new ModelResourceLocation("nucraftpower:active_carbonGenerator","inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GameRegistry.findItem("nucraftpower", "active_carbonGenerator"), 0, active_carbonGeneratorItemLoc);*/
		
		RenderingRegistry.registerEntityRenderingHandler(EntityNitroPrimed.class, new RenderNitroPrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntityMaercs.class, new RenderMaercs());
		RenderingRegistry.registerEntityRenderingHandler(EntityRiftCrawler.class, new RenderRiftCrawler());
		RenderingRegistry.registerEntityRenderingHandler(EntityRiftDaemon.class, new RenderRiftDaemon());
		RenderingRegistry.registerEntityRenderingHandler(EntityRiftBombPrimed.class, new RenderRiftBombPrimed());
	}
}

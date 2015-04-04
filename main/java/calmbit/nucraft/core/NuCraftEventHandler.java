package calmbit.nucraft.core;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import calmbit.nucraft.rift.EntityMaercs;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.NoteBlockEvent;

public class NuCraftEventHandler {

        public static NuCraftEventHandler INSTANCE = new NuCraftEventHandler();
        public Map<Block, Item> buckets = new HashMap<Block, Item>();

        private NuCraftEventHandler() {
        }

        @SubscribeEvent
        public void onBucketFill(FillBucketEvent event) {

                ItemStack result = fillCustomBucket(event.world, event.target);

                if (result == null)
                        return;

                event.result = result;
                event.setResult(Result.ALLOW);
        }
        
        private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) 
        {
                Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

                Item bucket = buckets.get(block);
                if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
                        world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
                        return new ItemStack(bucket);
                } else
                        return null;

        }
        
        @SubscribeEvent
        public void onRenderGameOverlay(RenderGameOverlayEvent event)
        {
        	if(event.type == RenderGameOverlayEvent.ElementType.DEBUG)
        	{
        		Minecraft mc = Minecraft.getMinecraft();
        		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        		int width = res.getScaledWidth();
        		FontRenderer render = Minecraft.getMinecraft().fontRenderer;
        		Block block = null;
        		if(mc.renderViewEntity.rayTrace(200.0f, 1.0f) != null)
        		{
        			int x = mc.renderViewEntity.rayTrace(200.0f, 1.0f).blockX;
        			int y = mc.renderViewEntity.rayTrace(200.0f, 1.0f).blockY;
        			int z = mc.renderViewEntity.rayTrace(200.0f, 1.0f).blockZ;
        			block = mc.theWorld.getBlock(x, y, z);
            		render.drawStringWithShadow(block.getUnlocalizedName(), width - render.getStringWidth(block.getUnlocalizedName()) - 10, 2 + 7 * 10, Color.WHITE.getRGB());
            		render.drawStringWithShadow("ID: " + block.getIdFromBlock(block), width - render.getStringWidth("ID: " + block.getIdFromBlock(block)) - 10, 2 + 8 * 10, Color.WHITE.getRGB());
            		render.drawStringWithShadow("Meta: " + block.getDamageValue(mc.theWorld, x, y, z), width - render.getStringWidth("Meta: " + block.getDamageValue(mc.theWorld, x, y, z)) - 10, 2 + 9 * 10, Color.WHITE.getRGB());
            		
        		}
        	}
        }
        
        @SubscribeEvent
        public void onItemCrafted(ItemCraftedEvent event)
        {
        	if(event.crafting.getItem() == Item.getItemFromBlock(NuCraft.machineCasing))
        	{
        		event.player.addStat(NuCraft.machineFrame, 1);
        	}
        	else if(event.crafting.getItem() == Item.getItemFromBlock(NuCraft.carbonGenerator))
        	{
        		event.player.addStat(NuCraft.makingEnergy, 1);
        	}
        	else if(event.crafting.getItem() == Item.getItemFromBlock(NuCraft.compressor))
        	{
        		event.player.addStat(NuCraft.compression, 1);
        	}
        }
}
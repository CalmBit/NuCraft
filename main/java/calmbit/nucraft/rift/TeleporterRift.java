package calmbit.nucraft.rift;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterRift extends Teleporter {

	WorldServer worldServerInstance;
	public TeleporterRift(WorldServer p_i1963_1_) {
		super(p_i1963_1_);
		this.worldServerInstance = p_i1963_1_;
	}
	
	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float rotation)
    {
       this.placeInExistingPortal(entity, x, y, z, rotation);
    }

	@Override
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotation)
    {
		double finalY = 128;
		for(int i = worldServerInstance.getActualHeight(); i >= 0;i--)
		{
			if(worldServerInstance.getBlock((int)x, i, (int)z) != Blocks.air && worldServerInstance.getBlock((int)x, i+1, (int)z)  == Blocks.air && worldServerInstance.getBlock((int)x, i+2, (int)z) == Blocks.air)
			{
				finalY = i+1; 
				break;
			}
		}
		entity.setLocationAndAngles(x, finalY, z, entity.rotationYaw, entity.rotationPitch);
		entity.timeUntilPortal = 40;
        return true;
    }
}

package calmbit.nucraft.rift;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRiftPortal extends TileEntity {

	protected int cooldownPeriod;
	private final static float PARTICLE_DISTANCE = 0.5f;
	
	public void setCooldownPeriod(int cooldown)
	{
		this.cooldownPeriod = cooldown;
	}
	
	public int getCooldownPeriod()
	{
		return cooldownPeriod;
	}
	
	@Override
	public void updateEntity()
	{
		if(cooldownPeriod > 0)
		{
			cooldownPeriod--;
			worldObj.spawnParticle("flame", xCoord + 0.5 + (worldObj.rand.nextBoolean() ? worldObj.rand.nextFloat()*PARTICLE_DISTANCE : worldObj.rand.nextFloat()*-PARTICLE_DISTANCE), yCoord + 1.0, zCoord + 0.5 + (worldObj.rand.nextBoolean() ? worldObj.rand.nextFloat()*PARTICLE_DISTANCE : worldObj.rand.nextFloat()*-PARTICLE_DISTANCE), 0.0f, 0.1f, 0.0f);
			markDirty();
		}
		else
		{
			worldObj.spawnParticle("portal", xCoord + 0.5 + (worldObj.rand.nextBoolean() ? worldObj.rand.nextFloat()*PARTICLE_DISTANCE : worldObj.rand.nextFloat()*-PARTICLE_DISTANCE), yCoord + 1.0, zCoord + 0.5 + (worldObj.rand.nextBoolean() ? worldObj.rand.nextFloat()*PARTICLE_DISTANCE : worldObj.rand.nextFloat()*-PARTICLE_DISTANCE), 0.0f, 0.1f, 0.0f);
		}
		
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound compound = new NBTTagCompound();
		writeToNBT(compound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet)
	{
		readFromNBT(packet.func_148857_g());
	}

	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        int cooldownPeriod = compound.getInteger("CooldownPeriod");
        this.cooldownPeriod = cooldownPeriod;
    }

	@Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("CooldownPeriod", cooldownPeriod);
    }
}

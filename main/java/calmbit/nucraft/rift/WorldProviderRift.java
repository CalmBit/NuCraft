package calmbit.nucraft.rift;

import calmbit.nucraft.core.NuCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderHell;

public class WorldProviderRift extends WorldProvider {

	
	public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(NuCraft.riftBiome, 0.3F);
        this.dimensionId = 2;
        this.hasNoSky = true;
    }
	
	@Override
	public String getDimensionName() {
		return "Rift";
	}
	
    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
        //return new ChunkProviderFlat(this.worldObj, this.worldObj.getSeed(), true, "2;7,60x" + Block.getIdFromBlock(NuCraft.riftStone) + ",4x" + Block.getIdFromBlock(NuCraft.riftDirt) + "," + Block.getIdFromBlock(NuCraft.riftGrass) + ";1;village(size=20),decoration");
    	return new ChunkProviderRift(worldObj, this.worldObj.getSeed(), true);
    }
    
    @Override
    public boolean isSurfaceWorld()
    {
    	return false;
    }
    
    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_)
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p_76562_1_, float p_76562_2_)
    {
        int i = 9445631;
        float f3 = (float)(i >> 16 & 255) / 255.0F;
        float f4 = (float)(i >> 8 & 255) / 255.0F;
        float f5 = (float)(i & 255) / 255.0F;
        return Vec3.createVectorHelper((double)f3, (double)f4, (double)f5);
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }

}

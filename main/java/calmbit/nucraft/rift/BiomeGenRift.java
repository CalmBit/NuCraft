package calmbit.nucraft.rift;

import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import calmbit.nucraft.world.WorldGenElmTrees;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeGenRift extends BiomeGenBase {
	private static final String __OBFID = "CL_00000173";

	public BiomeGenRift(int p_i1981_1_) {
		super(p_i1981_1_);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityMaercs.class, 100, 1, 6));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRiftCrawler.class, 100, 2, 4));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityRiftDaemon.class, 50, 1, 1));
		this.setColor(255);
		this.setBiomeName("The Rift");
		this.setHeight(height_RockyWaters);
		this.setTemperatureRainfall(0.2F, 0.3F);
		this.topBlock = NuCraft.riftGrass;
		this.fillerBlock = NuCraft.riftDirt;
	}

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ) {
		/*int i1;
		int j1;
		int k1;
		for (int k = 0; k < 2; ++k) {
			for (int l = 0; l < 2; ++l) 
			{
				i1 = chunkX + k * 4 + 1 + 8 + random.nextInt(3);
				j1 = chunkZ + l * 4 + 1 + 8 + random.nextInt(3);
				k1 = world.getHeightValue(i1, j1);
				WorldGenElmTrees trees = new WorldGenElmTrees();
				trees.generate(world, random, i1, k1, j1);
			}
		}*/
	}
	
	public final void genTerrainBlocks(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_)
    {
        boolean flag = true;
        Block block = this.topBlock;
        byte b0 = (byte)(this.field_150604_aj & 255);
        Block block1 = this.fillerBlock;
        int k = -1;
        int l = (int)(p_150560_7_ / 3.0D + 3.0D + p_150560_2_.nextDouble() * 0.25D);
        int i1 = p_150560_5_ & 15;
        int j1 = p_150560_6_ & 15;
        int k1 = p_150560_3_.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (j1 * 16 + i1) * k1 + l1;

            if (l1 <= 0 + p_150560_2_.nextInt(5))
            {
                p_150560_3_[i2] = Blocks.bedrock;
            }
            else
            {
                Block block2 = p_150560_3_[i2];

                if (block2 != null && block2.getMaterial() != Material.air)
                {
                    if (block2 == NuCraft.riftStone)
                    {
                        if (k == -1)
                        {
                            if (l <= 0)
                            {
                                block = null;
                                b0 = 0;
                                block1 = NuCraft.riftStone;
                            }
                            else if (l1 >= 59 && l1 <= 64)
                            {
                                block = this.topBlock;
                                b0 = (byte)(this.field_150604_aj & 255);
                                block1 = this.fillerBlock;
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                block = NuCraft.acidBlock;
                                b0 = 0;
                            }

                            k = l;

                            if (l1 >= 62)
                            {
                                p_150560_3_[i2] = block;
                                p_150560_4_[i2] = b0;
                            }
                            else if (l1 < 56 - l)
                            {
                                block = null;
                                block1 = NuCraft.riftStone;
                                p_150560_3_[i2] = Blocks.gravel;
                            }
                            else
                            {
                                p_150560_3_[i2] = block1;
                            }
                        }
                        else if (k > 0)
                        {
                            --k;
                            p_150560_3_[i2] = block1;

                            if (k == 0 && block1 == Blocks.sand)
                            {
                                k = p_150560_2_.nextInt(4) + Math.max(0, l1 - 63);
                                block1 = Blocks.sandstone;
                            }
                        }
                    }
                }
                else
                {
                    k = -1;
                }
            }
        }
    }

}
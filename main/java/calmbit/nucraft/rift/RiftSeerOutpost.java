package calmbit.nucraft.rift;

import java.util.List;
import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import calmbit.nucraft.power.Workshop;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class RiftSeerOutpost extends StructureVillagePieces.Village {

	public Block plankBlock = NuCraft.planksPlasmaticTree;
	public Block logBlock = NuCraft.logPlasmaticTree;
	public Block cobbleBlock = NuCraft.riftCobblestone;
	public Block doorBlock = NuCraft.doorPlasmaticTree;
	
	public RiftSeerOutpost() {}
	
	public RiftSeerOutpost(StructureVillagePieces.Start p_i2094_1_, int p_i2094_2_, Random p_i2094_3_, StructureBoundingBox p_i2094_4_, int p_i2094_5_) 
	{
		super(p_i2094_1_, p_i2094_2_);
        this.coordBaseMode = p_i2094_5_;
        this.boundingBox = p_i2094_4_;
	}
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox boundingBox) {
		 if (this.field_143015_k < 0)
         {
             this.field_143015_k = this.getAverageGroundLevel(world, boundingBox);

             if (this.field_143015_k < 0)
             {
                 return true;
             }

             this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 5 - 1, 0);
         }
		 this.fillWithAir(world, this.boundingBox, 0, 0, 0, 8, 5, 8);
		 this.fillWithBlocks(world, this.boundingBox, 0, 0, 0, 8, 0, 8, cobbleBlock, cobbleBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 1, 0, 1, 7, 0, 7, plankBlock, plankBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 1, 5, 1, 7, 5, 7, logBlock, logBlock, false);
		 
		 //Front Wall
		 this.fillWithBlocks(world, this.boundingBox, 0, 1, 0, 8, 4, 0, plankBlock, plankBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 1, 0, 0, 4, 0, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 8, 1, 0, 8, 4, 0, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 4, 0, 8, 4, 0, logBlock, logBlock, false);
		 this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 2, 2, 0, this.boundingBox);
		 this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 6, 2, 0, this.boundingBox);
		 
		 //Right Wall
		 this.fillWithBlocks(world, this.boundingBox, 0, 1, 0, 0, 4, 8, plankBlock, plankBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 1, 0, 0, 1, 8, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 4, 0, 0, 4, 8, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 2, 3, 0, 2, 6, Blocks.glass, Blocks.glass, false);
		 
		 //Wall
		 this.fillWithBlocks(world, this.boundingBox, 8, 1, 0, 8, 4, 8, plankBlock, plankBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 8, 1, 0, 8, 1, 8, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 8, 4, 0, 8, 4, 8, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 8, 2, 2, 8, 2, 6, Blocks.glass, Blocks.glass, false);
		 
		 //Back Wall
		 this.fillWithBlocks(world, this.boundingBox, 0, 1, 8, 8, 4, 8, plankBlock, plankBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 1, 8, 8, 1, 8, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 0, 4, 8, 8, 4, 8, logBlock, logBlock, false);
		 this.fillWithBlocks(world, this.boundingBox, 2, 2, 8, 6, 2, 8, Blocks.glass, Blocks.glass, false);
		 
		 
		 this.spawnVillagers(world, this.boundingBox, 4, 1, 4, 1);
		 return true;
		 
	}
	
    public static RiftSeerOutpost buildComponent(StructureVillagePieces.Start p_74898_0_, List p_74898_1_, Random p_74898_2_, int p_74898_3_, int p_74898_4_, int p_74898_5_, int p_74898_6_, int p_74898_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74898_3_, p_74898_4_, p_74898_5_, 0, 0, 0, 9, 6, 9, p_74898_6_);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74898_1_, structureboundingbox) == null ? new RiftSeerOutpost(p_74898_0_, p_74898_7_, p_74898_2_, structureboundingbox, p_74898_6_) : null;
    }
    
    protected int getVillagerType(int p_74888_1_)
    {
        return 1001;
    }

}

package calmbit.nucraft.world;

import java.util.List;
import java.util.Random;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class NuCraftPlot extends StructureVillagePieces.Village
{
	/** First crop type for this field. */
    private Block cropTypeA;
    /** Second crop type for this field. */
    private Block cropTypeB;
    /** Third crop type for this field. */
    private Block cropTypeC;
    /** Fourth crop type for this field. */
    private Block cropTypeD;
    
    public NuCraftPlot() {}

    public NuCraftPlot(StructureVillagePieces.Start p_i2094_1_, int p_i2094_2_, Random p_i2094_3_, StructureBoundingBox p_i2094_4_, int p_i2094_5_)
    {
        super(p_i2094_1_, p_i2094_2_);
        this.coordBaseMode = p_i2094_5_;
        this.boundingBox = p_i2094_4_;
    }

    public static NuCraftPlot buildComponent(StructureVillagePieces.Start p_74898_0_, List p_74898_1_, Random p_74898_2_, int p_74898_3_, int p_74898_4_, int p_74898_5_, int p_74898_6_, int p_74898_7_)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74898_3_, p_74898_4_, p_74898_5_, 0, 0, 0, 13, 4, 9, p_74898_6_);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74898_1_, structureboundingbox) == null ? new NuCraftPlot(p_74898_0_, p_74898_7_, p_74898_2_, structureboundingbox, p_74898_6_) : null;
    }
    
    public Block blockFromInt(int n)
    {
    	switch(n)
    	{
    		default:
    			return NuCraft.barleyCrop;
    		case 1:
    			return NuCraft.hopsCrop;
    	}
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
     * Mineshafts at the end, it adds Fences...
     */
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_)
    {
        if (this.field_143015_k < 0)
        {
            this.field_143015_k = this.getAverageGroundLevel(p_74875_1_, p_74875_3_);

            if (this.field_143015_k < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
        }

        this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 1, 0, 12, 4, 8, Blocks.air, Blocks.air, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland, Blocks.farmland, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland, Blocks.farmland, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 7, 0, 1, 8, 0, 7, Blocks.farmland, Blocks.farmland, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 10, 0, 1, 11, 0, 7, Blocks.farmland, Blocks.farmland, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 0, 0, 0, 0, 0, 8, NuCraft.logElm, NuCraft.logElm, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 6, 0, 0, 6, 0, 8, NuCraft.logElm, NuCraft.logElm, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 12, 0, 0, 12, 0, 8, NuCraft.logElm, NuCraft.logElm, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 0, 11, 0, 0, NuCraft.logElm, NuCraft.logElm, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 1, 0, 8, 11, 0, 8, NuCraft.logElm, NuCraft.logElm, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water, Blocks.water, false);
        this.fillWithBlocks(p_74875_1_, p_74875_3_, 9, 0, 1, 9, 0, 7, Blocks.water, Blocks.water, false);
        int i;

        for (i = 1; i <= 7; ++i)
        {
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 1, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 2, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 4, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 5, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 7, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 8, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 10, 1, i, p_74875_3_);
            this.placeBlockAtCurrentPosition(p_74875_1_, blockFromInt(p_74875_2_.nextInt(2)), MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7), 11, 1, i, p_74875_3_);
        }

        for (i = 0; i < 9; ++i)
        {
            for (int j = 0; j < 13; ++j)
            {
                this.clearCurrentPositionBlocksUpwards(p_74875_1_, j, 4, i, p_74875_3_);
                this.func_151554_b(p_74875_1_, Blocks.dirt, 0, j, -1, i, p_74875_3_);
            }
        }

        return true;
    }
}
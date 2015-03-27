package calmbit.nucraft.power;

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
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class Workshop extends StructureVillagePieces.Village
        {
            private static final String __OBFID = "CL_00000517";
            
            public Block plankBlock = NuCraft.planksElm;
            public Block logBlock = NuCraft.logElm;
            public Block doorBlock = NuCraft.doorElm;
            public int carpetMeta = -1;
            
            public static final WeightedRandomChestContent[] workshopChestContents = new WeightedRandomChestContent[] 
            {
            	new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 7), 
            	new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), 
            	new WeightedRandomChestContent(Items.redstone, 0, 1, 10, 10),
            	new WeightedRandomChestContent(NuCraft.carbonWafer, 0, 1, 3, 7),
            	new WeightedRandomChestContent(NuCraft.siliconFragment, 0, 1, 8, 7),
            	new WeightedRandomChestContent(NuCraft.steelIngot, 0, 1, 3, 5),
            	new WeightedRandomChestContent(NuCraft.copperIngot, 0, 1, 5, 7),
            	new WeightedRandomChestContent(Item.getItemFromBlock(NuCraft.saplingElm), 0, 1, 5, 8)
            };
            
            public Workshop() {}

            public Workshop(StructureVillagePieces.Start p_i2094_1_, int p_i2094_2_, Random p_i2094_3_, StructureBoundingBox p_i2094_4_, int p_i2094_5_)
            {
                super(p_i2094_1_, p_i2094_2_);
                this.coordBaseMode = p_i2094_5_;
                this.boundingBox = p_i2094_4_;
                boolean woodType = p_i2094_3_.nextBoolean();
                this.plankBlock = woodType ? NuCraft.planksCherry : NuCraft.planksElm;
                this.logBlock = woodType ?  NuCraft.logCherry : NuCraft.logElm;
                this.doorBlock = woodType ? NuCraft.doorCherry : NuCraft.doorElm;
                if(!NuCraft.config.get(NuCraft.config.CATEGORY_GENERAL, "discoCarpets", false).getBoolean(false))
                {
                	this.carpetMeta = p_i2094_3_.nextInt(15);
                }
            }

            public static Workshop buildComponent(StructureVillagePieces.Start p_74898_0_, List p_74898_1_, Random p_74898_2_, int p_74898_3_, int p_74898_4_, int p_74898_5_, int p_74898_6_, int p_74898_7_)
            {
                StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p_74898_3_, p_74898_4_, p_74898_5_, 0, 0, 0, 11, 5, 11, p_74898_6_);
                return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(p_74898_1_, structureboundingbox) == null ? new Workshop(p_74898_0_, p_74898_7_, p_74898_2_, structureboundingbox, p_74898_6_) : null;
            }

            /**
             * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes
             * Mineshafts at the end, it adds Fences...
             */
            public boolean addComponentParts(World world, Random random, StructureBoundingBox boundingBox)
            {
                if (this.field_143015_k < 0)
                {
                    this.field_143015_k = this.getAverageGroundLevel(world, boundingBox);

                    if (this.field_143015_k < 0)
                    {
                        return true;
                    }

                    this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 5 - 1, 0);
                }

                this.fillWithBlocks(world, boundingBox, 1, 1, 1, 10, 4, 10, Blocks.air, Blocks.air, false);
                this.fillWithBlocks(world, boundingBox, 0, 0, 0, 10, 0, 10, Blocks.cobblestone, Blocks.cobblestone, false);
                /*this.fillWithBlocks(world, boundingBox, 0, 0, 0, 10, 0, 0, Blocks.cobblestone, Blocks.cobblestone, false);
                this.fillWithBlocks(world, boundingBox, 10, 0, 0, 10, 0, 10, Blocks.cobblestone, Blocks.cobblestone, false);
                this.fillWithBlocks(world, boundingBox, 10, 0, 10, 0, 0, 10, Blocks.cobblestone, Blocks.cobblestone, false);
                this.fillWithBlocks(world, boundingBox, 0, 0, 10, 0, 0, 0, Blocks.cobblestone, Blocks.cobblestone, false);*/
                this.fillWithBlocks(world, boundingBox, 1, 0, 1, 9, 0, 9, plankBlock, plankBlock, false);
                this.fillWithBlocks(world, boundingBox, 0, 4, 0, 10, 4, 10, Blocks.cobblestone, Blocks.cobblestone, false);
                
                //RIGHT WALL
                this.fillWithBlocks(world, boundingBox, 0, 1, 0, 0, 1, 10, plankBlock, plankBlock, false);
                this.fillWithBlocks(world, boundingBox, 0, 3, 0, 0, 3, 10, plankBlock, plankBlock, false);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 0, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 0, 2, 1, boundingBox);
                this.fillWithBlocks(world, boundingBox, 0, 2, 2, 0, 2, 4, Blocks.glass, Blocks.glass, false);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 0, 2, 5, boundingBox);
                this.fillWithBlocks(world, boundingBox, 0, 2, 6, 0, 2, 8, Blocks.glass, Blocks.glass, false);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 0, 2, 9, boundingBox);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 0, 2, 10, boundingBox);
                
                //LEFT WALL
                this.fillWithBlocks(world, boundingBox, 10, 1, 0, 10, 1, 10, plankBlock, plankBlock, false);
                this.fillWithBlocks(world, boundingBox, 10, 3, 0, 10, 3, 10, plankBlock, plankBlock, false);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 10, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 10, 2, 1, boundingBox);
                this.fillWithBlocks(world, boundingBox, 10, 2, 2, 10, 2, 4, Blocks.glass, Blocks.glass, false);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 10, 2, 5, boundingBox);
                this.fillWithBlocks(world, boundingBox, 10, 2, 6, 10, 2, 8, Blocks.glass, Blocks.glass, false);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 10, 2, 9, boundingBox);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 10, 2, 10, boundingBox);
                
                //FRONT WALL
                this.fillWithBlocks(world, boundingBox, 0, 1, 0, 5, 1, 0, plankBlock, plankBlock, false);
                this.fillWithBlocks(world, boundingBox, 7, 1, 0, 10, 1, 0, plankBlock, plankBlock, false);
                this.fillWithBlocks(world, boundingBox, 0, 3, 0, 10, 3, 0, plankBlock, plankBlock, false);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 0, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 1, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 2, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 3, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 4, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 5, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 7, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 8, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 9, 2, 0, boundingBox);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 10, 2, 0, boundingBox);
                
                //BACK WALL
                this.fillWithBlocks(world, boundingBox, 0, 1, 10, 10, 1, 10, plankBlock, plankBlock, false);
                this.fillWithBlocks(world, boundingBox, 0, 3, 10, 10, 3, 10, plankBlock, plankBlock, false);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 0, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 1, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 2, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 3, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 4, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 5, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 6, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 7, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.glass, 0, 8, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 9, 2, 10, boundingBox);
                this.placeBlockAtCurrentPosition(world, plankBlock, 0, 10, 2, 10, boundingBox);
                
                
                //DECO
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 3, 1, 4, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 3, 1, 5, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 3, 1, 6, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 3, 1, 7, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 4, 1, 4, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 4, 1, 5, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 4, 1, 6, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 4, 1, 7, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 5, 1, 4, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 5, 1, 5, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 5, 1, 6, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 5, 1, 7, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 6, 1, 4, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 6, 1, 5, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 6, 1, 6, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 6, 1, 7, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 7, 1, 4, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 7, 1, 5, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 7, 1, 6, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.carpet, ((carpetMeta == -1) ? random.nextInt(15) : carpetMeta), 7, 1, 7, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.crafting_table, 0, 9, 1, 9, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 9, 1, 8, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 9, 1, 7, boundingBox);
                this.placeBlockAtCurrentPosition(world, logBlock, 0, 8, 1, 9, boundingBox);
                this.placeBlockAtCurrentPosition(world, Blocks.furnace, this.getMetadataWithOffset(Blocks.furnace, 1), 8, 1, 9, boundingBox);
                this.generateStructureChestContents(world, boundingBox, random, 7, 1, 9, workshopChestContents, getCount(random, 5, 7));
                this.generateStructureChestContents(world, boundingBox, random, 6, 1, 9, workshopChestContents, getCount(random, 5, 7));
                this.placeDoorAtCurrentPosition(world, boundingBox, random, 6, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
                //this.placeBlockAtCurrentPosition(world, Blocks.torch, this.getMetadataWithOffset(Blocks.torch, 1), 5, 3, 9, boundingBox);

                if (this.getBlockAtCurrentPosition(world, 6, 0, -1, boundingBox).getMaterial() == Material.air && this.getBlockAtCurrentPosition(world, 6, -1, -1, boundingBox).getMaterial() != Material.air)
                {
                    this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, this.getMetadataWithOffset(Blocks.stone_stairs, 3), 6, 0, -1, boundingBox);
                }

              /*  for (l = 0; l < 6; ++l)
                {
                    for (int i1 = 0; i1 < 9; ++i1)
                    {
                        this.clearCurrentPositionBlocksUpwards(p_74875_1_, i1, 9, l, p_74875_3_);
                        this.func_151554_b(p_74875_1_, Blocks.cobblestone, 0, i1, -1, l, p_74875_3_);
                    }
                }*/

                this.spawnVillagers(world, boundingBox, 2, 1, 2, 1);
                return true;
            }           
            
            @Override
            protected void placeDoorAtCurrentPosition(World p_74881_1_, StructureBoundingBox p_74881_2_, Random p_74881_3_, int p_74881_4_, int p_74881_5_, int p_74881_6_, int p_74881_7_)
            {
                int i1 = this.getXWithOffset(p_74881_4_, p_74881_6_);
                int j1 = this.getYWithOffset(p_74881_5_);
                int k1 = this.getZWithOffset(p_74881_4_, p_74881_6_);

                if (p_74881_2_.isVecInside(i1, j1, k1))
                {
                    ItemDoor.placeDoorBlock(p_74881_1_, i1, j1, k1, p_74881_7_, doorBlock);
                }
            }
            
            public int getCount(Random rand, int countMin, int countMax)
            {
                return countMin < countMax ? countMin + rand.nextInt(countMax - countMin) : countMin;
            }

            /**
             * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
             */
            protected int getVillagerType(int p_74888_1_)
            {
                return 1000;
            }
        }
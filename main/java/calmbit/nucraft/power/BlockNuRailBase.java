package calmbit.nucraft.power;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public abstract class BlockNuRailBase extends BlockRailBase {
	
	protected BlockNuRailBase(boolean powered) {
		super(powered);
	}

	public static final boolean CheckIfNuRailFromCoords(World world, int x, int y, int z)
    {
        return CheckIfNuRail(world.getBlock(x, y, z));
    }

    public static final boolean CheckIfNuRail(Block block)
    {
        return block instanceof BlockNuRailBase;
    }
    
	public class NuRail
	{
	    private World field_150660_b;
	    private int field_150661_c;
	    private int field_150658_d;
	    private int field_150659_e;
	    private final boolean field_150656_f;
	    private List field_150657_g = new ArrayList();
	    private static final String __OBFID = "CL_00000196";
	    private final boolean canMakeSlopes;

	    public NuRail(World p_i45388_2_, int p_i45388_3_, int p_i45388_4_, int p_i45388_5_)
	    {
	        this.field_150660_b = p_i45388_2_;
	        this.field_150661_c = p_i45388_3_;
	        this.field_150658_d = p_i45388_4_;
	        this.field_150659_e = p_i45388_5_;
	        BlockNuRailBase block = (BlockNuRailBase)p_i45388_2_.getBlock(p_i45388_3_, p_i45388_4_, p_i45388_5_);
	        int l = block.getBasicRailMetadata(p_i45388_2_, null, p_i45388_3_, p_i45388_4_, p_i45388_5_);
	        this.field_150656_f = !block.isFlexibleRail(p_i45388_2_, p_i45388_3_, p_i45388_4_, p_i45388_5_);
	        canMakeSlopes = block.canMakeSlopes(p_i45388_2_, p_i45388_3_, p_i45388_4_, p_i45388_5_);
	        this.func_150648_a(l);
	    }

	    private void func_150648_a(int p_150648_1_)
	    {
	        this.field_150657_g.clear();

	        if (p_150648_1_ == 0)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1));
	        }
	        else if (p_150648_1_ == 1)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e));
	        }
	        else if (p_150648_1_ == 2)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c + 1, this.field_150658_d + 1, this.field_150659_e));
	        }
	        else if (p_150648_1_ == 3)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c - 1, this.field_150658_d + 1, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e));
	        }
	        else if (p_150648_1_ == 4)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d + 1, this.field_150659_e - 1));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1));
	        }
	        else if (p_150648_1_ == 5)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d + 1, this.field_150659_e + 1));
	        }
	        else if (p_150648_1_ == 6)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1));
	        }
	        else if (p_150648_1_ == 7)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1));
	        }
	        else if (p_150648_1_ == 8)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1));
	        }
	        else if (p_150648_1_ == 9)
	        {
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e));
	            this.field_150657_g.add(new ChunkPosition(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1));
	        }
	    }

	    private void func_150651_b()
	    {
	        for (int i = 0; i < this.field_150657_g.size(); ++i)
	        {
	            BlockNuRailBase.NuRail rail = this.func_150654_a((ChunkPosition)this.field_150657_g.get(i));

	            if (rail != null && rail.func_150653_a(this))
	            {
	                this.field_150657_g.set(i, new ChunkPosition(rail.field_150661_c, rail.field_150658_d, rail.field_150659_e));
	            }
	            else
	            {
	                this.field_150657_g.remove(i--);
	            }
	        }
	    }

	    private boolean func_150646_a(int p_150646_1_, int p_150646_2_, int p_150646_3_)
	    {
	        return BlockNuRailBase.func_150049_b_(this.field_150660_b, p_150646_1_, p_150646_2_, p_150646_3_) ? true : (BlockNuRailBase.func_150049_b_(this.field_150660_b, p_150646_1_, p_150646_2_ + 1, p_150646_3_) ? true : BlockNuRailBase.func_150049_b_(this.field_150660_b, p_150646_1_, p_150646_2_ - 1, p_150646_3_));
	    }

	    private BlockNuRailBase.NuRail func_150654_a(ChunkPosition p_150654_1_)
	    {
	        return BlockNuRailBase.CheckIfNuRailFromCoords(this.field_150660_b, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY, p_150654_1_.chunkPosZ) ? BlockNuRailBase.this.new NuRail(this.field_150660_b, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY, p_150654_1_.chunkPosZ) : (BlockNuRailBase.func_150049_b_(this.field_150660_b, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY + 1, p_150654_1_.chunkPosZ) ? BlockNuRailBase.this.new NuRail(this.field_150660_b, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY + 1, p_150654_1_.chunkPosZ) : (BlockNuRailBase.func_150049_b_(this.field_150660_b, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY - 1, p_150654_1_.chunkPosZ) ? BlockNuRailBase.this.new NuRail(this.field_150660_b, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY - 1, p_150654_1_.chunkPosZ) : null));
	    }

	    private boolean func_150653_a(BlockNuRailBase.NuRail p_150653_1_)
	    {
	        for (int i = 0; i < this.field_150657_g.size(); ++i)
	        {
	            ChunkPosition chunkposition = (ChunkPosition)this.field_150657_g.get(i);

	            if (chunkposition.chunkPosX == p_150653_1_.field_150661_c && chunkposition.chunkPosZ == p_150653_1_.field_150659_e)
	            {
	                return true;
	            }
	        }

	        return false;
	    }

	    private boolean func_150652_b(int p_150652_1_, int p_150652_2_, int p_150652_3_)
	    {
	        for (int l = 0; l < this.field_150657_g.size(); ++l)
	        {
	            ChunkPosition chunkposition = (ChunkPosition)this.field_150657_g.get(l);

	            if (chunkposition.chunkPosX == p_150652_1_ && chunkposition.chunkPosZ == p_150652_3_)
	            {
	                return true;
	            }
	        }

	        return false;
	    }

	    protected int func_150650_a()
	    {
	        int i = 0;

	        if (this.func_150646_a(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1))
	        {
	            ++i;
	        }

	        if (this.func_150646_a(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1))
	        {
	            ++i;
	        }

	        if (this.func_150646_a(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e))
	        {
	            ++i;
	        }

	        if (this.func_150646_a(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e))
	        {
	            ++i;
	        }

	        return i;
	    }

	    private boolean func_150649_b(BlockNuRailBase.NuRail p_150649_1_)
	    {
	        return this.func_150653_a(p_150649_1_) ? true : (this.field_150657_g.size() == 2 ? false : (this.field_150657_g.isEmpty() ? true : true));
	    }

	    private void func_150645_c(BlockNuRailBase.NuRail p_150645_1_)
	    {
	        this.field_150657_g.add(new ChunkPosition(p_150645_1_.field_150661_c, p_150645_1_.field_150658_d, p_150645_1_.field_150659_e));
	        boolean flag = this.func_150652_b(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1);
	        boolean flag1 = this.func_150652_b(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1);
	        boolean flag2 = this.func_150652_b(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e);
	        boolean flag3 = this.func_150652_b(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e);
	        byte b0 = -1;

	        if (flag || flag1)
	        {
	            b0 = 0;
	        }

	        if (flag2 || flag3)
	        {
	            b0 = 1;
	        }

	        if (!this.field_150656_f)
	        {
	            if (flag1 && flag3 && !flag && !flag2)
	            {
	                b0 = 6;
	            }

	            if (flag1 && flag2 && !flag && !flag3)
	            {
	                b0 = 7;
	            }

	            if (flag && flag2 && !flag1 && !flag3)
	            {
	                b0 = 8;
	            }

	            if (flag && flag3 && !flag1 && !flag2)
	            {
	                b0 = 9;
	            }
	        }

	        if (b0 == 0 && canMakeSlopes)
	        {
	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c, this.field_150658_d + 1, this.field_150659_e - 1))
	            {
	                b0 = 4;
	            }

	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c, this.field_150658_d + 1, this.field_150659_e + 1))
	            {
	                b0 = 5;
	            }
	        }

	        if (b0 == 1 && canMakeSlopes)
	        {
	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c + 1, this.field_150658_d + 1, this.field_150659_e))
	            {
	                b0 = 2;
	            }

	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c - 1, this.field_150658_d + 1, this.field_150659_e))
	            {
	                b0 = 3;
	            }
	        }

	        if (b0 < 0)
	        {
	            b0 = 0;
	        }

	        int i = b0;

	        if (this.field_150656_f)
	        {
	            i = this.field_150660_b.getBlockMetadata(this.field_150661_c, this.field_150658_d, this.field_150659_e) & 8 | b0;
	        }

	        this.field_150660_b.setBlockMetadataWithNotify(this.field_150661_c, this.field_150658_d, this.field_150659_e, i, 3);
	    }

	    private boolean func_150647_c(int p_150647_1_, int p_150647_2_, int p_150647_3_)
	    {
	        BlockNuRailBase.NuRail rail = this.func_150654_a(new ChunkPosition(p_150647_1_, p_150647_2_, p_150647_3_));

	        if (rail == null)
	        {
	            return false;
	        }
	        else
	        {
	            rail.func_150651_b();
	            return rail.func_150649_b(this);
	        }
	    }

	    public void func_150655_a(boolean p_150655_1_, boolean p_150655_2_)
	    {
	        boolean flag2 = this.func_150647_c(this.field_150661_c, this.field_150658_d, this.field_150659_e - 1);
	        boolean flag3 = this.func_150647_c(this.field_150661_c, this.field_150658_d, this.field_150659_e + 1);
	        boolean flag4 = this.func_150647_c(this.field_150661_c - 1, this.field_150658_d, this.field_150659_e);
	        boolean flag5 = this.func_150647_c(this.field_150661_c + 1, this.field_150658_d, this.field_150659_e);
	        byte b0 = -1;

	        if ((flag2 || flag3) && !flag4 && !flag5)
	        {
	            b0 = 0;
	        }

	        if ((flag4 || flag5) && !flag2 && !flag3)
	        {
	            b0 = 1;
	        }

	        if (!this.field_150656_f)
	        {
	            if (flag3 && flag5 && !flag2 && !flag4)
	            {
	                b0 = 6;
	            }

	            if (flag3 && flag4 && !flag2 && !flag5)
	            {
	                b0 = 7;
	            }

	            if (flag2 && flag4 && !flag3 && !flag5)
	            {
	                b0 = 8;
	            }

	            if (flag2 && flag5 && !flag3 && !flag4)
	            {
	                b0 = 9;
	            }
	        }

	        if (b0 == -1)
	        {
	            if (flag2 || flag3)
	            {
	                b0 = 0;
	            }

	            if (flag4 || flag5)
	            {
	                b0 = 1;
	            }

	            if (!this.field_150656_f)
	            {
	                if (p_150655_1_)
	                {
	                    if (flag3 && flag5)
	                    {
	                        b0 = 6;
	                    }

	                    if (flag4 && flag3)
	                    {
	                        b0 = 7;
	                    }

	                    if (flag5 && flag2)
	                    {
	                        b0 = 9;
	                    }

	                    if (flag2 && flag4)
	                    {
	                        b0 = 8;
	                    }
	                }
	                else
	                {
	                    if (flag2 && flag4)
	                    {
	                        b0 = 8;
	                    }

	                    if (flag5 && flag2)
	                    {
	                        b0 = 9;
	                    }

	                    if (flag4 && flag3)
	                    {
	                        b0 = 7;
	                    }

	                    if (flag3 && flag5)
	                    {
	                        b0 = 6;
	                    }
	                }
	            }
	        }

	        if (b0 == 0 && canMakeSlopes)
	        {
	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c, this.field_150658_d + 1, this.field_150659_e - 1))
	            {
	                b0 = 4;
	            }

	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c, this.field_150658_d + 1, this.field_150659_e + 1))
	            {
	                b0 = 5;
	            }
	        }

	        if (b0 == 1 && canMakeSlopes)
	        {
	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c + 1, this.field_150658_d + 1, this.field_150659_e))
	            {
	                b0 = 2;
	            }

	            if (BlockNuRailBase.func_150049_b_(this.field_150660_b, this.field_150661_c - 1, this.field_150658_d + 1, this.field_150659_e))
	            {
	                b0 = 3;
	            }
	        }

	        if (b0 < 0)
	        {
	            b0 = 0;
	        }

	        this.func_150648_a(b0);
	        int i = b0;

	        if (this.field_150656_f)
	        {
	            i = this.field_150660_b.getBlockMetadata(this.field_150661_c, this.field_150658_d, this.field_150659_e) & 8 | b0;
	        }

	        if (p_150655_2_ || this.field_150660_b.getBlockMetadata(this.field_150661_c, this.field_150658_d, this.field_150659_e) != i)
	        {
	            this.field_150660_b.setBlockMetadataWithNotify(this.field_150661_c, this.field_150658_d, this.field_150659_e, i, 3);

	            for (int j = 0; j < this.field_150657_g.size(); ++j)
	            {
	                BlockNuRailBase.NuRail rail = this.func_150654_a((ChunkPosition)this.field_150657_g.get(j));

	                if (rail != null)
	                {
	                    rail.func_150651_b();

	                    if (rail.func_150649_b(this))
	                    {
	                        rail.func_150645_c(this);
	                    }
	                }
	            }
	        }
	    }
	}
}

package net.minecraft.entity.projectile;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityRubberBall extends EntityThrowable {

	public EntityRubberBall(World p_i1776_1_) {
		super(p_i1776_1_);
	}
	
	public EntityRubberBall(World p_i1774_1_, EntityLivingBase p_i1774_2_)
    {
        super(p_i1774_1_, p_i1774_2_);
    }

    public EntityRubberBall(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		this.motionY *= -0.75f;
		this.motionX *= 0.75f;
		this.motionZ *= 0.75f;
		if(this.motionY <= 0.25f && this.motionY >= -0.25f)
		{
			if (!this.worldObj.isRemote)
	        {
				this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(NuCraft.rubberBall)));
	            this.setDead();
	        }
		}
	}

}

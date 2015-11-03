package calmbit.nucraft.power;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityRubberBall;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RubberBallDispenseBehavior implements IBehaviorDispenseItem {

	@Override
	public ItemStack dispense(IBlockSource blockSource, ItemStack stack) 
	{
        EnumFacing var3 = BlockDispenser.func_149937_b(blockSource.getBlockMetadata());
        double var4 = blockSource.getX() + (double)var3.getFrontOffsetX();
        double var6 = blockSource.getY() + (double)var3.getFrontOffsetY();
        double var8 = blockSource.getZ() + (double)var3.getFrontOffsetZ();
        IProjectile iprojectile = new EntityRubberBall(blockSource.getWorld(), var4, var6, var8);
        iprojectile.setThrowableHeading((double)var3.getFrontOffsetX(), (double)((float)var3.getFrontOffsetY() + 0.1F), (double)var3.getFrontOffsetZ(), this.func_82500_b(), this.func_82498_a());
        blockSource.getWorld().spawnEntityInWorld((EntityRubberBall)iprojectile);
        blockSource.getWorld().playAuxSFX(1002, (int)var4, (int)var6, (int)var8, 0);
        stack.splitStack(1);
        return stack;
	}
	
	protected float func_82498_a()
    {
        return 6.0F;
    }

    protected float func_82500_b()
    {
        return 1.1F;
    }

}

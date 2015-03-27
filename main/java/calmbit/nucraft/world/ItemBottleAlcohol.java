package calmbit.nucraft.world;

import java.util.Iterator;
import java.util.List;

import calmbit.nucraft.core.NuCraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBottleAlcohol extends Item  {

	public static final int NUM_TYPES = 4;
	public IIcon[] textureList = new IIcon[NUM_TYPES];
	public ItemBottleAlcohol()
	{
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setUnlocalizedName("bottleAlcohol");
	}
	
	@Override
	public IIcon getIconFromDamage(int i)
	{
		return (i < NUM_TYPES) ? textureList[i] : textureList[0];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return this.getUnlocNameFromData(stack.getItemDamage());
	}
	
	public String getUnlocNameFromData(int meta)
	{
		return this.getUnlocalizedName() + this.getPrefixFromData(meta);
	}
	
	public String getPrefixFromData(int meta)
	{
		switch(meta)
		{
			default:
				return "Unknown";
			case 1:
				return "Ale";
			case 2:
				return "Lager";
			case 3:
				return "Cider";
		}
	}
	
	@Override
	public void registerIcons(IIconRegister register)
	{
		for(int i = 0;i < NUM_TYPES;i++)
		{
			textureList[i] = register.registerIcon("nucraftworld:alcohol" +  getPrefixFromData(i));
		}
		
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for(int i = 1;i < NUM_TYPES;i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.drink;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 32;
    }
    
    @Override
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        if (!p_77654_3_.capabilities.isCreativeMode)
        {
            --p_77654_1_.stackSize;
        }

        if (!p_77654_2_.isRemote)
        {
        	p_77654_3_.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
        	p_77654_3_.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1000, 0));
        }

        if (!p_77654_3_.capabilities.isCreativeMode)
        {
            if (p_77654_1_.stackSize <= 0)
            {
                return new ItemStack(Items.glass_bottle);
            }

            p_77654_3_.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }

        return p_77654_1_;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
            return p_77659_1_;
    }
    
    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        return false;
    }
}

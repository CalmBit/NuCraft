package calmbit.nucraft.rift;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelRiftDaemon extends ModelBase
{
  ModelRenderer bipedHead;
  ModelRenderer bipedBody;
  ModelRenderer bipedRightArm;
  ModelRenderer bipedLeftArm;
  ModelRenderer bipedRightLeg;
  ModelRenderer bipedLeftLeg;
  
  public ModelRiftDaemon()
  {
	  this.textureWidth = 128;
	  this.textureHeight = 128;
    
      bipedHead = new ModelRenderer(this, 0, 0);
      bipedHead.addBox(-4F, -8F, -4F, 14, 14, 14);
      bipedHead.setRotationPoint(-3F, -24F, -2.5F);
      bipedHead.setTextureSize(128, 128);
      bipedHead.mirror = true;
      setRotation(bipedHead, 0F, 0F, 0F);
      bipedBody = new ModelRenderer(this, 0, 61);
      bipedBody.addBox(-4F, 0F, -3F, 20, 23, 14);
      bipedBody.setRotationPoint(-6F, -18F, -3.5F);
      bipedBody.setTextureSize(128, 128);
      bipedBody.mirror = true;
      setRotation(bipedBody, 0F, 0F, 0F);
      bipedRightArm = new ModelRenderer(this, 56, 0);
      bipedRightArm.addBox(-3F, -2F, -2F, 8, 22, 10);
      bipedRightArm.setRotationPoint(-15F, -16F, -2.45F);
      bipedRightArm.setTextureSize(128, 128);
      bipedRightArm.mirror = true;
      setRotation(bipedRightArm, 0F, 0F, 0F);
      bipedLeftArm = new ModelRenderer(this, 56, 0);
      bipedLeftArm.addBox(-1F, -2F, -2F, 8, 22, 10);
      bipedLeftArm.setRotationPoint(11F, -16F, -2.5F);
      bipedLeftArm.setTextureSize(128, 128);
      bipedLeftArm.mirror = true;
      setRotation(bipedLeftArm, 0F, 0F, 0F);
      bipedRightLeg = new ModelRenderer(this, 0, 28);
      bipedRightLeg.addBox(-2F, 0F, -2F, 8, 19, 14);
      bipedRightLeg.setRotationPoint(-8F, 5F, -4.5F);
      bipedRightLeg.setTextureSize(128, 128);
      bipedRightLeg.mirror = true;
      setRotation(bipedRightLeg, 0F, 0F, 0F);
      bipedLeftLeg = new ModelRenderer(this, 0, 28);
      bipedLeftLeg.addBox(-2F, 0F, -2F, 8, 19, 14);
      bipedLeftLeg.setRotationPoint(4F, 5F, -4.5F);
      bipedLeftLeg.setTextureSize(128, 128);
      bipedLeftLeg.mirror = true;
      setRotation(bipedLeftLeg, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    bipedHead.render(f5);
    bipedBody.render(f5);
    bipedRightArm.render(f5);
    bipedLeftArm.render(f5);
    bipedRightLeg.render(f5);
    bipedLeftLeg.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
      this.bipedHead.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
      this.bipedHead.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
      this.bipedLeftLeg.rotateAngleX = -1.5F * this.func_78172_a(p_78087_1_, 13.0F) * p_78087_2_;
      this.bipedRightLeg.rotateAngleX = 1.5F * this.func_78172_a(p_78087_1_, 13.0F) * p_78087_2_;
      this.bipedLeftLeg.rotateAngleY = 0.0F;
      this.bipedRightLeg.rotateAngleY = 0.0F;
  }
  
  public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
  {
      EntityRiftDaemon entityRiftDaemon = (EntityRiftDaemon)p_78086_1_;
      int i = entityRiftDaemon.getAttackTimer();

      if (i > 0)
      {
          this.bipedRightArm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a((float)i - p_78086_4_, 10.0F);
          this.bipedLeftArm.rotateAngleX = -2.0F + 1.5F * this.func_78172_a((float)i - p_78086_4_, 10.0F);
      }
      else
      {
              this.bipedRightArm.rotateAngleX = (-0.2F + 1.5F * this.func_78172_a(p_78086_2_, 13.0F)) * p_78086_3_;
              this.bipedLeftArm.rotateAngleX = (-0.2F - 1.5F * this.func_78172_a(p_78086_2_, 13.0F)) * p_78086_3_;
      }
  }
  
  private float func_78172_a(float p_78172_1_, float p_78172_2_)
  {
      return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
  }

}

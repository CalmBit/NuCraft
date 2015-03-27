package calmbit.nucraft.rift;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMaercs extends ModelBiped
{
  
  public ModelMaercs()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      bipedHead = new ModelRenderer(this, 0, 0);
      bipedHead.addBox(-4F, -8F, -4F, 10, 8, 8);
      bipedHead.setRotationPoint(-1F, 6F, 0F);
      bipedHead.setTextureSize(64, 32);
      bipedHead.mirror = true;
      setRotation(bipedHead, 0F, 0F, 0F);
      bipedBody = new ModelRenderer(this, 16, 16);
      bipedBody.addBox(-4F, -2F, -2F, 12, 12, 4);
      bipedBody.setRotationPoint(-2F, 6F, 0F);
      bipedBody.setTextureSize(64, 32);
      bipedBody.mirror = true;
      setRotation(bipedBody, 0F, 0F, 0F);
      bipedRightArm = new ModelRenderer(this, 48, 16);
      bipedRightArm.addBox(-3F, -2F, -2F, 2, 12, 4);
      bipedRightArm.setRotationPoint(-5F, 8F, 0F);
      bipedRightArm.setTextureSize(64, 32);
      bipedRightArm.mirror = true;
      setRotation(bipedRightArm, 0F, 0F, 0F);
      bipedLeftArm = new ModelRenderer(this, 48, 16);
      bipedLeftArm.addBox(-1F, -2F, -2F, 2, 12, 4);
      bipedLeftArm.setRotationPoint(7F, 8F, 0F);
      bipedLeftArm.setTextureSize(64, 32);
      bipedLeftArm.mirror = true;
      setRotation(bipedLeftArm, 0F, 0F, 0F);
      bipedRightLeg = new ModelRenderer(this, 0, 16);
      bipedRightLeg.addBox(-2F, 6F, -2F, 4, 6, 4);
      bipedRightLeg.setRotationPoint(-2F, 18F, 0F);
      bipedRightLeg.setTextureSize(64, 32);
      bipedRightLeg.mirror = true;
      setRotation(bipedRightLeg, 0F, 0F, 0F);
      bipedLeftLeg = new ModelRenderer(this, 0, 16);
      bipedLeftLeg.addBox(-2F, 6F, -2F, 4, 6, 4);
      bipedLeftLeg.setRotationPoint(2F, 18F, 0F);
      bipedLeftLeg.setTextureSize(64, 32);
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
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}

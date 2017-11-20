package com.DragonFire.render.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelScarecrow.tcn - TechneToTabulaImporter
 * Created using Tabula 7.0.0
 */
public class ModelScarecrow extends ModelBase {
    public ModelRenderer Stick;
    public ModelRenderer Body;
    public ModelRenderer LStick;
    public ModelRenderer RStick;
    public ModelRenderer Head;
    public ModelRenderer Hatrim;
    public ModelRenderer Hatupper;

    public ModelScarecrow() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Hatrim = new ModelRenderer(this, 41, 17);
        this.Hatrim.setRotationPoint(-6.0F, -11.1F, -8.0F);
        this.Hatrim.addBox(0.0F, 0.0F, 0.0F, 13, 0, 11, 0.0F);
        this.LStick = new ModelRenderer(this, 0, 0);
        this.LStick.setRotationPoint(4.0F, 1.6F, -2.0F);
        this.LStick.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.setRotateAngle(LStick, 0.0F, -0.0F, 0.7330382858376184F);
        this.Stick = new ModelRenderer(this, 13, 70);
        this.Stick.setRotationPoint(-0.9F, 10.8F, -3.3F);
        this.Stick.addBox(0.0F, 0.0F, 0.0F, 3, 13, 3, 0.0F);
        this.Head = new ModelRenderer(this, 0, 24);
        this.Head.setRotationPoint(-5.0F, -10.9F, -7.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 11, 10, 9, 0.0F);
        this.Body = new ModelRenderer(this, 2, 46);
        this.Body.setRotationPoint(-4.0F, -1.5F, -5.0F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 9, 14, 6, 0.0F);
        this.RStick = new ModelRenderer(this, 0, 0);
        this.RStick.setRotationPoint(-2.0F, 1.6F, -2.0F);
        this.RStick.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.setRotateAngle(RStick, 0.0F, -0.0F, 2.2863813201125716F);
        this.Hatupper = new ModelRenderer(this, 47, 3);
        this.Hatupper.setRotationPoint(-5.0F, -13.8F, -7.0F);
        this.Hatupper.addBox(0.0F, 0.0F, 0.0F, 11, 3, 9, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Hatrim.render(f5);
        this.LStick.render(f5);
        this.Stick.render(f5);
        this.Head.render(f5);
        this.Body.render(f5);
        this.RStick.render(f5);
        this.Hatupper.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

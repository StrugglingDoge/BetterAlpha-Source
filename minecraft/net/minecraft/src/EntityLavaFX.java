package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class EntityLavaFX extends EntityFX
{

    public EntityLavaFX(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        motionX *= 0.80000001192092896D;
        motionY *= 0.80000001192092896D;
        motionZ *= 0.80000001192092896D;
        motionY = rand.nextFloat() * 0.4F + 0.05F;
        field_663_i = field_662_j = field_661_k = 1.0F;
        field_665_g *= rand.nextFloat() * 2.0F + 0.2F;
        field_674_a = field_665_g;
        field_666_f = (int)(16D / (Math.random() * 0.80000000000000004D + 0.20000000000000001D));
        field_9314_ba = false;
        field_670_b = 49;
    }

    public float getEntityBrightness(float f)
    {
        return 1.0F;
    }

    public void func_406_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        float f6 = ((float)field_667_e + f) / (float)field_666_f;
        field_665_g = field_674_a * (1.0F - f6 * f6);
        super.func_406_a(tessellator, f, f1, f2, f3, f4, f5);
    }

    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        if(field_667_e++ >= field_666_f)
        {
            setEntityDead();
        }
        float f = (float)field_667_e / (float)field_666_f;
        if(rand.nextFloat() > f)
        {
            worldObj.spawnParticle("smoke", posX, posY, posZ, motionX, motionY, motionZ);
        }
        motionY -= 0.029999999999999999D;
        moveEntity(motionX, motionY, motionZ);
        motionX *= 0.99900001287460327D;
        motionY *= 0.99900001287460327D;
        motionZ *= 0.99900001287460327D;
        if(onGround)
        {
            motionX *= 0.69999998807907104D;
            motionZ *= 0.69999998807907104D;
        }
    }

    private float field_674_a;
}

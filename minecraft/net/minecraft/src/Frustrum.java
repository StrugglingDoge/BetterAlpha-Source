package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class Frustrum
    implements ICamera
{

    public Frustrum()
    {
        field_593_a = ClippingHelperImplementation.func_1155_a();
    }

    public void func_343_a(double d, double d1, double d2)
    {
        field_592_b = d;
        field_595_c = d1;
        field_594_d = d2;
    }

    public boolean func_344_a(double d, double d1, double d2, double d3, double d4, double d5)
    {
        return field_593_a.func_1152_a(d - field_592_b, d1 - field_595_c, d2 - field_594_d, d3 - field_592_b, d4 - field_595_c, d5 - field_594_d);
    }

    public boolean func_342_a(AxisAlignedBB axisalignedbb)
    {
        return func_344_a(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
    }

    private ClippingHelper field_593_a;
    private double field_592_b;
    private double field_595_c;
    private double field_594_d;
}

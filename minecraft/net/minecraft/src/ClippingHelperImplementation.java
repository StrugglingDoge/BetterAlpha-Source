package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

public class ClippingHelperImplementation extends ClippingHelper
{

    public ClippingHelperImplementation()
    {
        field_1693_f = GLAllocation.createDirectFloatBuffer(16);
        field_1692_g = GLAllocation.createDirectFloatBuffer(16);
        field_1691_h = GLAllocation.createDirectFloatBuffer(16);
    }

    public static ClippingHelper func_1155_a()
    {
        field_1694_e.func_1153_b();
        return field_1694_e;
    }

    private void func_1154_a(float af[][], int i)
    {
        float f = MathHelper.sqrt_float(af[i][0] * af[i][0] + af[i][1] * af[i][1] + af[i][2] * af[i][2]);
        af[i][0] /= f;
        af[i][1] /= f;
        af[i][2] /= f;
        af[i][3] /= f;
    }

    private void func_1153_b()
    {
        field_1693_f.clear();
        field_1692_g.clear();
        field_1691_h.clear();
        GL11.glGetFloat(2983 /*GL_PROJECTION_MATRIX*/, field_1693_f);
        GL11.glGetFloat(2982 /*GL_MODELVIEW_MATRIX*/, field_1692_g);
        field_1693_f.flip().limit(16);
        field_1693_f.get(field_1687_b);
        field_1692_g.flip().limit(16);
        field_1692_g.get(field_1690_c);
        field_1689_d[0] = field_1690_c[0] * field_1687_b[0] + field_1690_c[1] * field_1687_b[4] + field_1690_c[2] * field_1687_b[8] + field_1690_c[3] * field_1687_b[12];
        field_1689_d[1] = field_1690_c[0] * field_1687_b[1] + field_1690_c[1] * field_1687_b[5] + field_1690_c[2] * field_1687_b[9] + field_1690_c[3] * field_1687_b[13];
        field_1689_d[2] = field_1690_c[0] * field_1687_b[2] + field_1690_c[1] * field_1687_b[6] + field_1690_c[2] * field_1687_b[10] + field_1690_c[3] * field_1687_b[14];
        field_1689_d[3] = field_1690_c[0] * field_1687_b[3] + field_1690_c[1] * field_1687_b[7] + field_1690_c[2] * field_1687_b[11] + field_1690_c[3] * field_1687_b[15];
        field_1689_d[4] = field_1690_c[4] * field_1687_b[0] + field_1690_c[5] * field_1687_b[4] + field_1690_c[6] * field_1687_b[8] + field_1690_c[7] * field_1687_b[12];
        field_1689_d[5] = field_1690_c[4] * field_1687_b[1] + field_1690_c[5] * field_1687_b[5] + field_1690_c[6] * field_1687_b[9] + field_1690_c[7] * field_1687_b[13];
        field_1689_d[6] = field_1690_c[4] * field_1687_b[2] + field_1690_c[5] * field_1687_b[6] + field_1690_c[6] * field_1687_b[10] + field_1690_c[7] * field_1687_b[14];
        field_1689_d[7] = field_1690_c[4] * field_1687_b[3] + field_1690_c[5] * field_1687_b[7] + field_1690_c[6] * field_1687_b[11] + field_1690_c[7] * field_1687_b[15];
        field_1689_d[8] = field_1690_c[8] * field_1687_b[0] + field_1690_c[9] * field_1687_b[4] + field_1690_c[10] * field_1687_b[8] + field_1690_c[11] * field_1687_b[12];
        field_1689_d[9] = field_1690_c[8] * field_1687_b[1] + field_1690_c[9] * field_1687_b[5] + field_1690_c[10] * field_1687_b[9] + field_1690_c[11] * field_1687_b[13];
        field_1689_d[10] = field_1690_c[8] * field_1687_b[2] + field_1690_c[9] * field_1687_b[6] + field_1690_c[10] * field_1687_b[10] + field_1690_c[11] * field_1687_b[14];
        field_1689_d[11] = field_1690_c[8] * field_1687_b[3] + field_1690_c[9] * field_1687_b[7] + field_1690_c[10] * field_1687_b[11] + field_1690_c[11] * field_1687_b[15];
        field_1689_d[12] = field_1690_c[12] * field_1687_b[0] + field_1690_c[13] * field_1687_b[4] + field_1690_c[14] * field_1687_b[8] + field_1690_c[15] * field_1687_b[12];
        field_1689_d[13] = field_1690_c[12] * field_1687_b[1] + field_1690_c[13] * field_1687_b[5] + field_1690_c[14] * field_1687_b[9] + field_1690_c[15] * field_1687_b[13];
        field_1689_d[14] = field_1690_c[12] * field_1687_b[2] + field_1690_c[13] * field_1687_b[6] + field_1690_c[14] * field_1687_b[10] + field_1690_c[15] * field_1687_b[14];
        field_1689_d[15] = field_1690_c[12] * field_1687_b[3] + field_1690_c[13] * field_1687_b[7] + field_1690_c[14] * field_1687_b[11] + field_1690_c[15] * field_1687_b[15];
        field_1688_a[0][0] = field_1689_d[3] - field_1689_d[0];
        field_1688_a[0][1] = field_1689_d[7] - field_1689_d[4];
        field_1688_a[0][2] = field_1689_d[11] - field_1689_d[8];
        field_1688_a[0][3] = field_1689_d[15] - field_1689_d[12];
        func_1154_a(field_1688_a, 0);
        field_1688_a[1][0] = field_1689_d[3] + field_1689_d[0];
        field_1688_a[1][1] = field_1689_d[7] + field_1689_d[4];
        field_1688_a[1][2] = field_1689_d[11] + field_1689_d[8];
        field_1688_a[1][3] = field_1689_d[15] + field_1689_d[12];
        func_1154_a(field_1688_a, 1);
        field_1688_a[2][0] = field_1689_d[3] + field_1689_d[1];
        field_1688_a[2][1] = field_1689_d[7] + field_1689_d[5];
        field_1688_a[2][2] = field_1689_d[11] + field_1689_d[9];
        field_1688_a[2][3] = field_1689_d[15] + field_1689_d[13];
        func_1154_a(field_1688_a, 2);
        field_1688_a[3][0] = field_1689_d[3] - field_1689_d[1];
        field_1688_a[3][1] = field_1689_d[7] - field_1689_d[5];
        field_1688_a[3][2] = field_1689_d[11] - field_1689_d[9];
        field_1688_a[3][3] = field_1689_d[15] - field_1689_d[13];
        func_1154_a(field_1688_a, 3);
        field_1688_a[4][0] = field_1689_d[3] - field_1689_d[2];
        field_1688_a[4][1] = field_1689_d[7] - field_1689_d[6];
        field_1688_a[4][2] = field_1689_d[11] - field_1689_d[10];
        field_1688_a[4][3] = field_1689_d[15] - field_1689_d[14];
        func_1154_a(field_1688_a, 4);
        field_1688_a[5][0] = field_1689_d[3] + field_1689_d[2];
        field_1688_a[5][1] = field_1689_d[7] + field_1689_d[6];
        field_1688_a[5][2] = field_1689_d[11] + field_1689_d[10];
        field_1688_a[5][3] = field_1689_d[15] + field_1689_d[14];
        func_1154_a(field_1688_a, 5);
    }

    private static ClippingHelperImplementation field_1694_e = new ClippingHelperImplementation();
    private FloatBuffer field_1693_f;
    private FloatBuffer field_1692_g;
    private FloatBuffer field_1691_h;

}

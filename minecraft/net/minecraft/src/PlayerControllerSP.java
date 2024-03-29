package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;

public class PlayerControllerSP extends PlayerController
{

    public PlayerControllerSP(Minecraft minecraft)
    {
        super(minecraft);
        field_1074_c = -1;
        field_1073_d = -1;
        field_1072_e = -1;
        field_1071_f = 0.0F;
        field_1070_g = 0.0F;
        field_1069_h = 0.0F;
        field_1068_i = 0;
    }

    public void flipPlayer(EntityPlayer entityplayer)
    {
        entityplayer.rotationYaw = -180F;
    }

    public boolean sendBlockRemoved(int i, int j, int k, int l)
    {
        int i1 = mc.theWorld.getBlockId(i, j, k);
        int j1 = mc.theWorld.getBlockMetadata(i, j, k);
        boolean flag = super.sendBlockRemoved(i, j, k, l);
        ItemStack itemstack = mc.thePlayer.getCurrentEquippedItem();
        boolean flag1 = mc.thePlayer.canHarvestBlock(Block.blocksList[i1]);
        if(itemstack != null)
        {
            itemstack.hitBlock(i1, i, j, k);
            if(itemstack.stackSize == 0)
            {
                itemstack.func_1097_a(mc.thePlayer);
                mc.thePlayer.destroyCurrentEquippedItem();
            }
        }
        if(flag && flag1)
        {
            Block.blocksList[i1].harvestBlock(mc.theWorld, i, j, k, j1);
        }
        return flag;
    }

    public void clickBlock(int i, int j, int k, int l)
    {
        int i1 = mc.theWorld.getBlockId(i, j, k);
        if(i1 > 0 && field_1071_f == 0.0F)
        {
            Block.blocksList[i1].onBlockClicked(mc.theWorld, i, j, k, mc.thePlayer);
        }
        if(i1 > 0 && Block.blocksList[i1].func_225_a(mc.thePlayer) >= 1.0F)
        {
            sendBlockRemoved(i, j, k, l);
        }
    }

    public void func_6468_a()
    {
        field_1071_f = 0.0F;
        field_1068_i = 0;
    }

    public void sendBlockRemoving(int i, int j, int k, int l)
    {
        if(field_1068_i > 0)
        {
            field_1068_i--;
            return;
        }
        if(i == field_1074_c && j == field_1073_d && k == field_1072_e)
        {
            int i1 = mc.theWorld.getBlockId(i, j, k);
            if(i1 == 0)
            {
                return;
            }
            Block block = Block.blocksList[i1];
            field_1071_f += block.func_225_a(mc.thePlayer);
            if(field_1069_h % 4F == 0.0F && block != null)
            {
                mc.sndManager.func_336_b(block.stepSound.func_1145_d(), (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, (block.stepSound.func_1147_b() + 1.0F) / 8F, block.stepSound.func_1144_c() * 0.5F);
            }
            field_1069_h++;
            if(field_1071_f >= 1.0F)
            {
                sendBlockRemoved(i, j, k, l);
                field_1071_f = 0.0F;
                field_1070_g = 0.0F;
                field_1069_h = 0.0F;
                field_1068_i = 5;
            }
        } else
        {
            field_1071_f = 0.0F;
            field_1070_g = 0.0F;
            field_1069_h = 0.0F;
            field_1074_c = i;
            field_1073_d = j;
            field_1072_e = k;
        }
    }

    public void func_6467_a(float f)
    {
        if(field_1071_f <= 0.0F)
        {
            mc.ingameGUI.field_6446_b = 0.0F;
            mc.field_6323_f.field_1450_i = 0.0F;
        } else
        {
            float f1 = field_1070_g + (field_1071_f - field_1070_g) * f;
            mc.ingameGUI.field_6446_b = f1;
            mc.field_6323_f.field_1450_i = f1;
        }
    }

    public float getBlockReachDistance()
    {
        return 4F;
    }

    public void func_717_a(World world)
    {
        super.func_717_a(world);
    }

    public void func_6474_c()
    {
        field_1070_g = field_1071_f;
        mc.sndManager.func_4033_c();
    }

    private int field_1074_c;
    private int field_1073_d;
    private int field_1072_e;
    private float field_1071_f;
    private float field_1070_g;
    private float field_1069_h;
    private int field_1068_i;
}

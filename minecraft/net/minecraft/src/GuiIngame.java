package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.gui.ModGui;
import me.strugglingdoge.betteralpha.gui.NahrFont;
import me.strugglingdoge.betteralpha.gui.NahrFont.FontType;
import me.strugglingdoge.betteralpha.util.ChatColor;
import me.strugglingdoge.betteralpha.util.Wrapper;
import net.minecraft.client.Minecraft;

public class GuiIngame extends Gui
{
	private NahrFont font;

    public GuiIngame(Minecraft minecraft)
    {
        chatMessageList = new ArrayList();
        rand = new Random();
        field_933_a = null;
        updateCounter = 0;
        field_9420_i = "";
        field_9419_j = 0;
        field_931_c = 1.0F;
        mc = minecraft;

        //TODO: BetterAlpha
        if (this.font == null)
            this.font = new NahrFont("Verdana", 18.0F);
    }

    public void renderGameOverlay(float f, boolean flag, int i, int j)
    {
        ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
        FontRenderer fontrenderer = mc.fontRenderer;
        mc.field_9243_r.func_905_b();
        GL11.glEnable(3042 /*GL_BLEND*/);
        if(mc.gameSettings.fancyGraphics)
        {
            func_4064_a(mc.thePlayer.getEntityBrightness(f), k, l);
        }
        ItemStack itemstack = mc.thePlayer.inventory.armorItemInSlot(3);
        if(!mc.gameSettings.thirdPersonView && itemstack != null && itemstack.itemID == Block.pumpkin.blockID)
        {
            func_4063_a(k, l);
        }
        float f1 = mc.thePlayer.field_4133_d + (mc.thePlayer.field_4134_c - mc.thePlayer.field_4133_d) * f;
        if(f1 > 0.0F)
        {
            func_4065_b(f1, k, l);
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/gui/gui.png"));
        InventoryPlayer inventoryplayer = mc.thePlayer.inventory;
        zLevel = -90F;
        drawTexturedModalRect(k / 2 - 91, l - 22, 0, 0, 182, 22);
        drawTexturedModalRect((k / 2 - 91 - 1) + inventoryplayer.currentItem * 20, l - 22 - 1, 0, 22, 24, 22);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/gui/icons.png"));
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(775, 769);
        drawTexturedModalRect(k / 2 - 7, l / 2 - 7, 0, 0, 16, 16);
        GL11.glDisable(3042 /*GL_BLEND*/);
        boolean flag1 = (mc.thePlayer.field_9306_bj / 3) % 2 == 1;
        if(mc.thePlayer.field_9306_bj < 10)
        {
            flag1 = false;
        }
        int i1 = mc.thePlayer.health;
        int j1 = mc.thePlayer.field_9335_K;
        rand.setSeed(updateCounter * 0x4c627);
        if(mc.field_6327_b.func_6469_d())
        {
            int k1 = mc.thePlayer.getPlayerArmorValue();
            for(int i2 = 0; i2 < 10; i2++)
            {
                int j3 = l - 32;
                if(k1 > 0)
                {
                    int k4 = (k / 2 + 91) - i2 * 8 - 9;
                    if(i2 * 2 + 1 < k1)
                    {
                        drawTexturedModalRect(k4, j3, 34, 9, 9, 9);
                    }
                    if(i2 * 2 + 1 == k1)
                    {
                        drawTexturedModalRect(k4, j3, 25, 9, 9, 9);
                    }
                    if(i2 * 2 + 1 > k1)
                    {
                        drawTexturedModalRect(k4, j3, 16, 9, 9, 9);
                    }
                }
                int i5 = 0;
                if(flag1)
                {
                    i5 = 1;
                }
                int k5 = (k / 2 - 91) + i2 * 8;
                if(i1 <= 4)
                {
                    j3 += rand.nextInt(2);
                }
                drawTexturedModalRect(k5, j3, 16 + i5 * 9, 0, 9, 9);
                if(flag1)
                {
                    if(i2 * 2 + 1 < j1)
                    {
                        drawTexturedModalRect(k5, j3, 70, 0, 9, 9);
                    }
                    if(i2 * 2 + 1 == j1)
                    {
                        drawTexturedModalRect(k5, j3, 79, 0, 9, 9);
                    }
                }
                if(i2 * 2 + 1 < i1)
                {
                    drawTexturedModalRect(k5, j3, 52, 0, 9, 9);
                }
                if(i2 * 2 + 1 == i1)
                {
                    drawTexturedModalRect(k5, j3, 61, 0, 9, 9);
                }
            }

            if(mc.thePlayer.isInsideOfMaterial(Material.water))
            {
                int j2 = (int)Math.ceil(((double)(mc.thePlayer.air - 2) * 10D) / 300D);
                int k3 = (int)Math.ceil(((double)mc.thePlayer.air * 10D) / 300D) - j2;
                for(int j5 = 0; j5 < j2 + k3; j5++)
                {
                    if(j5 < j2)
                    {
                        drawTexturedModalRect((k / 2 - 91) + j5 * 8, l - 32 - 9, 16, 18, 9, 9);
                    } else
                    {
                        drawTexturedModalRect((k / 2 - 91) + j5 * 8, l - 32 - 9, 25, 18, 9, 9);
                    }
                }

            }
        }
        GL11.glDisable(3042 /*GL_BLEND*/);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 1.0F, 0.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glPopMatrix();
        for(int l1 = 0; l1 < 9; l1++)
        {
            int k2 = (k / 2 - 90) + l1 * 20 + 2;
            int l3 = l - 16 - 3;
            func_554_a(l1, k2, l3, f);
        }

        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        if(Keyboard.isKeyDown(61))
        {
            fontrenderer.drawStringWithShadow((new StringBuilder()).append("Minecraft Alpha v1.2.6 (").append(mc.field_6292_I).append(")").toString(), 2, 2, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_6241_m(), 2, 12, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_6262_n(), 2, 22, 0xffffff);
            fontrenderer.drawStringWithShadow(mc.func_6245_o(), 2, 32, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l4 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l4 - l5;
            String s = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l2).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            drawString(fontrenderer, s, k - fontrenderer.getStringWidth(s) - 2, 2, 0xe0e0e0);
            s = (new StringBuilder()).append("Allocated memory: ").append((l4 * 100L) / l2).append("% (").append(l4 / 1024L / 1024L).append("MB)").toString();
            drawString(fontrenderer, s, k - fontrenderer.getStringWidth(s) - 2, 12, 0xe0e0e0);
            drawString(fontrenderer, (new StringBuilder()).append("x: ").append(mc.thePlayer.posX).toString(), 2, 64, 0xe0e0e0);
            drawString(fontrenderer, (new StringBuilder()).append("y: ").append(mc.thePlayer.posY).toString(), 2, 72, 0xe0e0e0);
            drawString(fontrenderer, (new StringBuilder()).append("z: ").append(mc.thePlayer.posZ).toString(), 2, 80, 0xe0e0e0);
        } else
        {
            fontrenderer.drawStringWithShadow("Minecraft Alpha v1.2.6", 2, 2, 0xffffff);
            fontrenderer.drawStringWithShadow("BetterAlpha v" + BetterAlpha.version, 2, 12, 0xffffff);
            //TODO: BetterAlpha gui
			ModGui.drawGUI(scaledresolution, font);
        }
        if(field_9419_j > 0)
        {
        	toolTipCountdown = 0;
            float f2 = (float)field_9419_j - f;
            int i3 = (int)((f2 * 256F) / 20F);
            if(i3 > 255)
            {
                i3 = 255;
            }
            if(i3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(k / 2, l - 48, 0.0F);
                GL11.glEnable(3042 /*GL_BLEND*/);
                GL11.glBlendFunc(770, 771);
                int i4 = Color.HSBtoRGB(f2 / 50F, 0.7F, 0.6F) & 0xffffff;
                fontrenderer.drawString(field_9420_i, -fontrenderer.getStringWidth(field_9420_i) / 2, -4, i4 + (i3 << 24));
                GL11.glDisable(3042 /*GL_BLEND*/);
                GL11.glPopMatrix();
            }
        }
        //TODO: WolfClient tooltip
        if(toolTipCountdown > 0)
        {
        	field_9419_j = 0;
        	float f2 = (float)toolTipCountdown - f;
            int i3 = (int)((f2 * 256F) / 20F);
            if(i3 > 255)
            {
                i3 = 255;
            }
			if (i3 > 0) {
				GL11.glPushMatrix();
				GL11.glTranslatef(k / 2, l - 48, 0.0F);
				GL11.glEnable(3042 /* GL_BLEND */);
				GL11.glBlendFunc(770, 771);
				fontrenderer.drawStringWithShadow(toolTip, -fontrenderer.getStringWidth(toolTip) / 2, -4, 0xffffff + (i3 << 24));
				GL11.glDisable(3042 /* GL_BLEND */);
				GL11.glPopMatrix();
			}
        }
        byte byte0 = 10;
        boolean flag2 = false;
        if(mc.currentScreen instanceof GuiChat)
        {
            byte0 = 20;
            flag2 = true;
        }
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, l - 48, 0.0F);
        int offset = 0;
        for(int j4 = 0; j4 < chatMessageList.size() && j4 < byte0; j4++)
        {
            if(((ChatLine)chatMessageList.get(j4)).updateCounter >= 200 && !flag2)
            {
                continue;
            }
            double d = (double)((ChatLine)chatMessageList.get(j4)).updateCounter / 200D;
            d = 1.0D - d;
            d *= 10D;
            if(d < 0.0D)
            {
                d = 0.0D;
            }
            if(d > 1.0D)
            {
                d = 1.0D;
            }
            d *= d;
            int i6 = (int)(255D * d);
            if(flag2)
            {
                i6 = 255;
            }
            if(i6 > 0)
            {
				if (Wrapper.getGameSettings().fancyChat) {
					offset += 3;
					byte byte1 = 2;
					int j6 = -j4 * 9;
					String s1 = ((ChatLine) chatMessageList.get(j4)).message;
					drawRect(byte1, j6 - 6 - offset, byte1 + 320, j6 + 6 - offset, i6 / 2 << 24);
					GL11.glEnable(3042 /* GL_BLEND */);
					font.drawString(s1, byte1 + 2, j6 - offset - 8.5F, FontType.NORMAL, 0xffffff + (i6 << 24));
				} else {
					byte byte1 = 2;
					int j6 = -j4 * 9;
					String s1 = ((ChatLine) chatMessageList.get(j4)).message;
					drawRect(byte1, j6 - 1, byte1 + 320, j6 + 8, i6 / 2 << 24);
					GL11.glEnable(3042 /* GL_BLEND */);
					fontrenderer.drawStringWithShadow(s1, byte1, j6, 0xffffff + (i6 << 24));
				}
            }
        }

        GL11.glPopMatrix();
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glDisable(3042 /*GL_BLEND*/);
    }

    private void func_4063_a(int i, int j)
    {
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void func_4064_a(float f, int i, int j)
    {
        f = 1.0F - f;
        if(f < 0.0F)
        {
            f = 0.0F;
        }
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        field_931_c += (double)(f - field_931_c) * 0.01D;
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(field_931_c, field_931_c, field_931_c, 1.0F);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void func_4065_b(float f, int i, int j)
    {
        f *= f;
        f *= f;
        f = f * 0.8F + 0.2F;
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, mc.renderEngine.getTexture("/terrain.png"));
        float f1 = (float)(Block.portal.blockIndexInTexture % 16) / 16F;
        float f2 = (float)(Block.portal.blockIndexInTexture / 16) / 16F;
        float f3 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16F;
        float f4 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, j, -90D, f1, f4);
        tessellator.addVertexWithUV(i, j, -90D, f3, f4);
        tessellator.addVertexWithUV(i, 0.0D, -90D, f3, f2);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, f1, f2);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void func_554_a(int i, int j, int k, float f)
    {
        ItemStack itemstack = mc.thePlayer.inventory.mainInventory[i];
        if(itemstack == null)
        {
            return;
        }
        float f1 = (float)itemstack.animationsToGo - f;
        if(f1 > 0.0F)
        {
            GL11.glPushMatrix();
            float f2 = 1.0F + f1 / 5F;
            GL11.glTranslatef(j + 8, k + 12, 0.0F);
            GL11.glScalef(1.0F / f2, (f2 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(j + 8), -(k + 12), 0.0F);
        }
        itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, j, k);
        if(f1 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, itemstack, j, k);
    }

    public void func_555_a()
    {
        if(field_9419_j > 0)
        {
            field_9419_j--;
        }
        //TODO: BetterAlpha
        if(toolTipCountdown > 0) {
        	toolTipCountdown--;
        }
        updateCounter++;
        for(int i = 0; i < chatMessageList.size(); i++)
        {
            ((ChatLine)chatMessageList.get(i)).updateCounter++;
        }

    }

    public void addChatMessage(String s)
    {
        int i;
        //TODO: BetterAlpha Chat
        int spacing = Wrapper.getGameSettings().fancyChat ? 350 : 320;
        for(; mc.fontRenderer.getStringWidth(s) > spacing; s = s.substring(i))
        {
            for(i = 1; i < s.length() && mc.fontRenderer.getStringWidth(s.substring(0, i + 1)) <= spacing; i++) { }
            addChatMessage(s.substring(0, i));
        }

        chatMessageList.add(0, new ChatLine(s));
        for(; chatMessageList.size() > 50; chatMessageList.remove(chatMessageList.size() - 1)) { }
    }

    public void func_553_b(String s)
    {
        field_9420_i = (new StringBuilder()).append("Now playing: ").append(s).toString();
        field_9419_j = 60;
    }
    
    public String getCurrentToolTip() {
    	return toolTip;
    }
    
    //TODO: BetterAlpha
    public void showTooltip(String s)
    {
    	toolTip = (new StringBuilder()).append("").append(s).toString();
        toolTipCountdown = 60;
    }

    private static RenderItem itemRenderer = new RenderItem();
    private java.util.List chatMessageList;
    private Random rand;
    private Minecraft mc;
    public String field_933_a;
    private int updateCounter;
    private String field_9420_i;
    private String toolTip;
    private int field_9419_j;
    private int toolTipCountdown;
    public float field_6446_b;
    float field_931_c;

}

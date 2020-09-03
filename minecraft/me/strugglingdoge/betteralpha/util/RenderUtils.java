package me.strugglingdoge.betteralpha.util;

import java.awt.Color;
import org.lwjgl.opengl.GL11;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderManager;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Tessellator;
import net.minecraft.src.AxisAlignedBB;

public class RenderUtils
{
    public static void drawRect(final float x1, final float y1, final float x2, final float y2, final int colour) {
        final float f = (colour >> 24 & 0xFF) / 255.0f;
        final float f2 = (colour >> 16 & 0xFF) / 255.0f;
        final float f3 = (colour >> 8 & 0xFF) / 255.0f;
        final float f4 = (colour & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(7);
        GL11.glVertex2d((double)x2, (double)y1);
        GL11.glVertex2d((double)x1, (double)y1);
        GL11.glVertex2d((double)x1, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawBorderedRect(final double i, final double j, final double k, final double l, final int m, final int color2) {
        drawBorderedRect((float)i, (float)j, (float)k, (float)l, 1.0f, m, color2);
    }
    
    public static void drawBorderedRect(final int x, final int y, final int x1, final int y1, final int bord, final int color) {
        drawRect((float)(x + 1), (float)(y + 1), (float)x1, (float)y1, color);
        drawVerticalLine(x, y, y1, bord);
        drawVerticalLine(x1, y, y1, bord);
        drawHorizontalLine(x + 1, y, x1, bord);
        drawHorizontalLine(x, y1, x1 + 1, bord);
    }
    
    public static void drawBorderedRect(final float x, final float y, final float x2, final float y2, final float l1, final int col1, final int col2) {
        drawRect(x, y, x2, y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GlStateManager.disableBlend();
    }
    
    public static ScaledResolution newScaledResolution() {
        return Wrapper.scaledResolution();
    }
    
    public static void drawVerticalLine(final int x, final int y, final int height, final int color) {
        drawRect((float)x, (float)y, (float)(x + 1), (float)height, color);
    }
    
    public static void drawHorizontalLine(final int x, final int y, final int width, final int color) {
        drawRect((float)x, (float)y, (float)width, (float)(y + 1), color);
    }

    public static void drawHollowRect(final double left, final double top, final double right, final double bottom, final float borderWidth, final int borderColor) {
        final float alpha = (borderColor >> 24 & 0xFF) / 255.0f;
        final float red = (borderColor >> 16 & 0xFF) / 255.0f;
        final float green = (borderColor >> 8 & 0xFF) / 255.0f;
        final float blue = (borderColor & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.func_179090_x();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);
        GL11.glEnable(2848);
        GL11.glLineWidth(borderWidth);
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(1);
        tessellator.addVertex(left, top, 0.0);
        tessellator.addVertex(left, bottom, 0.0);
        tessellator.addVertex(right, bottom, 0.0);
        tessellator.addVertex(right, top, 0.0);
        tessellator.addVertex(left, top, 0.0);
        tessellator.addVertex(right, top, 0.0);
        tessellator.addVertex(left, bottom, 0.0);
        tessellator.addVertex(right, bottom, 0.0);
        tessellator.draw();
        GL11.glLineWidth(2.0f);
        GL11.glDisable(2848);
        GlStateManager.func_179098_w();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
}

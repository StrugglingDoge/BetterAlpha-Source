package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class TexturePackDefault extends TexturePackBase
{

    public TexturePackDefault()
    {
        texturePackName = -1;
        texturePackFileName = "Default";
        firstDescriptionLine = "The default look of Minecraft";
        try
        {
            field_6490_f = ImageIO.read((TexturePackDefault.class).getResource("/pack.png"));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void func_6484_b(Minecraft minecraft)
    {
        if(field_6490_f != null)
        {
            minecraft.renderEngine.deleteTexture(texturePackName);
        }
    }

    public void func_6483_c(Minecraft minecraft)
    {
        if(field_6490_f != null && texturePackName < 0)
        {
            texturePackName = minecraft.renderEngine.allocateAndSetupTexture(field_6490_f);
        }
        if(field_6490_f != null)
        {
            minecraft.renderEngine.bindTexture(texturePackName);
        } else
        {
            GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.renderEngine.getTexture("/gui/unknown_pack.png"));
        }
    }

    private int texturePackName;
    private BufferedImage field_6490_f;
}

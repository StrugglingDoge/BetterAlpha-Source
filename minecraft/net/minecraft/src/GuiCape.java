package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.strugglingdoge.betteralpha.gui.ModGui;
import me.strugglingdoge.betteralpha.util.Wrapper;

public class GuiCape extends GuiScreen
{

    public GuiCape(GuiScreen guiscreen)
    {
        parentScreen = 0;
        updateCounter = guiscreen;
    }

    public void updateScreen()
    {
        parentScreen++;
        ((GuiButton)this.controlList.get(0)).enabled = (capeTextField.getText().length() > 0);
        capeTextField.updateCursorCounter();
    }

    public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiButton(2, width / 2 - 100, height / 4 + 95 + 12, "Clear Text"));
        controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120 + 12, "Set Cape"));
        controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 145 + 12, "Done"));
        String s = this.mc.gameSettings.userCapeUrl;
        this.capeTextField = new GuiTextField(this, this.fontRenderer, width / 2 - 100, height / 4 - 10 + 65, 200, 20, s);
        this.capeTextField.isFocused = true;
        this.capeTextField.setMaxStringLength(300);
      }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id == 1)
        {
            mc.displayGuiScreen(updateCounter);
        } else if (guibutton.id == 0) {
            Wrapper.getGameSettings().userCapeUrl = capeTextField.getText();
            if(Wrapper.theWorld() != null) {
            	Wrapper.thePlayer().updateCloak();
            	Wrapper.thePlayer().playerCloakUrl = Wrapper.getGameSettings().userCapeUrl;
            	loadDownloadableImageTexture(Wrapper.getGameSettings().userCapeUrl, null);
            }
          }else if(guibutton.id == 2) {
        	  	capeTextField.setText("");
          }
        }

    protected boolean loadDownloadableImageTexture(String s, String s1)
    {
        RenderEngine renderengine = Wrapper.getRenderManager().renderEngine;
        int i = renderengine.getTextureForDownloadableImage(s, s1);
        if(i >= 0)
        {
            renderengine.bindTexture(i);
            return true;
        } else
        {
            return false;
        }
    }
    
    private int func_4067_a(String s, int i)
    {
        try
        {
            return Integer.parseInt(s.trim());
        }
        catch(Exception exception)
        {
            return i;
        }
    }
    
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		this.capeTextField.mouseClicked(i, j, k);
	}

	protected void keyTyped(char c, int i) {
		if(i != Keyboard.KEY_SPACE)
			capeTextField.textboxKeyTyped(c, i);
        if(c == '\r')
        {
            actionPerformed((GuiButton)controlList.get(0));
        }
        ((GuiButton)controlList.get(0)).enabled = capeTextField.getText().length() > 0;
	}

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Set Cape URL", width / 2, (height / 4 - 60) + 20, 0xffffff);
        drawString(this.fontRenderer, "Set Cape URL:", width / 2 - 100, height / 4 + 40, 10526880);
        this.capeTextField.drawTextBox();
        super.drawScreen(i, j, f);
    }

    private GuiScreen updateCounter;
    private int parentScreen;
    private GuiTextField capeTextField;
}

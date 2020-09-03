package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import net.minecraft.client.Minecraft;

public class GuiMultiplayer extends GuiScreen
{

    public GuiMultiplayer(GuiScreen guiscreen)
    {
        parentScreen = 0;
        updateCounter = guiscreen;
    }

    public void updateScreen()
    {
        parentScreen++;
        field_22111_h.updateCursorCounter();
        usernameField.updateCursorCounter();
    }

    public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 120 + 12, "Connect"));
        controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 145 + 12, "Cancel"));
        String s = this.mc.gameSettings.field_12259_z.replaceAll("_", ":");
        ((GuiButton)this.controlList.get(0)).enabled = (s.length() > 0);
        this.field_22111_h = new GuiTextField(this, this.fontRenderer, width / 2 - 100, height / 4 - 10 + 65, 200, 20, s);
        this.field_22111_h.isFocused = true;
        this.field_22111_h.setMaxStringLength(128);
        (this.usernameField = new GuiTextField(this, this.fontRenderer, width / 2 - 100, height / 4 - 10 + 110, 200, 20, this.mc.field_6320_i.inventory)).setMaxStringLength(32);
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
            this.mc.field_6320_i.inventory = this.usernameField.getText().trim();
            String s = this.field_22111_h.getText().trim();
            this.mc.gameSettings.field_12259_z = this.field_22111_h.getText().trim();
            this.mc.gameSettings.saveOptions();
            String[] as = s.split(":");
            this.mc.displayGuiScreen(new GuiConnecting(this.mc, as[0], (as.length <= 1) ? 25565 : func_4067_a(as[1], 25565)));
            if (s.startsWith("[")) {
              int i = s.indexOf("]");
              if (i > 0) {
                String s1 = s.substring(1, i);
                String s2 = s.substring(i + 1).trim();
                if (s2.startsWith(":") && s2.length() > 0) {
                  s2 = s2.substring(1);
                  as = new String[2];
                  as[0] = s1;
                  as[1] = s2;
                } else {
                  as = new String[1];
                  as[0] = s1;
                } 
              } 
            } 
            if (as.length > 2) {
              as = new String[1];
              as[0] = s;
            } 
            this.mc.field_6320_i.inventory = this.usernameField.getText().trim();
            this.mc.displayGuiScreen(new GuiConnecting(this.mc, as[0], (as.length <= 1) ? 25565 : func_4067_a(as[1], 25565)));
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
		this.field_22111_h.mouseClicked(i, j, k);
		this.usernameField.mouseClicked(i, j, k);
	}

	protected void keyTyped(char c, int i) {
		field_22111_h.textboxKeyTyped(c, i);
        usernameField.textboxKeyTyped(c, i);
        if(c == '\r')
        {
            actionPerformed((GuiButton)controlList.get(0));
        }
        ((GuiButton)controlList.get(0)).enabled = field_22111_h.getText().length() > 0;
	}

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Play Multiplayer", width / 2, (height / 4 - 60) + 20, 0xffffff);
        drawString(fontRenderer, "Minecraft Multiplayer is currently not finished, but there", width / 2 - 140, (height / 4 - 60) + 60 + 0, 0xa0a0a0);
        drawString(fontRenderer, "is some buggy early testing going on.", width / 2 - 140, (height / 4 - 60) + 60 + 9, 0xa0a0a0);
        drawString(fontRenderer, "Enter the IP of a server to connect to it:", width / 2 - 140, (height / 4 - 60) + 60 + 36, 0xa0a0a0);
        drawString(this.fontRenderer, "Set Username:", width / 2 - 100, height / 4 + 85, 10526880);
        this.usernameField.drawTextBox();
        this.field_22111_h.drawTextBox();
        super.drawScreen(i, j, f);
    }

    private GuiScreen updateCounter;
    private int parentScreen;
    private GuiTextField usernameField;
    private GuiTextField field_22111_h;
}

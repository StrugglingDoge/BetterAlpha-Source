package me.strugglingdoge.betteralpha.command;

import me.strugglingdoge.betteralpha.util.Wrapper;
import net.minecraft.client.Minecraft;

public abstract class Command
{
    public Minecraft mc;
    
    public Command() {
        this.mc = Wrapper.getMinecraft();
    }
    
    public abstract String getAlias();
    
    public abstract String getDescription();
    
    public abstract String getSyntax();
    
    public abstract void onCommand(final String p0, final String[] p1) throws Exception;
}

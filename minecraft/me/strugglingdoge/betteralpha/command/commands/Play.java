package me.strugglingdoge.betteralpha.command.commands;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.command.Command;
import me.strugglingdoge.betteralpha.util.Wrapper;
import net.minecraft.src.Item;
import net.minecraft.src.ItemRecord;
import net.minecraft.src.SoundPoolEntry;

public class Play extends Command
{
	public static boolean failedToPlay = false;
	
    @Override
    public void onCommand(final String command, final String[] args) throws Exception {
    	failedToPlay = false;
        if (args.length > 2 || args.length < 1) {
            BetterAlpha.addChatMessage("Too many/little parameters" + args.length);
            BetterAlpha.addChatMessage("Usage: -play <bg/disc/list/stop> <soundtrack>");
        }
        else if(args.length == 2){
			if (args[1].contains("."))
				args[1] = args[1].substring(0, args[1].indexOf("."));
			if (args[0].equalsIgnoreCase("bg")) {
				SoundPoolEntry soundpoolentry = Wrapper.getMinecraft().sndManager.getSong(args[1]);
				if (soundpoolentry != null) {
					Wrapper.getMinecraft().sndManager.playAnySound(args[1], false);
					if(failedToPlay){
						BetterAlpha.addChatMessage("Failed to play music, " + args[1] + " is not a music track.");
						return;
					}
					Wrapper.getMinecraft().ingameGUI.func_553_b("C418 - " + args[1]);
					BetterAlpha.addChatMessage("Playing C418 - " + args[1]);
				}
			}else if(args[0].equalsIgnoreCase("disc")) {
				failedToPlay = true;
				for(Item disc : Item.itemsList) {
					if(disc instanceof ItemRecord)
						if(((ItemRecord)disc).recordName.equalsIgnoreCase(args[1])) {
							failedToPlay = false;
							Wrapper.getMinecraft().sndManager.playAnySound(args[1], true);
							Wrapper.getMinecraft().ingameGUI.func_553_b("C418 - " + args[1]);
							BetterAlpha.addChatMessage("Playing C418 - " + args[1]);
						}
				}
				
				if(failedToPlay) {
					BetterAlpha.addChatMessage("Failed to play music, " + args[1] + " is not a valid disc.");
				}
				
			}else if(args[0].equalsIgnoreCase("stop")) {
				BetterAlpha.addChatMessage("Stopping music...");
        		Wrapper.getMinecraft().sndManager.stopSound();
			}
        }else if(args.length == 1) {
        	if(args[0].equalsIgnoreCase("stop")) {
				BetterAlpha.addChatMessage("Stopping music...");
        		Wrapper.getMinecraft().sndManager.stopSound();
        	}else if(args[0].equalsIgnoreCase("list")) {
        		BetterAlpha.addChatMessage("Background: hal1, hal2, hal3, hal4, nuance1, nuance2, piano1, piano2, piano3");
        		BetterAlpha.addChatMessage("Records: 13, cat");
        	} else {
				BetterAlpha.addChatMessage("Too little parameters.");
	            BetterAlpha.addChatMessage("Usage: -play <bg/disc/list/stop> <soundtrack>");
        	}
        }
    }
    
    @Override
    public String getAlias() {
        return "play";
    }
    
    @Override
    public String getDescription() {
        return "Plays any in-game music specified.";
    }
    
    @Override
    public String getSyntax() {
        return "-play <bg/disc/list/stop> <soundtrack>";
    }
}

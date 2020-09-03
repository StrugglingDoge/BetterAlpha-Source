package me.strugglingdoge.betteralpha.command.commands;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.command.Command;
import me.strugglingdoge.betteralpha.util.Wrapper.PlayerWrapper;
import net.minecraft.src.MathHelper;

public class CopyCoordinates extends Command {
	@Override
	public void onCommand(final String command, final String[] args) throws Exception {
		if (args[0].length() == 0) {
			int x = MathHelper.floor_double(PlayerWrapper.getPosX());
			int y = MathHelper.floor_double(PlayerWrapper.getPosY());
			int z = MathHelper.floor_double(PlayerWrapper.getPosZ());
			StringSelection contents = new StringSelection(String.format("%s %s %s", x, y, z));
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(contents, null);

			BetterAlpha.addChatMessage("Copied your current coordinates to clipboard.");
		} else {
			BetterAlpha.addChatMessage("Too little/many parameters.");
			BetterAlpha.addChatMessage("Usage: -copycoord");
		}
	}

	@Override
	public String getAlias() {
		return "copycoord";
	}

	@Override
	public String getDescription() {
		return "Copies your current coordinates to the clipboard.";
	}

	@Override
	public String getSyntax() {
		return "-copycoord";
	}
}
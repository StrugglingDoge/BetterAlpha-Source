package me.strugglingdoge.betteralpha.command.commands;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.command.Command;
import me.strugglingdoge.betteralpha.command.CommandManager;

public class Help extends Command
{
    @Override
    public void onCommand(final String command, final String[] args) throws Exception {
        final int amountCommand = CommandManager.getInstance().getCommands().size();
        final int pages = (int)Math.ceil(amountCommand / 4.0);
        if (args[0].length() == 0 || Integer.parseInt(args[0]) < 1) {
            args[0] = "1";
        }
        else if (Integer.parseInt(args[0]) > pages) {
            args[0] = new StringBuilder().append(pages).toString();
        }
        if (args.length > 2) {
            BetterAlpha.addChatMessage("Too many/little parameters");
            BetterAlpha.addChatMessage("Usage: .help (page)");
        }
        else {
            int index = 0;
            int page = 1;
            int count = 0;
            if (args[0] != null || args[0] != "") {
                index = 4 * Integer.parseInt(args[0]) - 4;
                page = Integer.parseInt(args[0]);
            }
            BetterAlpha.addChatMessage("Available commands (" + page + " of " + pages + "):");
            for (final Command commands : CommandManager.getInstance().getCommands()) {
                if (index != 0) {
                    --index;
                }
                else {
                    if (count == 4) {
                        break;
                    }
                    BetterAlpha.addChatMessage(String.valueOf(commands.getSyntax()) + " - " + commands.getDescription());
                    ++count;
                }
            }
        }
    }
    
    @Override
    public String getAlias() {
        return "help";
    }
    
    @Override
    public String getDescription() {
        return "Provides a list of commands and their uses.";
    }
    
    @Override
    public String getSyntax() {
        return "-help (page)";
    }
}
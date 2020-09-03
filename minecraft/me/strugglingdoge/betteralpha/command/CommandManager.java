package me.strugglingdoge.betteralpha.command;

import java.util.ArrayList;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.command.commands.CopyCoordinates;
import me.strugglingdoge.betteralpha.command.commands.Help;
import me.strugglingdoge.betteralpha.command.commands.Play;

public class CommandManager
{
    static ArrayList<Command> commands;
    public static volatile CommandManager instance;
    protected ArrayList<String> aliases;
    
    public CommandManager() {
        commands = new ArrayList<Command>();
        aliases = new ArrayList<String>();
        addCommand(new Help());
        addCommand(new Play());
        addCommand(new CopyCoordinates());
    }
    
    public void addCommand(final Command c) {
        commands.add(c);
        aliases.add(c.getAlias());
    }
    
    public ArrayList<Command> getCommands() {
        return commands;
    }

	public ArrayList<String> getAliases() {
        return aliases;
    }
    
    public static CommandManager getInstance() {
        CommandManager result = CommandManager.instance;
        if (result == null) {
            synchronized (CommandManager.class) {
                result = CommandManager.instance;
                if (result == null) {
                    result = (CommandManager.instance = new CommandManager());
                }
            }
        }
        return result;
    }
    
    public void callCommand(final String input) {
        final String[] split = input.split(" ");
        final String command = split[0];
        final String args = input.substring(command.length()).trim();
        for (final Command c : this.getCommands()) {
            if (c.getAlias().equalsIgnoreCase(command)) {
                try {
                    c.onCommand(args, args.split(" "));
                }
                catch (Exception e) {
                    BetterAlpha.addChatMessage("Wrong usage! try:");
                    BetterAlpha.addChatMessage(c.getSyntax());
                    System.out.println(String.valueOf(c.getAlias()) + " exception thrown: " + e.getCause());
                }
                return;
            }
        }
        BetterAlpha.addChatMessage("Unknown command please type \"-help\" for help.");
    }
}

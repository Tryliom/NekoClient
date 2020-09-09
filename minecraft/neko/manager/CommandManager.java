package neko.manager;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.commands.Help;
import neko.command.commands.Mode;

public class CommandManager {

	private ArrayList<Command> commands = new ArrayList<Command>();
	
	public CommandManager() {
		this.commands.add(new Help());
		this.commands.add(new Mode());
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

}

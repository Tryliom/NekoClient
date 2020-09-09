package neko.manager;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.commands.Help;

public class CommandManager {

	private ArrayList<Command> commands = new ArrayList<Command>();
	
	public CommandManager() {
		this.commands.add(new Help());
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

}

package neko.manager;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.commands.Help;
import neko.command.commands.Mode;
import neko.command.commands.MyIp;
import neko.command.commands.MyPing;
import neko.command.commands.VelocityCoeff;
import neko.command.commands.VelocityHorizontal;
import neko.command.commands.VelocityVertical;

public class CommandManager {

	private ArrayList<Command> commands = new ArrayList<Command>();
	
	public CommandManager() {
		this.commands.add(new Help());
		this.commands.add(new Mode());
		this.commands.add(new VelocityCoeff());
		this.commands.add(new VelocityHorizontal());
		this.commands.add(new VelocityVertical());
		this.commands.add(new MyPing());
		this.commands.add(new MyIp());
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

}

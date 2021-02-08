package neko.command.commands;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.Utils;

public class Help extends Command {

	public Help() {
		super("help", "help [Nom de cheat]", "Affiche l'aide pour les commandes", 1, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		ArrayList<Command> list = new ArrayList<Command>();
		String prefix = client.prefixCmd;
		
		if (args.length == 1) {
			list = Utils.getCommandByType(CommandType.Other);
		} else if (Utils.isCommandType(args[1])) {
			list = Utils.getCommandByType(CommandType.valueOf(Utils.capitalize(args[1].toLowerCase())));
		} else {
			Utils.addChat("§cCette aide n'existe pas");
		}
		
		Utils.addChat(Utils.sep);
		for (Command cmd : list) {
			Utils.addChat(prefix + cmd.getHelp() + ": " + Utils.setColor(cmd.getDescription(), "§7"));
		}
	}

}

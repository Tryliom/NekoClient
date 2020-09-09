package neko.command.commands;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.Utils;

public class Help extends Command {

	public Help() {
		super("help", "Affiche l'aide pour les commandes", "help [Nom de cheat]", 1, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		ArrayList<Command> list = new ArrayList<Command>();
		String prefix = client.prefixCmd;
		
		if (args.length == 1) {
			list = Utils.getCommandByType(CommandType.Other);
		} else if (CommandType.valueOf(args[1].toLowerCase()) != null) {
			list = Utils.getCommandByType(CommandType.valueOf(args[1].toLowerCase()));
		} else {
			Utils.addChat("§cCette aide n'existe pas");
		}
		
		for (Command cmd : list) {
			Utils.addChat(prefix + cmd.getHelp() + ": " + cmd.getDescription());
		}
	}

}

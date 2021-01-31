package neko.command.commands;

import neko.Client;
import neko.command.Command;
import neko.command.CommandType;
import neko.module.other.ModeType;
import neko.utils.Utils;

public class Mode extends Command {

	public Mode() {
		super("mode", "mode [player:mob:all]", "Change le type d'entité qui est attaqué ou visible par des cheats", 2, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		String name = Utils.capitalize(args[1].toLowerCase());
		
		if (ModeType.valueOf(name) != null) {
			Client.getNeko().mode = ModeType.valueOf(name);
			Utils.addChat("§aLe mode  est changé en "+name+" !");
		} else {
			Utils.addError("Le mode ["+name+"] est inconnu");
		}
	}
}

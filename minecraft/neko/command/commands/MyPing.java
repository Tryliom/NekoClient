package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.Utils;

public class MyPing extends Command {

	public MyPing() {
		super("myping", "myping", "Affiche votre ping", 1, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		int ping = Utils.getPlayerPing(mc.session.getUsername());
		
		Utils.addChat("Votre ping: §7"+ping);
	}
}

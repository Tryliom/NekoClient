package neko.command.commands;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.CommandType;
import neko.dtb.RequestThread;

public class Unmute extends Command {

	public Unmute() {
		super("unmute", "unmute [nom du joueur]", "unmute le joueur (Droit admin requis)", 2, CommandType.Admin);
	} 
	
	public void onCommand(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add(args[1]);
		new RequestThread("unmute", list).start();
	}
}

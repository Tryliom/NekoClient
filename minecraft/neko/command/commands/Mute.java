package neko.command.commands;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.CommandType;
import neko.dtb.RequestThread;

public class Mute extends Command {

	public Mute() {
		super("mute", "mute [nom du joueur] [Raison]", "Mute le joueur (Droit admin requis)", 3, CommandType.Admin);
	} 
	
	public void onCommand(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add(args[1]);
		String s = args[2];
		if (args.length>3) {
			for (int i=3;i<args.length;i++)
				s+=" "+args[i];
		}
		list.add(s);
		new RequestThread("mute", list).start();
	}
}

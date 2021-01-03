package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.misc.Ping;
import neko.utils.Utils;

public class PingDelay extends Command {

	public PingDelay() {
		super("ping delay", "ping delay [Valeur]", "Change le delai, le lag que vous voulez faire voir pour les autres", 3, CommandType.Ping);
	}
	
	public void onCommand(String[] args) {
		Ping p = Ping.getPing();
		if (Utils.isInteger(args[2])) {
			int delay = Integer.parseInt(args[2]);
			
			p.setDelay(delay<0 ? 0 : delay);
			Utils.addChat("§aPing mis à "+p.getDelay()+" !");
		} else {
			Utils.addError("Nombre entier demandé !");
		}
	}
}

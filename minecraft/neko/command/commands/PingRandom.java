package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.misc.Ping;
import neko.utils.Utils;

public class PingRandom extends Command {

	public PingRandom() {
		super("ping random", "ping random", "Active le ping aléatoire", 2, CommandType.Ping);
	}
	
	public void onCommand(String[] args) {
		Ping p = Ping.getPing();
		if (p.isRandom()) {
			Utils.addChat("§cPing Random désactivé");
		} else {
			Utils.addChat("§aPing Random activé");
		}
		p.setRandom(!p.isRandom());
	}
}

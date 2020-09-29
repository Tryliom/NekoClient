package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.misc.Ping;
import neko.utils.Utils;

public class PingFreezer extends Command {

	public PingFreezer() {
		super("ping freezer", "ping freezer", "N'envoie plus son ping lorsque activé", 2, CommandType.Ping);
	}
	
	public void onCommand(String[] args) {
		Ping p = Ping.getPing();
		if (p.isFreezer()) {
			Utils.addChat("§cPing Freezer désactivé");
		} else {
			Utils.addChat("§aPing Freezer activé");
		}
		p.setFreezer(!p.isFreezer());
	}
}

package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.misc.Ping;
import neko.utils.Utils;

public class PingFreezer extends Command {

	public PingFreezer() {
		super("ping freezer", "ping freezer", "N'envoie plus son ping lorsque activ�", 2, CommandType.Ping);
	}
	
	public void onCommand(String[] args) {
		Ping p = Ping.getPing();
		if (p.isFreezer()) {
			Utils.addChat("�cPing Freezer d�sactiv�");
		} else {
			Utils.addChat("�aPing Freezer activ�");
		}
		p.setFreezer(!p.isFreezer());
	}
}

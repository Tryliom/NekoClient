package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.special.Limit;
import neko.utils.Utils;

public class LimitPacket extends Command {

	public LimitPacket() {
		super("limit packet", "limit packet [Nombre de packet max]", "Définit la limite des packets envoyés", 3, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		Limit.getInstance().setLimit(Integer.parseInt(args[2]));
		Utils.addChat("§aLa limite de paquet à été mise à " + Limit.getInstance().getLimit() + " packet/sec");
	}
}

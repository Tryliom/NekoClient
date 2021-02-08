package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.special.Limit;
import neko.utils.Utils;

public class LimitPacket extends Command {

	public LimitPacket() {
		super("limit packet", "limit packet [Nombre de packet max]", "D�finit la limite des packets envoy�s", 3, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		Limit.getInstance().setLimit(Integer.parseInt(args[2]));
		Utils.addChat("�aLa limite de paquet � �t� mise � " + Limit.getInstance().getLimit() + " packet/sec");
	}
}

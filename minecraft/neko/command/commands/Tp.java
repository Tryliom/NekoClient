package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.utils.TpUtils;
import neko.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;

public class Tp extends Command {

	public Tp() {
		super("tp", "tp [Pseudo d'un joueur]", "Téléporte jusqu'au joueur spécifié", 2, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		TpUtils tp = new TpUtils();
		EntityPlayer en = Utils.getPlayer(args[1]);
		if (en!=null)
			tp.doTpAller(en, en.posX, en.posY, en.posZ, false, 1);
		else
			Utils.addChat("§cErreur, ce joueur n'existe pas");
	}
}

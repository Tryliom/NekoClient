package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.player.Velocity;
import neko.utils.Utils;

public class VelocityCoeff extends Command {

	public VelocityCoeff() {
		super("velocity coeff", "velocity coeff [Valeur]", "Change le coefficient de knockback en horizontal et vertical", 3, CommandType.Velocity);
	}
	
	public void onCommand(String[] args) {
		Velocity velo = Velocity.getVelocity();
		if (Utils.isDouble(args[2])) {
			velo.setVcoeff(Double.parseDouble(args[2]));
			velo.setHcoeff(Double.parseDouble(args[2]));
			Utils.addChat(Utils.setColor("Le coefficient de knockback a été changé en "+args[2]+" !", "§a"));
		} else {
			Utils.addError("La valeur demandée doit être un nombre");
		}
	}
}

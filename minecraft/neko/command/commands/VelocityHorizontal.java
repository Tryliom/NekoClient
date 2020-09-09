package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.player.Velocity;
import neko.utils.Utils;

public class VelocityHorizontal extends Command {

	public VelocityHorizontal() {
		super("velocity horizontal", "velocity horizontal [Valeur]", "Change le coefficient de knockback en horizontal", 3, CommandType.Velocity);
	}
	
	public void onCommand(String[] args) {
		Velocity velo = Velocity.getVelocity();
		if (Utils.isDouble(args[2])) {
			velo.setHcoeff(Double.parseDouble(args[2]));
			Utils.addChat(Utils.setColor("Le coefficient de knockback horizontal a été changé en "+args[2]+" !", "§a"));
		} else {
			Utils.addError("La valeur demandée doit être un nombre");
		}
	}
}

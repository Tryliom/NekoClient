package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.player.Velocity;
import neko.utils.Utils;

public class VelocityVertical extends Command {

	public VelocityVertical() {
		super("velocity vertical", "velocity vertical [Valeur]", "Change le coefficient de knockback en vertical", 3, CommandType.Velocity);
	}
	
	public void onCommand(String[] args) {
		Velocity velo = Velocity.getVelocity();
		if (Utils.isDouble(args[2])) {
			velo.setVcoeff(Double.parseDouble(args[2]));
			Utils.addChat(Utils.setColor("Le coefficient de knockback vertical a �t� chang� en "+args[2]+" !", "�a"));
		} else {
			Utils.addError("La valeur demand�e doit �tre un nombre");
		}
	}
}

package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.hide.Friends;
import neko.utils.Utils;

public class FriendTeam extends Command {

	public FriendTeam() {
		super("friend team", "friend team", "Active l'ajout automatique des amis dans sa team", 2, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		if (!Friends.team) {
			Utils.addChat("§aAjout auto de player dans votre team activé !");
		} else if (Friends.team) {
			Utils.addChat("§cAjout auto de player dans votre team désactivé !");
		}
		
		Friends.team = !Friends.team;
	}
}

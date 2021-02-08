package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.hide.Friends;
import neko.utils.Utils;

public class FriendClear extends Command {

	public FriendClear() {
		super("friend clear", "friend clear", "Supprime toute la liste d'amis", 2, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		Utils.addChat("§aTa liste d'amis a été clear !");
		Friends.friend.clear();
		Utils.saveFriends();
	}
}

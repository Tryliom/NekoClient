package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.hide.Friends;

public class FriendAdd extends Command {

	public FriendAdd() {
		super("friend add", "friend add [Pseudo]", "Ajoute un ami", 3, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		String name = args[2];
		Friends.updateFriend(name);
	}
}

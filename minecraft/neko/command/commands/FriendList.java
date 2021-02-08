package neko.command.commands;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.hide.Friends;
import neko.utils.Utils;

public class FriendList extends Command {

	public FriendList() {
		super("friend list", "friend list", "Liste les amis", 2, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		ArrayList<String> friends = Friends.friend;
		
		if (friends.size() == 0)
			Utils.addChat("Aucun amis");
		
		friends.forEach(Utils::addChat);
	}
}

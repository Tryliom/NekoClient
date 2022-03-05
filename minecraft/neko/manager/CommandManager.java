package neko.manager;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.commands.*;

public class CommandManager {

	private ArrayList<Command> commands = new ArrayList<Command>();
	
	public CommandManager() {
		this.commands.add(new Help());
		this.commands.add(new Mode());
		this.commands.add(new VelocityCoeff());
		this.commands.add(new VelocityHorizontal());
		this.commands.add(new VelocityVertical());
		this.commands.add(new MyPing());
		this.commands.add(new ProxySet());
		this.commands.add(new ProxyReset());
		this.commands.add(new PingDelay());
		this.commands.add(new PingRandom());
		this.commands.add(new PingFreezer());
		this.commands.add(new Tp());
		this.commands.add(new FriendTeam());
		this.commands.add(new FriendAdd());
		this.commands.add(new FriendClear());
		this.commands.add(new FriendList());
		this.commands.add(new FriendRadius());
		this.commands.add(new LimitPacket());
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

}

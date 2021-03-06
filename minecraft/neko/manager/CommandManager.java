package neko.manager;

import java.util.ArrayList;

import neko.command.Command;
import neko.command.commands.FriendAdd;
import neko.command.commands.FriendClear;
import neko.command.commands.FriendList;
import neko.command.commands.FriendRadius;
import neko.command.commands.FriendTeam;
import neko.command.commands.Help;
import neko.command.commands.LimitPacket;
import neko.command.commands.Mode;
import neko.command.commands.MyPing;
import neko.command.commands.PingDelay;
import neko.command.commands.PingFreezer;
import neko.command.commands.PingRandom;
import neko.command.commands.ProxyReset;
import neko.command.commands.ProxySet;
import neko.command.commands.Tp;
import neko.command.commands.UnlockAll;
import neko.command.commands.VelocityCoeff;
import neko.command.commands.VelocityHorizontal;
import neko.command.commands.VelocityVertical;

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
		this.commands.add(new UnlockAll());
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

}

package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.lock.Lock;
import neko.manager.ModuleManager;

public class UnlockAll extends Command {

	public UnlockAll() {
		super("unlockall", "unlockall", "Dévérouille tous les cheats et tout", 1, CommandType.Other);
	}
	
	public void onCommand(String[] args) {
		for (Lock lock : ModuleManager.Lock) {
			lock.setLock(false);
		}
	}
}

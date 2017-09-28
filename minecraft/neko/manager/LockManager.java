package neko.manager;

import neko.lock.Lock;

public class LockManager {
	private static LockManager instance = null;
	
	public static LockManager getManager() {
		return instance==null ? instance = new LockManager() : instance;
	}
	
	public Lock getLockByName(String name) {
		for (Lock l : ModuleManager.Lock) {
			if (l.getName().equalsIgnoreCase(name)) {
				return l;
			}
		}
		return null;
	}
	
	public Lock getLockByCmdName(String name) {
		for (Lock l : ModuleManager.Lock) {
			if (l.getCmdName().equalsIgnoreCase(name)) {
				return l;
			}
		}
		return null;
	}
	
	public boolean setLock(String name, String cmdName, String nameUnlock) {
		for (Lock l : ModuleManager.Lock) {
			if (l.getName().equalsIgnoreCase(name)) {
				l.setCmdName(cmdName);
				l.setNameUnlock(nameUnlock);
				return true;
			}
		}
		return false;
	}
	
	public boolean isALockCmdLocked(String cmdname) {
		for (Lock l : ModuleManager.Lock) {
			if (l.getCmdName().equalsIgnoreCase(cmdname) && l.isLock()) {
				return true;
			}
		}
		return false;
	}
}

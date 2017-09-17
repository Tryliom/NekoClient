package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;

public class Ping extends Module {
	private boolean freezer=false;
	private boolean random=false;
	private int delay = 500;
	public static boolean isOn;
	private static Ping instance;
	
	public static Ping getPing() {
		return instance;
	}
	
	public Ping() {
		super("Ping", -1, Category.MISC);
		instance=this;
	}
	
	public void onEnabled() {		
		isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		isOn=false;
		super.onDisabled();
	}	

	public boolean isFreezer() {
		return freezer;
	}

	public void setFreezer(boolean freezer) {
		this.freezer = freezer;
	}

	public boolean isRandom() {
		return random;
	}

	public void setRandom(boolean random) {
		this.random = random;
	}		
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}

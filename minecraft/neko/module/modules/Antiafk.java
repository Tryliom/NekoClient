package neko.module.modules;

import neko.module.Category;
import neko.module.Module;

public class Antiafk extends Module {
	private int delay=0;
	private static Antiafk instance=null;
	private int sec=20;
	
	public Antiafk() {
		super("Antiafk", -1, Category.OTHER);
		instance=this;
	}
	
	public static Antiafk getInstance() {
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
			u.addChat("�a�oAntiafk activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
			u.addChat("�c�oAntiafk d�sactiv� !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (delay>sec*20) {
			delay=0;
			mc.thePlayer.jump();
		} else
			delay++;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}	
	
	
	
}

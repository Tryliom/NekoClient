package neko.module.modules.misc;

import neko.module.Category;
import neko.module.Module;

public class Antiafk extends Module {
	private int delay=0;
	private static Antiafk instance=null;
	private int sec=20;
	
	public Antiafk() {
		super("Antiafk", -1, Category.MISC, false);
		instance=this;
	}
	
	public static Antiafk getInstance() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Temps entre les sauts:§7 "+sec+"sec";
	}
	
	public void onUpdate() {
		if (delay>sec*20) {
			delay=0;
			mc.thePlayer.jump();
			double value1 = Math.random()*1 - 0.5;
			double value2 = Math.random()*1 - 0.5;
			mc.thePlayer.rotationPitch+=value1;
			mc.thePlayer.rotationYaw+=value2;
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

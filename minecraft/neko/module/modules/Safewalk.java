package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Safewalk extends Module {
	
	public static boolean isOn=false;
	
	public Safewalk() {
		super("Safewalk", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		if (u.display)
		u.addChat("§a§oSafewalk activé !");
		this.isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oSafewalk désactivé !");
		this.isOn=false;
		super.onDisabled();
	}
	

}


package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Safewalk extends Module {
	
	public static boolean isOn=false;
	
	public Safewalk() {
		super("Safewalk", -1, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		this.isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		this.isOn=false;
		super.onDisabled();
	}
	

}


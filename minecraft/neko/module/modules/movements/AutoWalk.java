package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class AutoWalk extends Module {	
	public static boolean isOn=false;
	
	public AutoWalk() {
		super("AutoWalk", -1, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		this.isOn=true;
		super.onEnabled();
	}
	
	public void onUpdate() {
		mc.gameSettings.keyBindForward.pressed = true;
	}
	
	public void onDisabled() {
		this.isOn=false;
		mc.gameSettings.keyBindForward.pressed = false;
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
}
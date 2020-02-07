package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;

public class AutoWalk extends Module {	
	
	public AutoWalk() {
		super("AutoWalk", -1, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onUpdate() {
		mc.gameSettings.keyBindForward.pressed = true;
	}
	
	public void onDisabled() {
		mc.gameSettings.keyBindForward.pressed = false;
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
}
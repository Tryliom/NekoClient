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
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
}
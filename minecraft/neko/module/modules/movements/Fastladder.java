package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;

public class Fastladder extends Module {
	public Fastladder() {
		super("Fastladder", -1, Category.MOVEMENT, false);
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

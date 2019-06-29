package neko.module.modules.combat;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Crit extends Module {
	
	public Crit() {
		super("Crit", -1, Category.COMBAT, false);
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
	
	public void onClick() {
		if (mc.pointedEntity!=null)
			u.crit();
	}
}

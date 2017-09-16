package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Crit extends Module {
	
	public Crit() {
		super("Crit", Keyboard.KEY_NONE, Category.COMBAT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	
	
	public void onClick() {
		if (mc.pointedEntity!=null)
			u.crit();
	}
}

package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Crit extends Module {
	
	public Crit() {
		super("Crit", Keyboard.KEY_NONE, Category.COMBAT);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("�a�oCrit activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oCrit d�sactiv� !");
		super.onDisabled();
	}	
	
	public void onClick() {
		if (mc.pointedEntity!=null)
			u.crit();
	}
}

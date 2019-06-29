package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Power extends Module {
	public static int p=1;

	public Power() {
		super("Power", -1, Category.RENDER, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Nombre de particules:§7 "+p;
	}
	
	public void onUpdate() {		
		Utils.doPower(p);		
	}
}

package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;

public class Jesus extends Module {
	public Jesus() {
		super("Jesus", -1, Category.PLAYER, false);
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

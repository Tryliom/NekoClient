package neko.module.modules;

import neko.module.Category;
import neko.module.Module;

public class Jesus extends Module {
	public Jesus() {
		super("Jesus", -1, Category.PLAYER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oJesus activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oJesus désactivé !");
		super.onDisabled();
	}
}

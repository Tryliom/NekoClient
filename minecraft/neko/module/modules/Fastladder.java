package neko.module.modules;

import neko.module.Category;
import neko.module.Module;

public class Fastladder extends Module {
	public Fastladder() {
		super("Fastladder", -1, Category.MOVEMENT);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oFastladder activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oFastladder désactivé !");
		super.onDisabled();
	}
}

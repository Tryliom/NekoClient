package neko.module.modules;

import neko.module.Category;
import neko.module.Module;

public class Autorespawn extends Module {
	
	public Autorespawn() {
		super("Autorespawn", -1, Category.PLAYER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oAutorespawn activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oAutorespawn désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (mc.thePlayer!=null && mc.thePlayer.getHealth()<=0)
		mc.thePlayer.respawnPlayer();		
	}
}

package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;

public class Autorespawn extends Module {
	
	public Autorespawn() {
		super("Autorespawn", -1, Category.PLAYER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (mc.thePlayer!=null && mc.thePlayer.getHealth()<=0)
		mc.thePlayer.respawnPlayer();		
	}
}

package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;

public class Automine extends Module {

	public Automine() {
		super("Automine", -1, Category.PLAYER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}

	@Override
	public void onUpdate() {
		if(mc.gameSettings.keyBindAttack.pressed == false){
			mc.sendClickBlockToController(true);
		}
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
}
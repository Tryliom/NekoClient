package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Jetpack extends Module {

	public Jetpack() {
		super("Jetpack", -1, Category.MOVEMENT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public String getValues() {
		return "";
	}
	
	public void onUpdate() {
		
		if (mc.gameSettings.keyBindJump.pressed) {
			mc.thePlayer.jump();
		}
	}
}

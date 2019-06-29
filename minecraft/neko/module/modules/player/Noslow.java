
package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Noslow extends Module {
	public static boolean isOn=false;

	public Noslow() {
		super("Noslow", -1, Category.PLAYER, false);
	}
	
	public void onEnabled() {
		isOn=true;
	}

	public void onDisabled() {
		isOn=false;
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {
		if (mc.thePlayer.onGround && mc.gameSettings.keyBindJump.pressed) {
			if (mc.thePlayer.isInWater()) {
				mc.thePlayer.jump();
			}
		}
		
	}
}

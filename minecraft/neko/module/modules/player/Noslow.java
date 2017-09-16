
package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Noslow extends Module {
	public static boolean isOn=false;
	Minecraft mc = Minecraft.getMinecraft();

	public Noslow() {
		super("Noslow", -1, Category.PLAYER);
	}
	
	public void onEnabled() {
		isOn=true;
	}

	public void onDisabled() {
		isOn=false;
	}
	
	public void onUpdate() {
		if (!this.getToggled()) {
			return;
		}
		if (mc.thePlayer.onGround && mc.gameSettings.keyBindJump.pressed) {
			if (mc.thePlayer.isInWater()) {
				mc.thePlayer.jump();
			}
		}
		
	}
}

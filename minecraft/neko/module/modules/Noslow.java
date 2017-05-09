
package neko.module.modules;

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
		if (u.display)
		u.addChat("§a§oNoslow activé !");
	}

	public void onDisabled() {
		isOn=false;
		if (u.display)
		u.addChat("§c§oNoslow désactivé !");
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

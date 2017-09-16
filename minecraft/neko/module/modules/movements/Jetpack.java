package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Jetpack extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static int count=0;
	int Tcount=0;
	int delay=0;

	public Jetpack() {
		super("Jetpack", -1, Category.MOVEMENT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (!this.getToggled()) {
			return;
		}
		
		if (mc.gameSettings.keyBindJump.pressed) {
			mc.thePlayer.jump();
		}
	}
}

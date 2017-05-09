package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", Keyboard.KEY_B, Category.RENDER);

	}

	
	public void onEnabled() {
		if (u.display)
		u.addChat("§a§oFullbright activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oFullbright désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (this.getToggled() || Xray.isOn == true) {
			while (Minecraft.getMinecraft().gameSettings.gammaSetting < 100F) {
				Minecraft.getMinecraft().gameSettings.gammaSetting += 0.5F;
			}
		} else {
			while (Minecraft.getMinecraft().gameSettings.gammaSetting > 1F) {
				Minecraft.getMinecraft().gameSettings.gammaSetting -= 0.5F;
			}
		}
	}
}

package neko.module.modules.render;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", Keyboard.KEY_B, Category.RENDER);

	}

	public void onEnabled() {
		while (mc.gameSettings.gammaSetting < 101F) {
			mc.gameSettings.gammaSetting += 0.5F;
		}
		super.onEnabled();
	}
	
	public void onDisabled() {
		while (mc.gameSettings.gammaSetting > 1F) {
			mc.gameSettings.gammaSetting -= 0.5F;
		}
		super.onDisabled();
	}
}

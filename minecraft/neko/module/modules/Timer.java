package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Timer extends Module {
	public static float time = 2.0F;
	
	public Timer () {
		super("Timer", Keyboard.KEY_Y, Category.OTHER);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		net.minecraft.util.Timer.timerSpeed = 1.0F;
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (!this.getToggled()) {
 			return;
		}
		net.minecraft.util.Timer.timerSpeed = time;
	}

}

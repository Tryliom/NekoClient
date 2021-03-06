package neko.module.modules.misc;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Timer extends Module {
	public static float time = 2.0F;
	
	public Timer () {
		super("Timer", Keyboard.KEY_Y, Category.MISC, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		net.minecraft.util.Timer.timerSpeed = 1.0F;
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "?6Speed:?7 "+time;
	}
	
	public void onUpdate() {
		net.minecraft.util.Timer.timerSpeed = time;
	}

}

package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {	
	public static boolean sprint=false;
	
	public Sprint() {
		super("Sprint", Keyboard.KEY_K, Category.MOVEMENT);
	}
	
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (mc.thePlayer.isMovingXZ())
			mc.thePlayer.setSprinting(true);					
		sprint=true;
	}
}

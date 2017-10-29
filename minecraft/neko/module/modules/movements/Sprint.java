package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {	
	
	public Sprint() {
		super("Sprint", Keyboard.KEY_K, Category.MOVEMENT);
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
		if (mc.thePlayer.isMovingXZ())
			mc.thePlayer.setSprinting(true);
	}
}

package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class FastPlace extends Module {
	
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_U, Category.PLAYER, false);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {
		mc.rightClickDelayTimer = 0;
		mc.leftClickCounter = 0;	
	}

}

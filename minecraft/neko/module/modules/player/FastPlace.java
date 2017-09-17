package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class FastPlace extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_U, Category.PLAYER);
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
		mc.rightClickDelayTimer = 0;
		mc.leftClickCounter = 0;	
	}

}

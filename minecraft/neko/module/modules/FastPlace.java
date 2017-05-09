package neko.module.modules;

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
		if (u.display)
		u.addChat("�a�oFastPlace activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oFastPlace d�sactiv� !");
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

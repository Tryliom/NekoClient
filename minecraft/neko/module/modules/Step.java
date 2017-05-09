package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;


public class Step extends Module {
	Minecraft mc = Minecraft.getMinecraft();

	public static double step=50;
	
	public Step() {
		super("Step", Keyboard.KEY_I, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		if (u.display)
		u.addChat("§a§oStep activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oStep désactivé !");
		Minecraft.getMinecraft().thePlayer.stepHeight = 0.5F;
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (this.getToggled()) {
			Minecraft.getMinecraft().thePlayer.stepHeight = (float) step;				    		
		}
		
	}	

}
package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Longjump extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static float speed=4F;
	
	public Longjump() {
		super("Longjump", -1, Category.MOVEMENT);
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
		
		if (mc.thePlayer.isAirBorne) {
		      mc.thePlayer.jumpMovementFactor = speed/100;
		}
		
	}
}

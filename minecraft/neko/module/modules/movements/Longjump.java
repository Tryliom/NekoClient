package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Longjump extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static float speed=4F;
	
	public Longjump() {
		super("Longjump", -1, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Speed:§7 "+speed;
	}
	
	public void onUpdate() {		
		if (mc.thePlayer.isAirBorne) {
		      mc.thePlayer.jumpMovementFactor = speed/100;
		}
		
	}
}

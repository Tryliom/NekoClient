package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

public class Flight extends Module {	
	public static double speed=1.0;
	public static boolean blink=false;
	boolean v;
	
	public Flight() {
		super("Flight", Keyboard.KEY_R, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {
		if (blink && !Utils.isToggle("Blink")) {
			Utils.toggleModule("Blink");
			v=false;
		} else if (blink && Utils.isToggle("Blink"))
			v=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (blink && !v)
			Utils.toggleModule("Blink");
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Speed:§7 "+speed+"\n"
				+ "§6Mode Blink:§7 "+Utils.displayBool(blink);
	}
	
	public void onUpdate() {
		u.doFlame(1);
		Minecraft mc = Minecraft.getMinecraft();
		mc.thePlayer.noClip = true;
		mc.thePlayer.fallDistance = 0;
		mc.thePlayer.onGround = false;
		
		mc.thePlayer.capabilities.isFlying = false;		
		mc.thePlayer.motionX = 0;
		mc.thePlayer.motionY = 0;
		mc.thePlayer.motionZ = 0;
		mc.thePlayer.fallDistance = 0.0F;

		
		mc.thePlayer.jumpMovementFactor = (float) speed;
		if(mc.gameSettings.keyBindJump.pressed) {
			mc.thePlayer.motionY+=speed;
		}
		
		if(mc.gameSettings.keyBindSneak.pressed) {
			mc.thePlayer.motionY-=speed;
		}
		
		
	}
	
	public static String getBlink() {
		return blink ? "on" : "off";
	}

}

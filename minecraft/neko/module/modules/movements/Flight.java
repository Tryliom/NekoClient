package neko.module.modules.movements;

import neko.module.modules.movements.Blink;
import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Flight extends Module {
	
	public static double speed=1.0;
	public static boolean blink=false;
	boolean v;
	
	public Flight() {
		super("Flight", Keyboard.KEY_R, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		if (blink && !Blink.isOn) {
			Blink.isOn=true; 
			v=false;
		} else if (blink && Blink.isOn)
			v=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (blink)
			Blink.isOn=v;		
		super.onDisabled();
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

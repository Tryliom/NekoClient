package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Dolphin extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static double dolph=1.15;

	public Dolphin() {
		super("Dolphin", Keyboard.KEY_J, Category.MOVEMENT);
	}
		
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public String getValues() {
		return "§6Speed:§7 "+dolph;
	}
	
	public void onUpdate() {
		if(!this.getToggled()) {
			return;
		}
		if (mc.thePlayer.handleWaterMovement() && mc.thePlayer.isInWater()) {
			mc.thePlayer.motionX*=dolph;
			mc.thePlayer.motionZ*=dolph;
		}
		
		double speed = 0.07D;
		if(mc.gameSettings.keyBindJump.pressed && mc.thePlayer.isInWater())
			mc.thePlayer.motionY += speed;
		if(mc.gameSettings.keyBindSneak.pressed && mc.thePlayer.isInWater())
			mc.thePlayer.motionY -= speed;
		
	}
}


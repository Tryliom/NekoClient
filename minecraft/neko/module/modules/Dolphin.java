package neko.module.modules;

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
		if (u.display)
		u.addChat("�a�oDolphin activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oDolphin d�sactiv� !");
		super.onDisabled();
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


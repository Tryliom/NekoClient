package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class FlyBypass extends Module {
	
	public FlyBypass() {
		super("FlyBypass", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		if (u.display)
		u.addChat("§a§oFlyBypass activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oFlyBypass désactivé !");	
		super.onDisabled();
	}
	
	public void onUpdate() {
		mc.thePlayer.motionY = 0;
		
		if (mc.thePlayer.isMovingXZ()) {
			mc.thePlayer.motionX*=0.03D;
			mc.thePlayer.motionZ*=0.03D;
		}			
		
		if(mc.gameSettings.keyBindJump.pressed) {
			mc.thePlayer.motionY=0.05D;
		}
		
		if(mc.gameSettings.keyBindSneak.pressed) {
			mc.thePlayer.motionY=-0.05D;
		}				
	}

}

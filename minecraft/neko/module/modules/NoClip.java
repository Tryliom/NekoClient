package neko.module.modules;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class NoClip extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	
	public static float speed = 0.5F;
	public NoClip() {
		super("NoClip", Keyboard.KEY_N, Category.MOVEMENT);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		Minecraft.getMinecraft().thePlayer.noClip = false;
		super.onDisabled();
	}
	
	public void onUpdate() {
		Minecraft mc = Minecraft.getMinecraft();
		mc.thePlayer.noClip = true;
		mc.thePlayer.fallDistance = 0;
		mc.thePlayer.onGround = false;
		
		mc.thePlayer.capabilities.isFlying = false;		
		mc.thePlayer.motionX = 0;
		mc.thePlayer.motionY = 0;
		mc.thePlayer.motionZ = 0;
		if (mc.thePlayer.fallDistance>=2) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
		
		
		mc.thePlayer.jumpMovementFactor = speed;
		if (mc.gameSettings.keyBindJump.pressed) {	    	
			mc.thePlayer.motionY += 1.0D;
	    }
	    if (mc.gameSettings.keyBindSneak.pressed) {
	    	mc.thePlayer.motionY -= 1.0D;
	    }
		
		if (mc.gameSettings.keyBindForward.pressed)
	    {
	      float var1 = mc.thePlayer.rotationYaw * 0.01745329F;
	      mc.thePlayer.motionX -= MathHelper.sin(var1) * 0.5F;
	      mc.thePlayer.motionZ += MathHelper.cos(var1) * 0.5F;
	    }
	    if (mc.gameSettings.keyBindBack.pressed)
	    {
	      float var1 = mc.thePlayer.rotationYaw * 0.01745329F;
	      mc.thePlayer.motionX -= MathHelper.sin(var1) * -0.5F;
	      mc.thePlayer.motionZ += MathHelper.cos(var1) * -0.5F;
	    }
		
		
		
	}
		
	}
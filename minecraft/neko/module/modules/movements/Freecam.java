package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldSettings.GameType;

public class Freecam extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	private EntityOtherPlayerMP fakePlayer = null;
	public static float speed=1.0F;
	  private double oldX;
	  private double oldY;
	  private double oldZ;
	
	public Freecam() {
		super("Freecam", Keyboard.KEY_L, Category.MOVEMENT);
	}
	
	public void onEnabled() {		
	    this.oldX = mc.thePlayer.posX;
	    this.oldY = mc.thePlayer.posY;
	    this.oldZ = mc.thePlayer.posZ;
	    this.fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
	    this.fakePlayer.clonePlayer(mc.thePlayer, true);
	    this.fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
	    this.fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
	    mc.theWorld.addEntityToWorld(-2, this.fakePlayer);
		super.onEnabled();
	}
	
	public void onDisabled() {
	    mc.thePlayer.setPositionAndRotation(this.oldX, this.oldY, this.oldZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
	    mc.theWorld.removeEntityFromWorld(-2);
	    this.fakePlayer = null;
	    mc.renderGlobal.loadRenderers();
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "�6Speed:�7 "+speed;
	}
	
	public void onUpdate() {		
		mc.thePlayer.noClip=true;
		mc.thePlayer.motionX = 0.0D;
	    mc.thePlayer.motionY = 0.0D;
	    mc.thePlayer.motionZ = 0.0D;
	    mc.thePlayer.onGround=false;
	    mc.thePlayer.jumpMovementFactor = speed;
	    if (mc.gameSettings.keyBindJump.pressed) {
	    	if (mc.thePlayer.isCollidedVertically) {
		    	  mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY+1, mc.thePlayer.posZ);
		      } else
	      mc.thePlayer.motionY += speed;
	    }
	    if (mc.gameSettings.keyBindSneak.pressed) {
	      if (mc.thePlayer.isCollidedVertically) {
	    	  mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ);
	      } else
	    	mc.thePlayer.motionY -= speed;
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

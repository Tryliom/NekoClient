package neko.module.modules.movements;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;

public class Blink extends Module {
	private EntityOtherPlayerMP fakePlayer = null;
	public static ArrayList<Packet> packet = new ArrayList<Packet>();
	public static boolean isOn;
	public static double lastPosX;
	public static double lastPosY;
	public static double lastPosZ;
	public static float lastYaw;
	public static float lastPitch;
	public static boolean onGround;
	
	public Blink() {
		super("Blink", -1, Category.MOVEMENT, false);
	}
	
	public void onEnabled() {		
		lastPosX=mc.thePlayer.posX;
		lastPosY=mc.thePlayer.posY;
		lastPosZ=mc.thePlayer.posZ;
		lastYaw=mc.thePlayer.rotationYaw;
		lastPitch=mc.thePlayer.rotationPitch;
		onGround=mc.thePlayer.onGround;
	    this.fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
	    this.fakePlayer.clonePlayer(mc.thePlayer, true);
	    this.fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
	    this.fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
	    mc.theWorld.addEntityToWorld(-2, this.fakePlayer);
	    isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
	    mc.theWorld.removeEntityFromWorld(-2);
	    this.fakePlayer = null;
	    isOn=false;
	    sendPacket();
	    mc.theWorld.updateEntities();
		super.onDisabled();
	}		
	
	public void setValues() {
		this.values = "";
	}
	
	public void sendPacket() {
		if (packet!=null && packet.size()!=0) {
			for (int k=0;k<packet.size();k++) {
				mc.thePlayer.sendQueue.addToSendQueue(packet.get(k));
			}
			packet.clear();
			return;
		}
	}
}

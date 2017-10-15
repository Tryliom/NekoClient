package neko.module.modules.special;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;

public class PunKeel extends Module {
	public static ArrayList<Packet> packet = new ArrayList<Packet>();
	public static boolean isOn;
	private int count;
	public static Double delay = 1.0;
	public static boolean attack = false;
	
	public PunKeel() {
		super("PunKeel", -1, Category.Special);
	}
	
	public void onEnabled() {	
	    isOn=true;
	    this.count = 0;	    
		super.onEnabled();
	}
	
	public void onDisabled() {
	    sendPacket();
	    isOn=false;
	    mc.theWorld.updateEntities();
		super.onDisabled();
	}		
	
	public void onUpdate() {
		if (this.count>this.delay*20 || mc.thePlayer.getHealth()<0) {
			this.count=0;
			this.sendPacket();
		} else
			this.count++;
	}
	
	public void sendPacket() {
		this.isOn=false;
		if (packet!=null && packet.size()!=0) {
			for (int k=0;k<packet.size();k++) {
				mc.thePlayer.sendQueue.addToSendQueue(packet.get(k));
			}
			packet.clear();
			this.isOn=true;
			return;
		}
		this.isOn=true;
	}
}

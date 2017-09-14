package neko.module.modules;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;

public class Cancer extends Module {
	public static ArrayList<Packet> packet = new ArrayList<Packet>();
	public static boolean isOn;
	private int count;
	public static Double delay = 1.0;
	public static boolean attack = false;
	
	public Cancer() {
		super("Cancer", -1, Category.Special);
	}
	
	public void onEnabled() {	
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		} 
		if (u.display)
			u.addChat("§a§oCancer activé !");
	    isOn=true;
	    this.count = 0;	    
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oCancer désactivé !");
	    sendPacket();
	    isOn=false;
	    mc.theWorld.updateEntities();
		super.onDisabled();
	}		
	
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
		if (this.count>this.delay*20) {
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

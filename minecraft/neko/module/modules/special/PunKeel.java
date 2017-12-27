package neko.module.modules.special;

import java.util.ArrayList;
import java.util.Vector;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.Packet;

public class PunKeel extends Module {
	public static ArrayList<Packet> packet = new ArrayList<Packet>();
	public static boolean isOn;
	private int count;
	public static Double delay = 1.0;
	public static Double delay_ = 1.0;
	public static Boolean random = false;
	public static Vector<Double> rDelay = new Vector<>();
	public static boolean attack = false;
	
	public PunKeel() {
		super("PunKeel", -1, Category.Special);
	}
	
	public void onEnabled() {	
		if (random)
			this.delay_ = rDelay.lastElement()-rDelay.firstElement()+(Math.random()*rDelay.firstElement());
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
	
	public void setValues() {
		this.values = "§6Delais:§7 "+delay+"sec\n"
				+ "§6Attack: "+Utils.displayBool(attack)+"\n"
				+ "§6Random: "+Utils.displayBool(random)+"\n"
				+ "§6Delais Min - Max: §e"+rDelay.firstElement()+" §7- §e"+rDelay.lastElement();
	}
	
	public void onUpdate() {
		if (random) {
			if (this.count>this.delay_*20 || mc.thePlayer.getHealth()<0) {
				this.delay_ = rDelay.lastElement()-rDelay.firstElement()+(Math.random()*rDelay.firstElement());
				this.count=0;
				this.sendPacket();
			} else 
				this.count++;
		} else if (this.count>this.delay*20 || mc.thePlayer.getHealth()<0) {
			this.count=0;
			this.sendPacket();
		} else
			this.count++;
	}
	
	public void onAction() {
		if (!this.getToggled() && this.isOn)
			this.isOn = false;
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

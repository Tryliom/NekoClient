package neko.module.modules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.PacketPing;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class Ping extends Module {
	private boolean freezer=false;
	private boolean random=false;
	private int delay = 500;
	public static boolean isOn;
	private static Ping instance;
	
	public static Ping getPing() {
		return instance;
	}
	
	public Ping() {
		super("Ping", -1, Category.OTHER);
		instance=this;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oPing activé !");
		isOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oPing désactivé !");
		isOn=false;
		super.onDisabled();
	}	

	public boolean isFreezer() {
		return freezer;
	}

	public void setFreezer(boolean freezer) {
		this.freezer = freezer;
	}

	public boolean isRandom() {
		return random;
	}

	public void setRandom(boolean random) {
		this.random = random;
	}		
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}

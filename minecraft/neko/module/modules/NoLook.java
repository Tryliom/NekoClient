package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;

public class NoLook extends Module {
	private static NoLook instance;
	private float pitch;
	private float yaw;
	
	public static NoLook getLook() {
		return instance;
	}
	
	public NoLook() {
		super("NoLook", -1, Category.Special);
		instance = this;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oNoLook activé !");
		pitch = mc.thePlayer.rotationPitch;
		yaw = mc.thePlayer.rotationYaw;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oNoLook désactivé !");
		super.onDisabled();
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	
}

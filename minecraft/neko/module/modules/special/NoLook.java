package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;

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
		pitch = mc.thePlayer.rotationPitch;
		yaw = mc.thePlayer.rotationYaw;
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
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

package neko.module.modules.render;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.guicheat.clickgui.util.SettingsUtil;
import neko.module.Category;
import neko.module.Module;

public class Rotator extends Module {
	private Double rotate = 0d;
	private Double speed = 20d;
	private static Rotator instance = null;
	
	public Rotator() {
		super("Rotator", -1, Category.RENDER, false);
	}
	
	public static Rotator getRotator() {
		return instance == null ? instance = new Rotator() : instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		this.rotate += this.speed;
		if (this.rotate == 380)
			this.rotate = 0d;
	}
	
	@Override
	public void setup() {
		Client.getNeko().settingsManager.rSetting(new Setting("RotatorSpeed", this.getRotator(), this.getRotator().getSpeed(), 0, 50, true));
		
		super.setup();
	}


	public Double getRotate() {
		return this.rotate;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
		SettingsUtil.setRotatorSpeed(speed);
	}
}

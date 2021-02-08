package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.guicheat.clickgui.util.SettingsUtil;
import neko.module.Category;
import neko.module.Module;

public class Velocity extends Module {
	public static double hcoeff = 1;
	public static double vcoeff = 1;
	private static Velocity instance;
	
	public Velocity() {
		super("Velocity", Keyboard.KEY_P, Category.PLAYER, false);
		instance=this;
	}
	
	public static Velocity getVelocity() {
		return instance;
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}

	public void setValues() {
		this.values = "§6Horizontal:§7 "+hcoeff*100+"%\n"
				+ "§6Vertical:§7 "+(vcoeff*100)+"%\n"
				+ "§6Coeff. moyen:§7 "+((vcoeff+hcoeff)/2)*100+"%";
	}
	
	@Override
	public void setup() {
		
		Client.getNeko().settingsManager.rSetting(new Setting("Velocity_Horizontal", this, this.hcoeff, 1, 100, false));
		Client.getNeko().settingsManager.rSetting(new Setting("Velocity_Vertical", this, this.vcoeff, 1, 100, false));
		
	}
	
	public double getHcoeff() {
		return hcoeff;
	}

	public void setHcoeff(double hcoeff) {
		this.hcoeff = hcoeff;
		SettingsUtil.setVelocityHorizontal(this.hcoeff);
	}

	public double getVcoeff() {
		return vcoeff;
	}

	public void setVcoeff(double vcoeff) {
		this.vcoeff = vcoeff;
		SettingsUtil.setVelocityVertical(this.vcoeff);
	}
	
}

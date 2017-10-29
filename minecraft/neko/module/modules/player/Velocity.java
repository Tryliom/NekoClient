package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;

public class Velocity extends Module {
	private double hcoeff = 1;
	private double vcoeff = 1;
	private static Velocity instance;
	
	public Velocity() {
		super("Velocity", Keyboard.KEY_P, Category.PLAYER);
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
	
	public double getHcoeff() {
		return hcoeff;
	}

	public void setHcoeff(double hcoeff) {
		this.hcoeff = hcoeff;
	}

	public double getVcoeff() {
		return vcoeff;
	}

	public void setVcoeff(double vcoeff) {
		this.vcoeff = vcoeff;
	}
	
}

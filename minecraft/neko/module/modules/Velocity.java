package neko.module.modules;

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

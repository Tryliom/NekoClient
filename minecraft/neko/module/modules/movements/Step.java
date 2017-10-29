package neko.module.modules.movements;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;


public class Step extends Module {
	private boolean bypass=false;
	private double step=50;
	private static Step instance=null;
	
	public Step() {
		super("Step", Keyboard.KEY_I, Category.MOVEMENT);
		instance = this;
	}
	
	public static Step getStep() {
		return instance;
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.thePlayer.stepHeight = 0.5F;
		super.onDisabled();
	}
	
	public String getValues() {
		return "§6Hauteur:§7 "+step+" blocs\n"
				+ "§6Bypass:§7 "+Utils.displayBool(bypass);
	}
	
	public void onUpdate() {
		mc.thePlayer.stepHeight = (float) step;				    						
	}

	public boolean isBypass() {
		return bypass;
	}

	public void setBypass(boolean bypass) {
		this.bypass = bypass;
	}

	public void setStep(double step) {
		this.step = step;
	}	
}
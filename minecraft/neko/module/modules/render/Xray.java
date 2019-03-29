package neko.module.modules.render;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public final class Xray extends Module {
	
	private boolean alreadyactivated;
	private boolean active;
	
	public Xray() {
		super("Xray", Keyboard.KEY_X, Category.RENDER);
	}

	public void onEnabled() {
		
		if(Utils.getModule("Fullbright").getToggled() == true) {
			this.alreadyactivated = true;
		} else {
			Utils.getModule("Fullbright").setToggled(true);
		}
		super.onEnabled();
		mc.renderGlobal.loadxRayRenderers();
		Utils.addChat("§eLe §6XRay §en'est pas modulable et a quelques bugs. Utilisez le §6..search §epour §echercher §eun bloc spécifique.");
	}

	public void onDisabled() {
		
		if(this.alreadyactivated == false) {
			Utils.getModule("Fullbright").setToggled(false);
		}
		mc.renderGlobal.loadxRayRenderers();
		super.onDisabled();
	}
	
	public void onUpdate() {
		
		if(Utils.getModule("Fullbright").getToggled() == false) {
			Utils.getModule("Fullbright").setToggled(true);
		}
		Utils.spectator = true;
		super.onUpdate();
	}

	public void setValues() {
		this.values = "";
	}

}

package neko.module.modules.render;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public final class Xray extends Module {
	
	public Xray() {
		super("Xray", Keyboard.KEY_X, Category.RENDER);
	}

	public void onEnabled() {
		mc.gameSettings.gammaSetting = 10f;
		mc.renderGlobal.loadRenderers();
		super.onEnabled();
		Utils.addChat("�eLe �6XRay �en'est pas modulable et a quelques bugs. Utilisez le �6..search �epour �echercher �eun bloc sp�cifique.");
	}

	public void onDisabled() {
		mc.gameSettings.gammaSetting = 0.5f;
		mc.renderGlobal.loadRenderers();
		super.onDisabled();
	}
	
	public void onUpdate() {
		Utils.spectator = true;
		mc.gameSettings.gammaSetting = 10f;
		super.onUpdate();
	}

	public void setValues() {
		this.values = "";
	}

}

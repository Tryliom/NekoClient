package neko.module.modules.params;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.guicheat.clickgui.ClickGUI;
import neko.guicheat.clickgui.settings.Setting;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Gui extends Module {
	

	public Gui() {
		super("Gui", Keyboard.KEY_RCONTROL, Category.PARAMS, false);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("New");
		options.add("JellyLike");
		Client.Neko.settingsManager.rSetting(new Setting("Gui_Design", this, "New", options));
		Client.getNeko().settingsManager.rSetting(new Setting("Gui_Sound", this, false));
		Client.getNeko().settingsManager.rSetting(new Setting("Gui_Rainbow", this, false));
		Client.getNeko().settingsManager.rSetting(new Setting("Gui_Red", this, 255, 0, 255, true));
		Client.getNeko().settingsManager.rSetting(new Setting("Gui_Green", this, 26, 0, 255, true));
		Client.getNeko().settingsManager.rSetting(new Setting("Gui_Blue", this, 42, 0, 255, true));
	}
	
	@Override
	public void onEnabled() {
		mc.displayGuiScreen(Client.getNeko().clickGui);
		super.onEnabled();
	}
	
	public void onDisabled() {
		Utils.saveFrame();
		mc.displayGuiScreen(null);
		super.onDisabled();
	}
	
	public void onToggle() {
		if(!(mc.currentScreen instanceof ClickGUI)) {
			mc.displayGuiScreen(var.clickGui);
		}
	}

}













package neko.module.modules.hide;

import java.util.ArrayList;

import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import de.Hero.settings.Setting;
import neko.Client;
import neko.guicheat.UIRender;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Gui extends Module {

	public Gui() {
		super("Gui", Keyboard.KEY_RCONTROL, Category.RENDER);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("New");
		options.add("JellyLike");
		Client.getNeko().settingsManager.rSetting(new Setting("Design", Utils.getModule("Gui"), "New", options));
		Client.getNeko().settingsManager.rSetting(new Setting("Sound", Utils.getModule("Gui"), false));
		Client.getNeko().settingsManager.rSetting(new Setting("GuiRed", Utils.getModule("Gui"), 255, 0, 255, true));
		Client.getNeko().settingsManager.rSetting(new Setting("GuiGreen", Utils.getModule("Gui"), 26, 0, 255, true));
		Client.getNeko().settingsManager.rSetting(new Setting("GuiBlue", Utils.getModule("Gui"), 42, 0, 255, true));
	}
	
	@Override
	public void onEnabled() {
		mc.displayGuiScreen(Client.getNeko().clickGui);
	}
	
	public void onDisabled() {
		//Utils.saveFrame();
	}
	
	public void onToggle() {
		/*if(!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
			mc.displayGuiScreen(new GuiManagerDisplayScreen(var.gui));
			new UIRender().renderAndUpdateFrames();
		}*/
	}

}













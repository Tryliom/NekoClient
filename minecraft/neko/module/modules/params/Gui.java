package neko.module.modules.params;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Gui extends Module {

	public Gui() {
		super("Gui", Keyboard.KEY_RCONTROL, Category.PARAMS);
	}
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<>();
		options.add("New");
		options.add("JellyLike");
		try {
			Client.Neko.settingsManager.rSetting(new Setting("Design", this, "New", options));
		} catch (NullPointerException e) {
			System.out.println("Erreur Design Gui New : " + e);
		}
		try {
			Client.getNeko().settingsManager.rSetting(new Setting("Sound", this, false));
		} catch (NullPointerException e) {
			System.out.println("Erreur Sound Gui : " + e);
		}
		try {
			Client.getNeko().settingsManager.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
		} catch (NullPointerException e) {
			System.out.println("Erreur GuiRed Gui : " + e);
		}
		try {
			Client.getNeko().settingsManager.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
		} catch (NullPointerException e) {
			System.out.println("Erreur GuiGreen Gui : " + e);
		}
		try {
			Client.getNeko().settingsManager.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
		} catch (NullPointerException e) {
			System.out.println("Erreur GuiBlue Gui : " + e);
		}
	}
	
	@Override
	public void onEnabled() {
		mc.displayGuiScreen(Client.getNeko().clickGui);
		super.onEnabled();
	}
	
	public void onDisabled() {
		//Utils.saveFrame();
		mc.displayGuiScreen(null);
		super.onDisabled();
	}
	
	public void onToggle() {
		/*if(!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
			mc.displayGuiScreen(new GuiManagerDisplayScreen(var.gui));
			new UIRender().renderAndUpdateFrames();
		}*/
	}

}













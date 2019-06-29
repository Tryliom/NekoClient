package neko.module.modules.params;

import neko.Client;
import neko.gui.InGameGui;
import neko.guicheat.clickgui.settings.Setting;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class ArrayList extends Module {
	
	public ArrayList() {
		super("ArrayList", -1, Category.PARAMS, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	@Override
	public void setup() {
		java.util.ArrayList<String> ordre = new java.util.ArrayList<>();
		ordre.add("Alphabétique"); ordre.add("Taille");
		java.util.ArrayList<String> rangement = new java.util.ArrayList<>();
		rangement.add("Module"); rangement.add("Mélanger");
		java.util.ArrayList<String> invert = new java.util.ArrayList<>();
		invert.add("Oui"); invert.add("Non");
		java.util.ArrayList<String> box = new java.util.ArrayList<>();
		box.add("Neko Box"); box.add("Name Box"); box.add("Sans Box");
		java.util.ArrayList<String> color = new java.util.ArrayList<>();
		color.add("Rainbow"); color.add("UniColor"); color.add("Basique");
		Client.Neko.settingsManager.rSetting(new Setting("Ordre", this, "Alphabétique", ordre));
		Client.Neko.settingsManager.rSetting(new Setting("Rangement", this, "Module", rangement));
		Client.Neko.settingsManager.rSetting(new Setting("Inverser", this, "Non", invert));
		Client.Neko.settingsManager.rSetting(new Setting("Shadow Box", this, "Name Box", box));
		Client.Neko.settingsManager.rSetting(new Setting("Array Color", this, "Basique", color));
		Client.getNeko().settingsManager.rSetting(new Setting("Array Red", this, 255, 0, 255, true));
		Client.getNeko().settingsManager.rSetting(new Setting("Array Green", this, 26, 0, 255, true));
		Client.getNeko().settingsManager.rSetting(new Setting("Array Blue", this, 42, 0, 255, true));
		
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onRender2D() {
		// Que si le debugFPS n'est pas actif
		if (!mc.gameSettings.showDebugInfo)
			g.render();
	}
}

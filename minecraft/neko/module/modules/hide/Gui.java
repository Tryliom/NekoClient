package neko.module.modules.hide;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.input.Keyboard;

import neko.guicheat.UIRender;
import neko.manager.GuiManager;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Gui extends Module {

	public Gui() {
		super("Gui", Keyboard.KEY_RCONTROL, Category.HIDE);
	}
	
	public void onToggle() {
		if(!(mc.currentScreen instanceof GuiManagerDisplayScreen)) {
			mc.displayGuiScreen(new GuiManagerDisplayScreen(var.gui));
			new UIRender().renderAndUpdateFrames();
		}
	}

}













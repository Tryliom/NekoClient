package neko.guicheat;

import java.awt.Color;

import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager.HorizontalGridConstraint;
import org.darkstorm.minecraft.gui.listener.ButtonListener;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;
import org.lwjgl.opengl.GL11;

import neko.Client;
import neko.guicheat.GuiManager.ModuleFrame;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;

public class UIRender {
	Client var = Client.getNeko();
	
	public void renderAndUpdateFrames(){
		for(Frame f: var.gui.getFrames()) {
			f.update();
			if (f.getTitle().equalsIgnoreCase("Commande")) {
				for (Component c : f.getChildren()) {
					f.remove(c);
				}
				for(Module m : var.moduleManager.ActiveModule) {
					if (m.getCategory()==Category.HIDE && m.isCmd()) {
						
						final Module updateModule = m;
						Button button = new BasicButton(m.getName());
						button.addButtonListener(new ButtonListener(){
							@Override
							public void onButtonPress(Button button){
								updateModule.toggleModule();
							}
						});
						f.add(button, HorizontalGridConstraint.FILL);	
					}
								
					
					
				}
			}
		}		
	}
}

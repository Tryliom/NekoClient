package neko.guicheat;

import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.layout.GridLayoutManager.HorizontalGridConstraint;
import org.darkstorm.minecraft.gui.listener.ButtonListener;

import neko.Client;
import neko.module.Category;
import neko.module.Module;

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

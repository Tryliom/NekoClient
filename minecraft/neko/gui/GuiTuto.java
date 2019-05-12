package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.manager.TutoManager;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GuiTuto extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private Client var = Client.getNeko();
	private TutoManager tm = TutoManager.getTuto();
	private String part=tm.getPart(tm.getPart());
	private int hBtn=0;
	
	public GuiTuto(GuiScreen gui) {
		this.prevGui=gui;
	}
	
	public void initGui() {
		this.buttonList.clear();
		if (tm.getPart()==1) {
			this.buttonList.add(new GuiButton(2, this.width / 2 - 150, hBtn+24, 300, 20, "§aActiver l'HUD & ArrayList et passer au n°2"));
		} else {
			this.buttonList.add(new GuiButton(2, this.width / 2 - 150, hBtn+24, 300, 20, (tm.getPart())==tm.getTotPart() ? "§aTerminer le tuto" : "§aPasser au n°"+(tm.getPart()+1)));
			if ((tm.getPart()-1)!=0)
				this.buttonList.add(new GuiButton(3, this.width / 6 - 50, this.height - 50, 100, 20, "§aRetourner au n°"+(tm.getPart()-1)));
		}
		this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 50, 100, 20, "Retour"));
		this.buttonList.add(new GuiButton(0, this.width - (this.width / 6 + 50), this.height - 50, 100, 20, "§cPasser le tuto"));
		this.part = tm.getPart(tm.getPart());
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		drawRect(10, 10, this.width - 10, this.height - 10, Integer.MIN_VALUE);	    
        // this.port.drawTextBox();
		String s[] = this.part.split("\n");
		for (int i=0;i<s.length;i++) {
			hBtn=30 + 15 * i;
			this.drawCenteredString(var.NekoFont, s[i], this.width / 2, hBtn, -1);
		}
        this.initGui(); // Pour les test
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button.id == 0) {
			tm.setDone(true);
			tm.setPart(1);
			mc.displayGuiScreen(this.prevGui);
		}
		if (button.id == 1) {
			mc.displayGuiScreen(this.prevGui);
		}
		if (button.id == 2) {
			if (tm.getPart()==1) {
				if (!Utils.isToggle("HUD"))
					Utils.toggleModule("HUD");
				if (!Utils.isToggle("ArrayList"))
					Utils.toggleModule("ArrayList");
			}
			if ((tm.getPart())==tm.getTotPart()) {
				tm.setDone(true);
				tm.setPart(1);
				mc.displayGuiScreen(this.prevGui);
				return;
			} else {
				tm.setPart(tm.getPart()+1);
			}
			mc.displayGuiScreen(new GuiTuto(this.prevGui));
		}
		if (button.id == 3) {
			tm.setPart(tm.getPart()-1);
			mc.displayGuiScreen(this.prevGui);
		}
	}
	
	  protected void keyTyped(char typedChar, int keyCode) throws IOException {
		  if (keyCode == KeyEvent.VK_ESCAPE)
		    	this.mc.displayGuiScreen(this.prevGui);
		  super.keyTyped(typedChar, keyCode);
	  }

	public void handleMouseInput() throws IOException {		  		 
		super.handleMouseInput();	    
	} 	
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		// this.host.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}
		    
}






















package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GuiShop extends GuiScreen {
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private Client var = Client.getNeko();
	private String info="§aConnecté au NekoShop !";
	
	public GuiShop(GuiScreen gui) {
		this.prevGui=gui;
	}
	
	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 50, 100, 20, "Retour"));
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		drawRect(10, 10, this.width - 10, this.height - 10, Integer.MIN_VALUE);	    
        // this.port.drawTextBox();
        this.drawCenteredString(var.NekoFont, this.info, this.width / 2, 30, -1);
        this.initGui(); // Pour les test
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button.id == 1) {
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






















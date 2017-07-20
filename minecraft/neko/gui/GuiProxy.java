package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.lwjgl.input.Keyboard;

import neko.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiProxy extends GuiScreen {
	 private GuiScreen prevGui;
	 private Minecraft mc = Minecraft.getMinecraft();
	  private ResourceLocation background = mc.getTextureManager().getDynamicTextureLocation("background", GuiMainMenu.viewportTexture);
	  private Client var = Client.getNeko();
	  private GuiTextField host;
	  private GuiTextField port;
	  private String error="";
	  
	  public GuiProxy(GuiScreen gui)
	  {
	    this.prevGui = gui;
	  }
	  
	  public void initGui()
	  {
	    int j = 28;
	    this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height - 200, 100, 20, "Se connecter"));
	    this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height - 150, 100, 20, "Retour"));
	    this.host = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
	    this.port = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 100, 98, 200, 20);
	    this.host.setText("Adresse Host");
	    this.port.setText("Port");
	  }
	  
	  public void drawScreen(int mouseX, int mouseY, float partialTicks)
	  {
	    ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	    this.mc.getTextureManager().bindTexture(this.background);
	    Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
	    
	    drawDefaultBackground();
	    
	    drawCenteredString(var.NekoFont, "Proxy", this.width / 2, 10, 16777215);      
	    drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, 140, 16777215);
        String var10 = "§b-[§eNeko v"+var.CLIENT_VERSION+"§b]-";
        this.host.drawTextBox();
        this.port.drawTextBox();
        this.drawString(var.NekoFont, var10, 2, this.height - 10, -1);
        String var11="§b-[§eBy Tryliom§b]-";
        this.drawString(var.NekoFont, var11, this.width - var.NekoFont.getStringWidth(var11) - 2, this.height - 10, -1);
	    super.drawScreen(mouseX, mouseY, partialTicks);
	  }
	  
	  protected void actionPerformed(GuiButton button)
	    throws IOException
	  {
	    super.actionPerformed(button);
	    switch (button.id)
	    {
	    case 0: 
	      this.mc.displayGuiScreen(this.prevGui);
	      break;
	    case 1: 	    	
	    	String host = this.host.getText();
	    	String port = (this.port.getText().equalsIgnoreCase("Port") ? "1080" : this.port.getText());
	    	if (host.isEmpty() || host.equalsIgnoreCase("Adresse Host")) {
	    		this.error="Veuillez remplir ce champs !";
	    	} else {
		    	Properties props = System.getProperties();
		    	props.setProperty("socksProxyHost", host);
		    	props.setProperty("socksProxyPort", port.isEmpty() ? "1080" : port); // Par défaut 80
		    	System.setProperties(props);
		    	this.mc.displayGuiScreen(this.prevGui);
	    	}
	    	break;	    
	    }
	  }
	  
	  protected void keyTyped(char typedChar, int keyCode)
	    throws IOException
	  {
		  if (this.host.isFocused()) {
			  if (keyCode==15) {
				  this.host.setFocused(!this.host.isFocused());
				  this.port.setFocused(!this.port.isFocused());
			  }
		  } else if (this.port.isFocused()) {
			  if (keyCode==15) {
				  this.host.setFocused(!this.host.isFocused());
				  this.port.setFocused(!this.port.isFocused());
			  }
		  }
		  
		  if (this.host.isFocused()) {
			  if (this.host.getText().equalsIgnoreCase("Adresse Host"))
				  this.host.setText("");
			  this.host.textboxKeyTyped(typedChar, keyCode);
	      }
		  if (this.port.isFocused()) {
			  if (this.port.getText().equalsIgnoreCase("Port"))
				  this.port.setText("");
			  this.port.textboxKeyTyped(typedChar, keyCode);
	      }
	    if (keyCode == KeyEvent.VK_ESCAPE)
	    	this.mc.displayGuiScreen(this.prevGui);
	    super.keyTyped(typedChar, keyCode);
	  }
	  
	  public void handleMouseInput()
	    throws IOException
	  {		  		 
	    super.handleMouseInput();	    
	  }	  	  	 	  
	  
	  protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
		      throws IOException
		    {
		      this.host.mouseClicked(mouseX, mouseY, mouseButton);
		      this.port.mouseClicked(mouseX, mouseY, mouseButton);
		      
		      super.mouseClicked(mouseX, mouseY, mouseButton);
		    }
		    
		    public void onGuiClosed()
		    {
		      Keyboard.enableRepeatEvents(false);
		      super.onGuiClosed();
		    }
		    
}

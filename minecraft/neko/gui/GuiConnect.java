package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiConnect extends GuiScreen {
	 private GuiScreen prevGui;
	 private Minecraft mc = Minecraft.getMinecraft();
	  private ResourceLocation background = mc.getTextureManager().getDynamicTextureLocation("background", GuiMainMenu.viewportTexture);
	  private Client var = Client.getNeko();
	  private GuiTextField user;
	  private GuiTextField pass;
	  private String error="";
	  private boolean userv = false;
	  private boolean passv = false;
	  private int part;
	  
	  public GuiConnect(GuiScreen gui, int part)
	  {
	    this.prevGui = gui;
	    this.part=part;
	  }
	  
	  public void initGui()
	  {
		this.buttonList.clear();
	    int j = 28;
	    if (this.part==1) {
		    this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 3 + 50, 100, 20, "Se connecter"));
		    this.buttonList.add(new GuiButton(0, this.width / 2 - 105, this.height - 50, 100, 20, "Refresh"));
		    this.buttonList.add(new GuiButton(2, this.width / 2 - 105, this.height / 3 + 50, 100, 20, "Créer un compte"));
		    this.buttonList.add(new GuiButton(-1, this.width - 75, this.height / 2, 50, 20, "Next"));
		    this.user = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
		    this.pass = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 100, 98, 200, 20);
		    this.user.setText("Pseudo");
		    this.pass.setText("Mot de passe");
	    }
	  }
	  
	  public void drawScreen(int mouseX, int mouseY, float partialTicks)
	  {
	    ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	    this.mc.getTextureManager().bindTexture(this.background);
	    Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
	    
	    drawDefaultBackground();
	    
	    drawCenteredString(var.NekoFont, "§e§nConnexion à Neko", this.width / 2, 10, 16777215);      
	    drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, 130, 16777215);
        String var10 = "§b-[§eNeko v"+var.CLIENT_VERSION+"§b]-";
        this.user.drawTextBox();
        this.pass.drawTextBox();
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
	    	String user = this.user.getText();
	    	String pass = this.pass.getText();
	    	if (user.isEmpty() || pass.isEmpty()) {
	    		this.error="Veuillez remplir les champs !";
	    	}
	    	this.error="Ce compte n'existe pas, mauvais mot de passe ou pseudo";
	    	break;
	    case 2:
	    	Utils.openUrl("https://nekohc.fr/beta/register.php");
	    	break;
	    }
	  }
	  
	  protected void keyTyped(char typedChar, int keyCode)
	    throws IOException
	  {
		  if (this.user.isFocused()) {
			  if (keyCode==15) {
				  this.user.setFocused(!this.user.isFocused());
				  this.pass.setFocused(!this.pass.isFocused());
			  }
		  } else if (this.pass.isFocused()) {
			  if (keyCode==15) {
				  this.user.setFocused(!this.user.isFocused());
				  this.pass.setFocused(!this.pass.isFocused());
			  }
		  }
		  
		  if (this.user.isFocused()) {
			  if (this.user.getText().equalsIgnoreCase("Pseudo") && !userv) {
				  this.user.setText("");
				  userv = true;
			  }
			  this.user.textboxKeyTyped(typedChar, keyCode);
	      }
		  if (this.pass.isFocused()) {
			  if (this.pass.getText().equalsIgnoreCase("Mot de passe") && !passv) {
				  this.pass.setText("");
				  passv = true;
			  }
			  this.pass.textboxKeyTyped(typedChar, keyCode);
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
		      this.user.mouseClicked(mouseX, mouseY, mouseButton);
		      this.pass.mouseClicked(mouseX, mouseY, mouseButton);
		      
		      super.mouseClicked(mouseX, mouseY, mouseButton);
		    }
		    
		    public void onGuiClosed()
		    {
		      Keyboard.enableRepeatEvents(false);
		      super.onGuiClosed();
		    }
		    
}

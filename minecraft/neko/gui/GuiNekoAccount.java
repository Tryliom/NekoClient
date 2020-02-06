package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.api.NekoCloud;
import neko.manager.ModuleManager;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiNekoAccount extends GuiScreen {
	
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private Client var = Client.getNeko();
	
	private int scrollingTextPosX;
    private String scrollingText = "§eCeci est votre page de profil Neko, §f"+NekoCloud.getName()+"§e, appuyez sur §cEchap §epour revenir au menu principal.";

	
	public GuiNekoAccount(GuiScreen gui) {
		this.prevGui=gui;
	}
	
	public void initGui() {

	}
	
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation("neko/gui/NekoGuiAccount.png");
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawScaledCustomSizeModalRect(0, 0, 0, 0, 1, 1, this.width, this.height, 1, 1);
        GlStateManager.enableAlpha();
		
	    super.drawScreen(mouseX, mouseY, partialTicks);  
	    
	    int rectPosXLeft = this.width/2-20;
	    int rectPosYTop = 20;
	    int rectPosXRight = this.width-20;
	    int rectPosYBot = this.height-20;
	    
	    int MainBoxColor = 0x778c8c8c;
	    int LineColor = 0x99ffffff;
	    
	    int guiWidth = (rectPosXRight-rectPosXLeft);
	    int guiHeight = (rectPosYBot-rectPosYTop);

	    //Rect basique
		drawRect(rectPosXLeft, rectPosYTop, rectPosXRight, rectPosYBot, MainBoxColor);
		
	    double maxNekoName = ((0.05)*guiWidth); int maxintNekoName = (int)maxNekoName; int maxNekoNameOwo = maxintNekoName+rectPosXLeft;
	    double maxNekoRank = ((0.05)*guiWidth); int maxintNekoRank = (int)maxNekoRank; int maxNekoRankOwo = maxintNekoRank+rectPosXLeft+Client.getNeko().NekoFont.getStringWidth(NekoCloud.getName())+50;
	    
	    //Name + Rank
		drawString(Client.getNeko().NekoFont, "§f§l"+NekoCloud.getName(), maxNekoNameOwo, rectPosYTop+15, -1);
		drawString(Client.getNeko().NekoFont, var.rang.getColor()+var.rang.getName(), maxNekoRankOwo, rectPosYTop+15, -1);
		
		//First line
	    drawHorizontalLine(rectPosXLeft+5, rectPosXRight-5, rectPosYTop+30, LineColor);
	    
	    //Lvl + XP
	    double maxNekoLvl = ((0.35)*guiWidth); int maxintNekoLvl = (int)maxNekoLvl; int maxNekoLvlOwo = maxintNekoLvl+rectPosXLeft;
	    drawString(Client.getNeko().NekoFont, "§9§lLVL     §f§l"+String.valueOf(var.niveau), maxNekoLvlOwo, rectPosYTop+50, -1);
	    double maxNekoXPH = ((0.75)*guiWidth); int maxintNekoXPH = (int)maxNekoXPH; int maxNekoXPHOwo = maxintNekoXPH+rectPosXLeft;
	    drawString(Client.getNeko().NekoFont, "§9§lXp Actuel", maxNekoXPHOwo, rectPosYTop+50, -1);
	    double maxNekoXPH2 = ((0.75)*guiWidth); int maxintNekoXPH2 = (int)maxNekoXPH2; int maxNekoXPHOwo2 = maxintNekoXPH2+rectPosXLeft;
	    drawString(Client.getNeko().NekoFont, "§f§l"+String.valueOf(var.xp), maxNekoXPHOwo2, rectPosYTop+60, -1);
	    double maxNekoXPN = ((0.75)*guiWidth); int maxintNekoXPN = (int)maxNekoXPN; int maxNekoXPNOwo = maxintNekoXPN+rectPosXLeft;
	    drawString(Client.getNeko().NekoFont, "§9§lNext level", maxNekoXPNOwo, rectPosYTop+80, -1);
	    double maxNekoXPN2 = ((0.75)*guiWidth); int maxintNekoXPN2 = (int)maxNekoXPN2; int maxNekoXPNOwo2 = maxintNekoXPN2+rectPosXLeft;
	    drawString(Client.getNeko().NekoFont, "§f§l"+String.valueOf(var.xpMax), maxNekoXPNOwo2, rectPosYTop+90, -1);
	    
	    
	    //Second line
	    drawHorizontalLine(rectPosXLeft+5, rectPosXRight-5, rectPosYTop+100, LineColor);
	    
	    //int rangs unlock, souls, time playing
	    drawString(Client.getNeko().NekoFont, "§9§lSouls", maxNekoLvlOwo, rectPosYTop+110, -1);
	    drawString(Client.getNeko().NekoFont, "§f§l"+String.valueOf(var.ame), maxNekoLvlOwo, rectPosYTop+120, -1);
	    drawString(Client.getNeko().NekoFont, "§9§lTemps de jeu", maxNekoLvlOwo, rectPosYTop+140, -1);
	    drawString(Client.getNeko().NekoFont, "§f§l"+String.valueOf((Utils.timeInGameHour!=0 ? Utils.timeInGameHour+"h "+Utils.timeInGameMin+"min "+Utils.timeInGameSec : Utils.timeInGameMin!=0 ? Utils.timeInGameMin+"min "+Utils.timeInGameSec : Utils.timeInGameSec)+"s"), maxNekoLvlOwo, rectPosYTop+150, -1);
	    drawString(Client.getNeko().NekoFont, "§9§lRangs", maxNekoLvlOwo, rectPosYTop+170, -1);
	    drawString(Client.getNeko().NekoFont, "§f§l"+String.valueOf(Utils.getNbRankUnlock()+"§8§l/§f§l"+ModuleManager.rang.size()), maxNekoLvlOwo, rectPosYTop+180, -1);
	    //var.time
	    
	    this.scrollingTextPosX--;
        if(this.scrollingTextPosX < -Client.getNeko().NekoFont.getStringWidth(this.scrollingText)) {
        	this.scrollingTextPosX = this.width;
        }
        this.drawRect(0, 0, this.width, 12, 0x33FFFFFF);
        this.drawString(Client.getNeko().NekoFont, this.scrollingText, this.scrollingTextPosX, 2, -1);
        
	
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 9863) {
			this.mc.displayGuiScreen(this.prevGui);
		}
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == KeyEvent.VK_ESCAPE) {
			this.mc.displayGuiScreen(this.prevGui);
		}
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

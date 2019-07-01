package neko.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.api.NekoCloud;
import neko.module.other.Irc;
import neko.updater.Updater;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiUpdate extends GuiScreen {
	
	private GuiScreen prevGui;
	private Minecraft mc = Minecraft.getMinecraft();
	private ResourceLocation background = new ResourceLocation("textures/gui/title/background/npanorama_0.png");
	private Client var = Client.getNeko();
	private String error = "";
	private int part;
	private String version = "";
	private NekoCloud nc = NekoCloud.getNekoAPI();
	
	public GuiUpdate(GuiScreen gui, int part, String versions, String... loginorcreate) {
		this.prevGui = gui;
		this.part = part;
		this.version = versions;
		if (loginorcreate.length > 0) {
			this.error = "Le nom du compte sera le même que dans l'irc";
		}
	}
	
	public void initGui() {
		this.buttonList.clear();
		
		this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 3 + 74, 100, 20, "§cDémarrer la mise à jour"));
		
		if(this.part == 1) {
			
		} else if(this.part == 2) {
			
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc);
		this.mc.getTextureManager().bindTexture(this.background);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		//drawDefaultBackground();
		
		drawCenteredString(var.NekoFont, "§eLa mise à jour §3" + this.version + " §ede Neko est disponible !", this.width / 2, this.height / 2 - 70, 16777215);
		drawCenteredString(var.NekoFont, "§eAjout principal: §3" + Client.getNeko().changelog, this.width / 2, this.height / 2 - 50, 16777215);
		drawCenteredString(var.NekoFont, "§c§nCliquez sur le bouton pour la démarrer. (Et redémarrez Minecraft.)", this.width / 2, this.height / 2 - 10, 16777215);
		drawCenteredString(fontRendererObj, "§c(Si votre jeu ne s'éteind pas automatiquement après l'appuie sur le bouton, étéignez-le.)", this.width / 2, this.height / 3 + 100, 16777215);
		
		drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, this.height / 2 - 50, 16777215);
		String var10 = Client.getNeko().strNeko;
		if (this.part == 1) {
			
			//Calcul des INT RGBA : https://www.shodor.org/stella2java/rgbint.html
		}
		this.drawString(var.NekoFont, var10, 2, this.height - 10, -1);
		String var11 = Client.getNeko().strCreator;
		this.drawString(var.NekoFont, var11, this.width - var.NekoFont.getStringWidth(var11) - 2, this.height - 10, -1);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		//Updater.update(this.version);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 1:
			Updater.update(this.version);
			break;
		}
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (this.part == 1) {
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}

}

package neko.gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;

import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import neko.Client;
import neko.api.NekoCloud;
import neko.manager.GuiManager;
import neko.manager.ModuleManager;
import neko.manager.OnlyRpgManager;
import neko.module.other.Irc;
import neko.module.other.Rank;
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
	/*private ResourceLocation background = mc.getTextureManager().getDynamicTextureLocation("background",
			GuiMainMenu.viewportTexture);*/
	private ResourceLocation background = new ResourceLocation("textures/gui/title/background/npanorama_0.png");
	private Client var = Client.getNeko();
	private GuiTextField user;
	private GuiTextField pass;
	private String error = "";
	private boolean userv = false;
	private boolean passv = false;
	private int part;
	private String loginOrCreate = "login";
	private NekoCloud nc = NekoCloud.getNekoAPI();

	public GuiConnect(GuiScreen gui, int part, String... loginorcreate) {
		this.prevGui = gui;
		this.part = part;
		if (loginorcreate.length > 0) {
			this.loginOrCreate = "create";
			this.error = "Le nom du compte sera le même que dans l'irc";
		}
	}

	public void initGui() {
		this.buttonList.clear();
		int j = 28;
		if (this.part == 1) {
			this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 2 + 50, 100, 20,
					this.loginOrCreate.equalsIgnoreCase("create") ? "Créer" : "Login"));
			this.buttonList.add(new GuiButton(2, this.width / 2 - 105, this.height / 2 + 50, 100, 20,
					this.loginOrCreate.equalsIgnoreCase("create") ? "Passer au login" : "Créer un compte"));
			this.user = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 20, 200, 20);
			this.pass = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 100, this.height / 2 + 10, 200, 20);
			
			this.user.setText("Pseudo");
			this.pass.setText("Mot de passe");
		} else if (this.part == 2) {
			this.buttonList.add(new GuiButton(3, this.width / 2 - 105, this.height / 3 + 50, 205, 20,
					"§aImporter votre sauvegarde local"));
			this.buttonList.add(new GuiButton(4, this.width / 2 - 50, this.height / 3 + 74, 100, 20, "§cIgnorer"));
		}
		this.buttonList.add(new GuiButton(5, this.width-110, 10, 100, 20, "Music Manager"));
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		this.mc.getTextureManager().bindTexture(this.background);
		
		Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.getScaledWidth(), sr.getScaledHeight(),
				sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());

		//drawDefaultBackground();
		
		drawCenteredString(var.NekoFont, "§e§nConnexion à Neko", this.width / 2, this.height / 2 - 70, 16777215);
		
		drawCenteredString(var.NekoFont, "§c" + this.error, this.width / 2, this.height / 2 - 50, 16777215);
		String var10 = Client.getNeko().strNeko;
		if (this.part == 1) {
			this.user.drawRGBATextBox(-13882323, -14737633);
			this.pass.drawRGBATextBox(-13882323, -14737633);
			//Calcul des INT RGBA : https://www.shodor.org/stella2java/rgbint.html
		}
		this.drawString(var.NekoFont, var10, 2, this.height - 10, -1);
		String var11 = Client.getNeko().strCreator;
		this.drawString(var.NekoFont, var11, this.width - var.NekoFont.getStringWidth(var11) - 2, this.height - 10, -1);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch (button.id) {
		case 1:
			String user = this.user.getText();
			String pass = this.pass.getText();
			if (user.isEmpty() || pass.isEmpty()) {
				this.error = "Veuillez remplir les champs !";
			}
			this.nc.setName(user);
			this.nc.setPassword(pass);
			String result = this.loginOrCreate.equalsIgnoreCase("create") ? this.nc.createAccount()
					: this.nc.loginAccount();
			if (result.equalsIgnoreCase("success")) {
				Utils.saveCredentials();
				this.nc.setLogin(true);
				if (this.loginOrCreate.equalsIgnoreCase("create"))
					this.mc.displayGuiScreen(new GuiConnect(new GuiMainMenu(), 2));
				else {
					Utils.loadSaveCloud();
					this.mc.displayGuiScreen(new GuiMainMenu());
				}
				break;
			} else {
				this.error = result;
			}
			break;
		case 2:
			// Mettre en mode register
			if (this.loginOrCreate.equalsIgnoreCase("create"))
				this.mc.displayGuiScreen(new GuiConnect(new GuiMainMenu(), 1));
			else
				this.mc.displayGuiScreen(new GuiConnect(new GuiMainMenu(), 1, "".split("")));
			break;
		case 3:
			// Importer save
			this.error = "Importation en cours...";
			Utils.importSave();
			this.error = "§aImportation réussie !";
			for (Object o : this.buttonList) {
				if (o instanceof GuiButton) {
					GuiButton gb = (GuiButton) o;
					if (gb.id==3) {
						gb.visible = false;
					}
					if (gb.id==4) {
						gb.displayString="§aEntrer";
					}
				}
			}
			break;
		case 4:
			// Ignore
			Irc.getInstance().setNamePlayer(NekoCloud.getNekoAPI().getName());
			this.mc.displayGuiScreen(new GuiMainMenu());
			break;
		case 5:
			this.mc.displayGuiScreen(new GuiMusicManager(this));
			break;
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (this.user.isFocused()) {
			if (keyCode == 15) {
				this.user.setFocused(!this.user.isFocused());
				this.pass.setFocused(!this.pass.isFocused());
			}
		} else if (this.pass.isFocused()) {
			if (keyCode == 15) {
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
		super.keyTyped(typedChar, keyCode);
	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
	}

	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		if (this.part == 1) {
			this.user.mouseClicked(mouseX, mouseY, mouseButton);
			this.pass.mouseClicked(mouseX, mouseY, mouseButton);
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		super.onGuiClosed();
	}

}
